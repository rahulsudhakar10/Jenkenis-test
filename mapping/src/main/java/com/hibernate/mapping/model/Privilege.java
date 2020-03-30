package com.hibernate.mapping.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Privilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long privilegeId;

	@Column(unique = true, nullable = false, length = 50)
	private String privilegeName;

	@ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
	private Collection<Role> roles;

	public Privilege() {
		super();
	}

	public Privilege(Long privilegeId, String privilegeName, Collection<Role> roles) {
		super();
		this.privilegeId = privilegeId;
		this.privilegeName = privilegeName;
		this.roles = roles;
	}

	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	

}
