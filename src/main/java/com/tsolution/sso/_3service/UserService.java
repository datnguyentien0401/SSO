package com.tsolution.sso._3service;

import org.springframework.http.ResponseEntity;

import com.tsolution.sso._1Entities.UserEntity;
import com.tsolution.sso.exceptions.BusinessException;

public interface UserService {
	ResponseEntity<Object> changePassword(String oldPassword, String newPassword, String newConfirmPassword)
			throws Exception;

	ResponseEntity<Object> active(String username) throws BusinessException;

	ResponseEntity<Object> deactive(String username) throws BusinessException;

	ResponseEntity<Object> resetPassword(String username) throws BusinessException;

	ResponseEntity<Object> find(String firstName, String lastName, String username, Integer pageNumber,
			Integer pageSize) throws Exception;

	ResponseEntity<Object> create(UserEntity userEntityInput) throws BusinessException;

	ResponseEntity<Object> update(Long id, UserEntity userEntityInput) throws BusinessException;

	ResponseEntity<Object> delete(Long id) throws BusinessException;

	ResponseEntity<Object> getOne(Long id) throws BusinessException;

	ResponseEntity<Object> getUserRole(String username) throws BusinessException;

	ResponseEntity<Object> getStatusByUsername(String username) throws BusinessException;
}
