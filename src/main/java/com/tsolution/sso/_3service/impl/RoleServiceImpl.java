package com.tsolution.sso._3service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsolution.sso._1Entities.RoleEntity;
import com.tsolution.sso._2Repository.RoleRepository;
import com.tsolution.sso._3service.RoleService;
import com.tsolution.sso.exceptions.BusinessException;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public ResponseEntity<Object> getOne(Long roleId) {
		return new ResponseEntity<>(this.roleRepository.findById(roleId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> find(String clientId, String text, Integer pageNumber, Integer pageSize) {
		return new ResponseEntity<>(this.roleRepository.find(clientId, text, PageRequest.of(pageNumber, pageSize)),
				HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> create(RoleEntity roleEntity) {
		return new ResponseEntity<>(this.roleRepository.save(roleEntity), HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> update(Long id, RoleEntity roleEntityInput) throws Exception {
		RoleEntity roleEntity = this.roleRepository.getOne(id);
		if (roleEntity == null) {
			throw new BusinessException("Quyền không tồn tại");
		}
		roleEntity.setClientId(roleEntityInput.getClientId());
		roleEntity.setDescription(roleEntityInput.getDescription());
		roleEntity.setRoleName(roleEntityInput.getRoleName());
		if ((roleEntityInput.getMenus() != null) && (roleEntityInput.getPermissions() != null)) {
			roleEntity.setMenus(roleEntityInput.getMenus());
			roleEntity.setPermissions(roleEntityInput.getPermissions());
		}
		return new ResponseEntity<>(this.roleRepository.save(roleEntity), HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> delete(Long id) throws Exception {
		this.roleRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAll() {
		return new ResponseEntity<>(this.roleRepository.findAll(), HttpStatus.OK);
	}
}
