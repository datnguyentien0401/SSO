package com.tsolution.sso._1Entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "app_role")
public class RoleEntity implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6582569587406895080L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "description")
	private String description;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany // (fetch = FetchType.EAGER)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
	private List<PermissionEntity> permissions;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany  //(fetch = FetchType.EAGER)
	@JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"))
	private List<MenuEntity> menus;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PermissionEntity> getPermissions() {
		return this.permissions;
	}

	public List<MenuEntity> getMenus() {
		return this.menus;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}

	public void setMenus(List<MenuEntity> menus) {
		this.menus = menus;
	}
	
}
