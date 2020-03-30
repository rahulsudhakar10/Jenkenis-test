package com.hibernate.mapping.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long roleId;
	
	@Column(unique=true,nullable=false,length=50)
	private String roleName;
	
	@ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
	private Collection<User> users;
	
	/* While Fetching Role Object then one role entry gets repeated based on privileges assigned.So Used @Fetch 
	@Fetch(FetchMode.SUBSELECT)*/
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_privilege_mapping", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "roleId"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "privilegeId"))
	private Collection<Privilege> privileges;

	public Role() {
		super();
	}

	public Role(Long roleId, String roleName, Collection<User> users, Collection<Privilege> privileges) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
		this.privileges = privileges;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

	
}
