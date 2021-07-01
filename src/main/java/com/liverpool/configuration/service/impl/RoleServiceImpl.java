package com.liverpool.configuration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.Roles;
import com.liverpool.configuration.repository.RoleRepository;
import com.liverpool.configuration.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository; 
	
	@Override
	public Roles getRoles() {
	List<Role> roleList=roleRepository.findAll();
	Roles roles = new Roles();
	roles.setRoles(roleList);
	
	return roles;
	}
	
	@Override
	public String addRole(Role role) {
		roleRepository.insert(role);
		return "Added Roles Succesfully";
	}
	@Override
	public void deleteRole(String id) {
		roleRepository.deleteById(id);
	}
	@Override
	public String updateRole(Role role) {
		roleRepository.save(role);
		return "role updated successfully";
	}
	public Role getRoleById(String id) {
		return roleRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO ROLE FOUND WITH ID:"+id));
	}
	
	public String getAllRoles(String id) {
		roleRepository.findAll();
		return "found all roles";
	}
	
	
}
