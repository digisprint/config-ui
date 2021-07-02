package com.liverpool.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.Roles;
import com.liverpool.configuration.service.RoleService;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping(value = "/role")
	public Roles getRoles() {
		return roleService.getRoles();
	}

	@PostMapping(value = "/role")
	public String addRoles(@RequestBody Role role) {
		System.out.println(role.getRoleName());
		return roleService.addRole(role);
	}
	
	@DeleteMapping(value = "/role/{id}")
	public void deleteRoles(@PathVariable("id") String id) {
		roleService.deleteRole(id);
	}

	@PutMapping(value = "/role")
	public String updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}

	@GetMapping(value = "/role/{id}")
	public Role getRoleById(@PathVariable("id") String id) {
		return roleService.getRoleById(id);
	}
}
