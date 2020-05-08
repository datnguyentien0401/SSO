package com.tsolution.sso._3service.impl;

import com.tsolution.sso._1Entities.PermissionEntity;
import com.tsolution.sso._2Repository.I_PermissionRepository;
import com.tsolution.sso._3service.I_PermissionService;
import com.tsolution.sso.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements I_PermissionService {

	@Autowired
	I_PermissionRepository permissionRepository;

	@Override
	public ResponseEntity<Object> getOne(Long permissionId) {
		return new ResponseEntity<>(this.permissionRepository.findById(permissionId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> find(String clientId, String url, String description, Pageable pageable) throws Exception {
		return new ResponseEntity<>(this.permissionRepository.findAllByConditionSearch(clientId, url, description, pageable),
				HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> create(PermissionEntity permissionEntityInput) {

		return new ResponseEntity<>(this.permissionRepository.save(permissionEntityInput), HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> update(Long id, PermissionEntity permissionEntityInput) throws Exception {
		PermissionEntity permissionEntity = this.permissionRepository.getOne(id);
		if (permissionEntity == null) {
			new BusinessException("Phân quyền không tồn tại");
		}

		permissionEntity.setClientId(permissionEntityInput.getClientId());
		permissionEntity.setUrl(permissionEntityInput.getUrl());
		permissionEntity.setDescription(permissionEntityInput.getDescription());

		return new ResponseEntity<>(this.permissionRepository.save(permissionEntity), HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> delete(Long id) throws Exception {
		this.permissionRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAll() {
		return new ResponseEntity<>(this.permissionRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByClientId(String clientId) {
		clientId = clientId == null ? "" : clientId;
		List<PermissionEntity> results = this.permissionRepository.findAllByClientId(clientId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}
