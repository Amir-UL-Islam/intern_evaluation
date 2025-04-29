package org.spring.intro.repository;

import org.spring.intro.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MUserRepository extends JpaRepository<MUser, Long> {
    @Query("SELECT u FROM MUser u WHERE u.phoneNumber = ?1")
    Optional<MUser> findByPhoneNumber(String username);

    @Query("SELECT u FROM MUser u WHERE u.username = ?1")
    Optional<MUser> findByUsername(String username);
}
