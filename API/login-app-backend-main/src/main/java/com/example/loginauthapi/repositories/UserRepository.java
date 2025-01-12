package com.example.loginauthapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.loginauthapi.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // Keep Long if your User ID is Long
    // Method to find a user by their email
    Optional<User> findByEmail(String email);

    // Fix for findByUserName (remove the static and implement it properly)
    Optional<User> findByUserName(String userName);

    Optional<User> findById(Integer id);

}
