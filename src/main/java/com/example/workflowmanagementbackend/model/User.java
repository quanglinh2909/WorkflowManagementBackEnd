package com.example.workflowmanagementbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    @Column(unique = true, nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    private boolean isActivate = false;

    @Column()
    private String idSocket;
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();
    @Column(nullable = false, updatable = false)
    private Date createAt;

    @PrePersist
    protected void onCreate() {
        createAt = new Date();
    }

    @Column()
    private Date updateAt;
    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }

    @Column(nullable = true)
    private Date deleteAt;

    @PreRemove
    protected void onDelete() {
        deleteAt = new Date();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
