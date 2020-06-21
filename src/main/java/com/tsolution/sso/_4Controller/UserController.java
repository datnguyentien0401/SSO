package com.tsolution.sso._4Controller;

import java.security.Principal;
import com.tsolution.sso._1Entities.dto.ChangePasswordRequestDto;
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

import com.tsolution.sso._1Entities.UserEntity;
import com.tsolution.sso._3service.UserService;
import com.tsolution.sso.exceptions.BusinessException;
import com.tsolution.sso.utils.Translator;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/me")
	public Principal user(Principal principal) {
		return principal;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable("id") Long id) throws BusinessException {
		return this.userService.getOne(id);
	}

	@GetMapping("/{username}/status")
	public ResponseEntity<Object> getStatusByUsername(@PathVariable("username") String username)
			throws BusinessException {
		return this.userService.getStatusByUsername(username);
	}

	@GetMapping("/{username}/role")
	public ResponseEntity<Object> getUserRole(@PathVariable("username") String username) throws BusinessException {
		return this.userService.getUserRole(username);
	}

	@GetMapping("/find")
	public ResponseEntity<Object> getAll(@RequestParam(value = "text", required = false) String text,
			@RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize) throws Exception {
		text = text == null ? "" : text;
		return this.userService.find(text, text, text, pageNumber, pageSize);
	}

	@PostMapping("/change-password")
	public ResponseEntity<Object> changePassword(
			@RequestBody ChangePasswordRequestDto requestDto) throws Exception {
		return this.userService.changePassword(requestDto.getPassword(), requestDto.getNewPassword(), requestDto.getNewPasswordConfirm());
	}

	@PostMapping("/active")
	public ResponseEntity<Object> active(@RequestBody String username) throws BusinessException {
		return this.userService.active(username);
	}

	@PostMapping("/deactive")
	public ResponseEntity<Object> deactive(@RequestBody String username) throws BusinessException {
		return this.userService.deactive(username);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<Object> resetPassword(@RequestBody String username)
			throws BusinessException {
		return this.userService.resetPassword(username);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody UserEntity userEntityInput) throws Exception {
		if (userEntityInput == null) {
			throw new BusinessException(Translator.toLocale("common.input.info.invalid"));
		}
		return this.userService.create(userEntityInput);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Long id,
										 @RequestBody UserEntity userEntityInput)
			throws Exception {
		if (userEntityInput == null) {
			throw new BusinessException(Translator.toLocale("common.input.info.invalid"));
		}
		return this.userService.update(id, userEntityInput);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) throws Exception {
		return this.userService.delete(id);
	}
}
