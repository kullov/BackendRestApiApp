package com.backend.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "App_Role")
public class AppRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Role_Id", nullable = false)
	private int role_id;

	@Column(name = "Role_Name", length = 30, nullable = false)
	private String roleName;
	
//	@ManyToMany( mappedBy = "roles")
//    private Set<AppUser> users;

	public AppRole() {
		super();
	}

	public AppRole(int roleId, String roleName) {
		super();
		this.role_id = roleId;
		this.roleName = roleName;
	}
	public AppRole(String roleName) {
		super();
		this.roleName = roleName;
	}


	public int getRole_id() {
		return role_id;
	}

//	public void setRole_id(int role_id) {
//		this.role_id = role_id;
//	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String role_name) {
		this.roleName = role_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public Set<AppUser> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<AppUser> users) {
//		this.users = users;
//	}

	@Override
	public String toString() {
		return roleName;
	}
	
	
}
