package com.example.workflowmanagementbackend.model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")

public class Role  {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  //role
  @Column(unique = true, nullable = false)
  private String role;

  @OneToMany(mappedBy = "role")
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

}
