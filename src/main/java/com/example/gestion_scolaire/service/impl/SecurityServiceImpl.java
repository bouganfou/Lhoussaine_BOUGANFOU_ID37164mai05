package com.example.gestion_scolaire.service.impl;

import com.example.gestion_scolaire.dao.AppRoleRepository;
import com.example.gestion_scolaire.dao.AppUserRepository;
import com.example.gestion_scolaire.entities.AppRole;
import com.example.gestion_scolaire.entities.AppUser;
import com.example.gestion_scolaire.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * couche service pour la sécurité
 * */
@Service
@Slf4j
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserReository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public SecurityServiceImpl(AppUserRepository appUserReository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserReository = appUserReository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveNewUser(String username, String password, String rePwd) {
        if (!password.equals(rePwd)) throw new  RuntimeException("Password not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        AppUser savedAppUser = appUserReository.save(appUser);
        return savedAppUser;
    }
    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole != null)
            throw new RuntimeException("Role "+roleName+" already exist!!!");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserReository.findByUsername(username);
        if (appUser == null)
            throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null)
            throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserReository.findByUsername(username);
        if (appUser == null)
            throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null)
            throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserReository.findByUsername(username);
    }
}
