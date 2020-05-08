package com.tsolution.sso._3service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tsolution.sso._1Entities.UserEntity;
import com.tsolution.sso._1Entities.dto.UserDetailsDto;
import com.tsolution.sso._2Repository.UserRepository;

@Service("userDetailsService")
public class LoginServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) {
		Optional<UserEntity> oUser = this.userRepository.findOneByUsername(s);
		if (!oUser.isPresent()) {
			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
		}
		UserEntity userEntity = oUser.get();
		if (Boolean.FALSE.equals(userEntity.getEnabled())) {
			throw new UsernameNotFoundException("Username is locked");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		userEntity.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			role.getPermissions().forEach(permission -> {
				authorities.add(new SimpleGrantedAuthority(permission.getUrl()));
			});
		});
		return new UserDetailsDto(userEntity.getUsername(), userEntity.getPassword(), authorities,
				userEntity.getFirstName(), userEntity.getLastName(), userEntity.getRoles());
	}
}