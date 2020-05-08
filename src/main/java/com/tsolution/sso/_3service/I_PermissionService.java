package com.tsolution.sso._3service;

import com.tsolution.sso._1Entities.PermissionEntity;
import com.tsolution.sso.exceptions.BusinessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface I_PermissionService {

    ResponseEntity<Object> getOne(Long menuId);

    ResponseEntity<Object> find(String clientId, String url, String code, Pageable pageable) throws Exception;

    ResponseEntity<Object> create(PermissionEntity permissionEntity) throws BusinessException;

    ResponseEntity<Object> update(Long id, PermissionEntity permissionEntityInput) throws Exception;

    ResponseEntity<Object> delete(Long id) throws Exception;

    ResponseEntity<Object> getAll();

    ResponseEntity<Object> findByClientId(String clientId);
}
