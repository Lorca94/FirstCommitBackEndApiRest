package com.raullorca.FCProject.repository;
import com.raullorca.FCProject.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findById(Long id);
    AppUser findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
}
