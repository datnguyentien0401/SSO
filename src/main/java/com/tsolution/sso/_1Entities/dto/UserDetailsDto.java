package com.tsolution.sso._1Entities.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.tsolution.sso._1Entities.RoleEntity;

public class UserDetailsDto extends User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6333363935488363408L;
	private String firstName;
	private String lastName;
	private List<RoleEntity> roles;

	public UserDetailsDto(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String fistName, String lastName, List<RoleEntity> roles) {
		super(username, password, authorities);
		this.firstName = fistName;
		this.lastName = lastName;
		this.roles = roles;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public List<RoleEntity> getRoles() {
		return this.roles;
	}

}
