package org.spring.intro.repository;

import org.spring.intro.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MUserRepository extends JpaRepository<MUser, Long> {
}
