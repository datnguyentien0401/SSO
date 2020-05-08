package com.tsolution.sso._2Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsolution.sso._1Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findOneByUsername(String username);

	@Query(value = "SELECT SYSDATE()", nativeQuery = true)
	LocalDateTime getSysdate();

	Page<UserEntity> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrUsernameIgnoreCaseContaining(
			String firstName, String lastName, String username, Pageable pageable);
}
