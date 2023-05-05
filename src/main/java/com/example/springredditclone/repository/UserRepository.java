package com.example.springredditclone.repository;

import com.example.springredditclone.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("DELETE FROM user u WHERE u.id = :id")
    void deleteById(@Param("id") Long id);
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM user u LEFT JOIN verification_token vt ON u.id = vt.user.id WHERE u.enabled = true OR vt.expiryDate < UTC_TIMESTAMP()")
    List<User> findInactiveUsers();
    @Query("SELECT u FROM user u LEFT JOIN verification_token vt ON u.id = vt.user.id WHERE u.enabled = true OR vt.expiryDate > UTC_TIMESTAMP() AND u.username = :username")
    Optional<User> findActiveUsersByUsername(@Param("username") String username);
    @Query("SELECT u FROM user u LEFT JOIN verification_token vt ON u.id = vt.user.id WHERE u.enabled = true OR vt.expiryDate > UTC_TIMESTAMP() AND u.email = :email")
    Optional<User> findActiveUsersByEmail(@Param("email") String email);
}
