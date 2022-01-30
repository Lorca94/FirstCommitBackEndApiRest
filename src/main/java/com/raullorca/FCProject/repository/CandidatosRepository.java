package com.raullorca.FCProject.repository;

import com.raullorca.FCProject.entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatosRepository extends JpaRepository<Candidato,Long> {
    Optional<Candidato> findById(Long id);
    Candidato findByEmail(String email);
    Boolean existsByEmail(String email);
}
