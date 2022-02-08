package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;

import java.util.List;
import java.util.Optional;

public interface CandidatosService {
    List<Candidato> findAll();
    void saveCandidato(Candidato candidato);
    Candidato addUserToCandidato(String email, Candidato candidato);
    Candidato findByEmail(String email);
    List<Candidato>getCandidatos();
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    Optional<Candidato> findById(Long id);
    void addEtiquetaToCandidato(Candidato candidato, Etiqueta etiqueta) throws Exception;
    void deleteById(Long id);
}
