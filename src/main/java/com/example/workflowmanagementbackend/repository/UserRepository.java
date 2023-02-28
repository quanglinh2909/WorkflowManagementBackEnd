package com.example.workflowmanagementbackend.repository;


import com.example.workflowmanagementbackend.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);

    //    //find by idSocket return List<User>
//    List<User> findByIdSocket(String idSocket);
//    //set idSocket = null
    @Modifying
    @Query("UPDATE User u SET u.idSocket = null WHERE u.idSocket = ?1")
    void setIdSocketNull(String idSocket);
}
