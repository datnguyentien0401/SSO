package com.tsolution.sso._3service;

import org.springframework.http.ResponseEntity;

import com.tsolution.sso._1Entities.RoleEntity;
import com.tsolution.sso.exceptions.BusinessException;

public interface RoleService {
	ResponseEntity<Object> getOne(Long menuId);

	ResponseEntity<Object> find(String clientId, String text, Integer pageNumber, Integer pageSize);

	ResponseEntity<Object> create(RoleEntity roleEntity) throws BusinessException;

	ResponseEntity<Object> update(Long id, RoleEntity roleEntityInput) throws Exception;

	ResponseEntity<Object> delete(Long id) throws Exception;

	ResponseEntity<Object> getAll();
}
