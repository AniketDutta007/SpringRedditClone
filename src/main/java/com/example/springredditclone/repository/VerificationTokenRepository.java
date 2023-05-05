package com.example.springredditclone.repository;

import com.example.springredditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    @Query("SELECT vt FROM verification_token vt WHERE vt.token = :token AND vt.expiryDate > UTC_TIMESTAMP()")
    Optional<VerificationToken> findByToken(@Param("token") String token);
}
