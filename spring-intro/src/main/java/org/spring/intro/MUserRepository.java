package org.spring.intro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MUserRepository extends JpaRepository<MUser, Long> {
}
