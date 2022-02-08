package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.AppUser;
import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.repository.CandidatosRepository;
import com.raullorca.FCProject.repository.EtiquetaRepository;
import com.raullorca.FCProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatosServiceImpl implements CandidatosService {

    //Repositories a usar
    private final CandidatosRepository candidatosRepository;
    private final UserService userService;
    private final EtiquetaRepository etiquetaRepository;

    //Constructor
    public CandidatosServiceImpl(CandidatosRepository candidatosRepository, UserService userService,
                                 EtiquetaRepository etiquetaRepository) {
        this.candidatosRepository = candidatosRepository;
        this.userService = userService;
        this.etiquetaRepository = etiquetaRepository;
    }

    // ====================Obtención de candidatos====================
    @Override
    public List<Candidato> findAll() {
        return candidatosRepository.findAll();
    }

    @Override
    public List<Candidato> getCandidatos() {
        return candidatosRepository.findAll();
    }

    @Override
    public Optional<Candidato> findById(Long id) {
        return candidatosRepository.findById(id);
    }

    @Override
    public Candidato findByEmail(String email) {
        return candidatosRepository.findByEmail(email);
    }

    // ====================Existencia de candidatos====================
    @Override
    public boolean existsById(Long id) {
        return candidatosRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return candidatosRepository.existsByEmail(email);
    }

    // ====================Guardado y eliminado de candidatos====================
    @Override
    public void saveCandidato(Candidato candidato) {
        candidatosRepository.save(candidato);
    }

    @Override
    public void deleteById(Long id) {
        candidatosRepository.deleteById(id);
    }

    // ====================Relación entre entidades candidatos-entidad====================
    @Override
    public void addEtiquetaToCandidato(Candidato candidato, Etiqueta etiqueta) throws Exception {
        candidato.addEtiqueta(etiqueta);
    }

    @Override
    public Candidato addUserToCandidato(String emailUser, Candidato candidato) {
        AppUser user = userService.findByEmail(emailUser);
        candidato.setAppUserRel(user);
        return candidato;
    }
}
