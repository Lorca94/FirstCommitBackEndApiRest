package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.AppUser;
import com.raullorca.FCProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository UserRepository){
        this.userRepository = UserRepository;
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }
    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }




}
