package com.example.demo.controller;

import com.example.demo.dto.Role;
import com.example.demo.dto.RoleInput;
import com.example.demo.service.RoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleServiceImpl roleServiceImpl;

    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        var roles = roleServiceImpl.findAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        var role = roleServiceImpl.findRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody RoleInput roleInput) {
        var role = roleServiceImpl.addRole(roleInput);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        var role1 = roleServiceImpl.updateRole(role);
        return new ResponseEntity<>(role1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleServiceImpl.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
