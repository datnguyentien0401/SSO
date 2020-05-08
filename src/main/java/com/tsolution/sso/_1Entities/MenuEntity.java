package com.tsolution.sso._1Entities;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "app_menu")
@Entity
public class MenuEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -575415150358637616L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, nullable = false)
	private Long id;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "url")
	private String url;

	@Column(name = "code")
	private String code;

	@Column(name = "app_type")
	private String appType;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_menu_id", referencedColumnName = "id")
	private MenuEntity parentMenu;

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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAppType() {
		return this.appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public MenuEntity getParentMenu() {
		return this.parentMenu;
	}

	public void setParentMenu(MenuEntity parentMenu) {
		this.parentMenu = parentMenu;
	}

}
