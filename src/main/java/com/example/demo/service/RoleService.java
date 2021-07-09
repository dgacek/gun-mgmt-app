package com.example.demo.service;

import com.example.demo.dto.Role;
import com.example.demo.dto.RoleInput;
import com.example.demo.entity.RoleEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role addRole(RoleInput roleInput) {
        var roleEntity = roleRepo.save(new RoleEntity(roleInput.getName()));
        return new Role(roleEntity);
    }

    public List<Role> findAllRoles() {
        var roleEntities = roleRepo.findAll();
        var roles = new ArrayList<Role>();
        for (RoleEntity roleEntity: roleEntities) {
            roles.add(new Role(roleEntity));
        }
        return roles;
    }

    public Role updateRole(Role role) {
        var roleEntity = roleRepo.findById(role.getId())
                .orElseThrow(() -> new IdNotFoundException(("Role of id:"+role.getId()+" could not be found in the database")));
        roleEntity.setName(role.getName());
        roleRepo.save(roleEntity);
        return new Role(roleEntity);
    }

    public Role findRoleById(Long id) {
        return new Role(roleRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException(("Role of id:"+id+" could not be found in the database"))));
    }

    @Transactional
    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }
}
