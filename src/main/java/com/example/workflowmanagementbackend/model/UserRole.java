package com.example.workflowmanagementbackend.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    //role
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    @Column(nullable = false, updatable = false)
    private Date createAt;

    public UserRole(User user, Role role) {
    }

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

}
