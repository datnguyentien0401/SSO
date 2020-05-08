package com.tsolution.sso._2Repository;

import com.tsolution.sso._1Entities.PermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface I_PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    @Query (value = "select p from PermissionEntity p where " +
            "( lower(p.clientId) like lower(CONCAT(CONCAT('%',:clientId),'%'))) " +
            "and (lower(p.url) like lower(CONCAT(CONCAT('%',:url),'%')) " +
            "or lower(p.description) like lower(CONCAT(CONCAT('%',:description),'%')))")
    Page<PermissionEntity> findAllByConditionSearch
            (String clientId, String url, String description, Pageable pageable);

    List<PermissionEntity> findAllByClientId(String clientId);
}
