package com.tsolution.sso._2Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsolution.sso._1Entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	@Query(value = "SELECT r.* " + RoleSql.FIND, countQuery = "SELECT COUNT(r.id) " + RoleSql.FIND, nativeQuery = true)
	Page<RoleEntity> find(String clientId, String text, Pageable pageable);
}
