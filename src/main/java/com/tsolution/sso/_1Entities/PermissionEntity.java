package com.tsolution.sso._1Entities;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "app_permission")
@Entity
public class PermissionEntity implements Serializable {

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

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "description")
	private String description;

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
