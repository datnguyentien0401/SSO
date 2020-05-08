package com.tsolution.sso._4Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsolution.sso._1Entities.RoleEntity;
import com.tsolution.sso._3service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable("id") Long menuId) {
		return this.roleService.getOne(menuId);
	}

	@GetMapping("/find")
	public ResponseEntity<Object> findAll(@RequestParam(value = "clientId", required = false) String clientId,
			@RequestParam(value = "text", required = false) String text,
			@RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize) {
		return this.roleService.find(clientId, text, pageNumber, pageSize);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() {
		return this.roleService.getAll();
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RoleEntity roleEntity) throws Exception {
		return this.roleService.create(roleEntity);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody RoleEntity roleEntity)
			throws Exception {
		return this.roleService.update(id, roleEntity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) throws Exception {
		return this.roleService.delete(id);
	}

}
