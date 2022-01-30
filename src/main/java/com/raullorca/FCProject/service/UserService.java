package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<AppUser> findAll();
    Optional<AppUser> findById(Long id);
    AppUser findByEmail(String email);
    AppUser getUser(String username);
    AppUser saveUser(AppUser user);
    boolean existsByEmail(String email);

}
