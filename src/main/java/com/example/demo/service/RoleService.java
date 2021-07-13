package com.example.demo.service;

import com.example.demo.model.dto.Role;
import com.example.demo.model.dto.RoleInput;

import java.util.List;

public interface RoleService {
    Role addRole(RoleInput roleInput);
    List<Role> findAllRoles();
    Role updateRole(Role role);
    Role findRoleById(Long id);
    void deleteRole(Long id);
}
