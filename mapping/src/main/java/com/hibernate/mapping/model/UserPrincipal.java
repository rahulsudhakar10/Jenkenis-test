package com.hibernate.mapping.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserPrincipal implements UserDetails{

	private Long id;

    private String name;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
	

	public UserPrincipal(Long id, String name, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	
	 public static UserPrincipal create(User user) {
	        return new UserPrincipal(
	        		 user.getId(),
	                 user.getName(),
	                 user.getUsername(),
	                 user.getEmail(),
	                 user.getPassword(),
	                 getAuthorities(user.getRoles()));
	        		
	 }	       		

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		List<Privilege> privileges = getPrivileges(roles);
		return privileges.stream().map(privilege -> new CustomGrantedAuthorities(privilege.getPrivilegeName(),
				privilege.getPrivilegeId())).collect(Collectors.toList());
	}

	private static List<Privilege> getPrivileges(Collection<Role> roles) {
		List<Collection<Privilege>> privilegeList = roles.stream().map(Role::getPrivileges).collect(Collectors.toList());
		return privilegeList.stream().flatMap(Collection::stream).distinct().collect(Collectors.toList());
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


	@Override
	public String toString() {
		return "UserPrincipal [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", authorities=" + authorities + "]";
	}
	

}
