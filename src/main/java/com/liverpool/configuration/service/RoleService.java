package com.liverpool.configuration.service;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.Roles;

public interface RoleService {
	
	Roles getRoles();
	String addRole(Role role);
	void deleteRole(String id);
	String updateRole(Role role);
	Role getRoleById(String id);
	
}
