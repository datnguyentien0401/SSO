package com.tsolution.sso._3service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsolution.sso._1Entities.RoleEntity;
import com.tsolution.sso._1Entities.UserEntity;
import com.tsolution.sso._2Repository.UserRepository;
import com.tsolution.sso._3service.UserService;
import com.tsolution.sso.exceptions.BusinessException;
import com.tsolution.sso.utils.StringUtils;
import com.tsolution.sso.utils.Translator;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final String USER_USERNAME_IS_NOT_EXISTS = "user.username.is.not.exists";

	private static final String COMMON_ACCESS_DENIED = "common.access.denied";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${sso.default.password}")
	private String defaultPassword;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> changePassword(String oldPassword, String newPassword, String newConfirmPassword)
			throws Exception {
		if (StringUtils.isNullOrEmpty(oldPassword) || StringUtils.isNullOrEmpty(newPassword)
				|| StringUtils.isNullOrEmpty(newConfirmPassword)) {
			throw new BusinessException(Translator.toLocale("common.input.info.invalid"));
		}
		if (newPassword.length() < 8) {
			throw new BusinessException(Translator.toLocale("user.input.new.password.character.length.invalid"));
		}
		if (!newPassword.equalsIgnoreCase(newConfirmPassword)) {
			throw new BusinessException(Translator.toLocale("user.input.new.password.invalid"));
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new BusinessException(Translator.toLocale(COMMON_ACCESS_DENIED));
		}
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(authentication.getName());
		if (!oUser.isPresent()) {
			throw new BusinessException(
					Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS, authentication.getName()));
		}
		UserEntity userEntity = oUser.get();
		if (!this.passwordEncoder.matches(oldPassword, userEntity.getPassword())) {
			throw new BusinessException(Translator.toLocale("user.input.old.password.invalid"));
		}
		userEntity.setPassword(this.passwordEncoder.encode(newPassword));
		userEntity = this.userRepository.save(userEntity);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> active(String username) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new BusinessException(Translator.toLocale(COMMON_ACCESS_DENIED));
		}
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(username);
		if (!oUser.isPresent()) {
			throw new BusinessException(Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS, username));
		}
		UserEntity userEntity = oUser.get();
		userEntity.setEnabled(true);
		userEntity = this.userRepository.save(userEntity);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> deactive(String username) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new BusinessException(Translator.toLocale(COMMON_ACCESS_DENIED));
		}
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(username);
		if (!oUser.isPresent()) {
			throw new BusinessException(Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS, username));
		}
		UserEntity userEntity = oUser.get();
		userEntity.setEnabled(false);
		userEntity = this.userRepository.save(userEntity);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> resetPassword(String username) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new BusinessException(Translator.toLocale(COMMON_ACCESS_DENIED));
		}
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(username);
		if (!oUser.isPresent()) {
			throw new BusinessException(Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS, username));
		}
		UserEntity userEntity = oUser.get();
		userEntity.setPassword(this.passwordEncoder.encode(this.defaultPassword));
		userEntity = this.userRepository.save(userEntity);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getOne(Long id) throws BusinessException {
		Optional<UserEntity> oUser = this.userRepository.findById(id);
		if (!oUser.isPresent()) {
			throw new BusinessException(Translator.toLocale("user.username.is.exists"));
		}
		return new ResponseEntity<>(oUser.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getStatusByUsername(String username) throws BusinessException {
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(username);
		if (!oUser.isPresent()) {
			throw new BusinessException(Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS, username));
		}
		return new ResponseEntity<>(oUser.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getUserRole(String username) throws BusinessException {
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(username);
		if (!oUser.isPresent()) {
			throw new BusinessException(Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS, username));
		}
		return new ResponseEntity<>(oUser.get().getRoles(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> find(String firstName, String lastName, String username, Integer pageNumber,
			Integer pageSize) throws Exception {
		return new ResponseEntity<>(this.userRepository
				.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrUsernameIgnoreCaseContaining(
						firstName, lastName, username, PageRequest.of(pageNumber, pageSize)),
				HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> create(UserEntity userEntityInput) throws BusinessException {
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(userEntityInput.getUsername());
		if (oUser.isPresent()) {
			throw new BusinessException(Translator.toLocale("user.username.is.exists"));
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userEntityInput.getUsername());
		userEntity.setFirstName(userEntityInput.getFirstName());
		userEntity.setLastName(userEntityInput.getLastName());
		userEntity.setPassword(this.passwordEncoder.encode(this.defaultPassword));
		userEntity.setEnabled(userEntityInput.getEnabled());
//		if (userEntity.getEnabled() == null) {
//			userEntity.setEnabled(false);
//		}
        userEntity.setEnabled(true);
		userEntity = this.userRepository.save(userEntity);

		if ((userEntityInput.getRoles() != null) && !userEntityInput.getRoles().isEmpty()) {
			List<RoleEntity> roles = new ArrayList<>();
			userEntityInput.getRoles().forEach(roles::add);
			userEntity.setRoles(roles);
		}
		return new ResponseEntity<>(this.userRepository.save(userEntity), HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> update(Long id, UserEntity userEntityInput) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new BusinessException(Translator.toLocale(COMMON_ACCESS_DENIED));
		}
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(userEntityInput.getUsername());
		if (!oUser.isPresent()) {
			throw new BusinessException(
					Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS, userEntityInput.getUsername()));
		}
		if (id > 0) {
			oUser = this.userRepository.findById(id);
			if (!oUser.isPresent()) {
				throw new BusinessException(Translator.toLocaleByFormatString(USER_USERNAME_IS_NOT_EXISTS,
						userEntityInput.getUsername()));
			}
		}
		UserEntity user = oUser.get();
		user.setFirstName(userEntityInput.getFirstName());
		user.setLastName(userEntityInput.getLastName());
		List<RoleEntity> roles = new ArrayList<>();
		if ((userEntityInput.getRoles() != null)) {
			userEntityInput.getRoles().forEach(roles::add);
		}
		user.setRoles(roles);
		return new ResponseEntity<>(this.userRepository.save(user), HttpStatus.OK);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> delete(Long id) throws BusinessException {
		this.userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
