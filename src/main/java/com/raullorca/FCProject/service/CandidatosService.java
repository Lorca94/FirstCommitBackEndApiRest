package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;

import java.util.List;
import java.util.Optional;

public interface CandidatosService {
    List<Candidato> findAll();
    void saveCandidato(String email, Candidato candidato);
    Candidato addUserToCandidato(String email, Candidato candidato);
    Candidato findByEmail(String email);
    List<Candidato>getCandidatos();
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    Optional<Candidato> findById(Long id);
    void updateCandidato(Candidato candidato);
    void relationEtiquetas(Candidato candidato, Etiqueta etiqueta);
}
