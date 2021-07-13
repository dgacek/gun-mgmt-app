package com.example.demo.service.implementation;

import com.example.demo.model.dto.Role;
import com.example.demo.model.dto.RoleInput;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.mapper.RoleMapper;
import com.example.demo.model.repo.RoleRepo;
import com.example.demo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final RoleMapper roleMapper;

    public Role addRole(RoleInput roleInput) {
        return roleMapper.toRole(roleRepo.save(new RoleEntity(null, roleInput.getName())));
    }

    public List<Role> findAllRoles() {
        return roleMapper.toRoleList(roleRepo.findAll());
    }

    @Transactional
    public Role updateRole(Role role) {
        RoleEntity roleEntity = roleRepo.findById(role.getId())
                .orElseThrow(() -> new IdNotFoundException(("Role of id:"+role.getId()+" could not be found in the database")));
        roleEntity.setName(role.getName());
        return roleMapper.toRole(roleEntity);
    }

    public Role findRoleById(Long id) {
        return roleMapper.toRole(roleRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException(("Role of id:"+id+" could not be found in the database"))));
    }

    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }
}
