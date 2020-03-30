package com.hibernate.mapping.model;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthorities implements GrantedAuthority{
 
private static final long serialVersionUID = 1L;
	
	private String privilegeName;
	private Long privilegeId;
	
	public CustomGrantedAuthorities() {}
	
	public CustomGrantedAuthorities(String privilegeName, Long privilegeId) {
		super();
		this.privilegeName = privilegeName;
		this.privilegeId = privilegeId;
	}
	
	
	@Override
	public String getAuthority() {
		return privilegeName;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}
	
	

}
