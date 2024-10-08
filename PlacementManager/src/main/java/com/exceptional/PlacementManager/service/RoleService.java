package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.entity.RoleEntity;
import com.exceptional.PlacementManager.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public RoleEntity findOrCreateRole(String roleName) {
        RoleEntity roleEntity = roleRepository.findByName(roleName);
        if (roleEntity == null) {
            RoleEntity tempRoleEntity = new RoleEntity();
            tempRoleEntity.setName(roleName);
            roleEntity = roleRepository.save(tempRoleEntity);
        }
        return roleEntity;
    }
}
