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
public class CandidatosServiceImpl implements CandidatosService{

    private final CandidatosRepository candidatosRepository;
    private final UserService userService;
    private final EtiquetaRepository etiquetaRepository;


    public CandidatosServiceImpl(CandidatosRepository candidatosRepository, UserService userService,
                                 EtiquetaRepository etiquetaRepository){
        this.candidatosRepository=candidatosRepository;
        this.userService = userService;
        this.etiquetaRepository = etiquetaRepository;
    }

    @Override
    public List<Candidato> findAll(){
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
    public void updateCandidato(Candidato candidato) {

    }

    @Override
    public void relationEtiquetas(Candidato candidato, Etiqueta etiqueta) {

    }


    @Override
    public boolean existsById(Long id) {
        return candidatosRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return candidatosRepository.existsByEmail(email);
    }

    @Override
    public Candidato findByEmail(String email) {
        return candidatosRepository.findByEmail(email);
    }

    @Override
    public Candidato addUserToCandidato(String emailUser, Candidato candidato) {
        AppUser user = userService.findByEmail(emailUser);
        candidato.setAppUserRel(user);
        return candidato;
    }

    @Override
    public void saveCandidato(String emailUser, Candidato candidato) {
        Candidato newCandidato = addUserToCandidato(emailUser, candidato);
        candidatosRepository.save(newCandidato);
    }
}
