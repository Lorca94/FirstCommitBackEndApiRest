package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.entity.RelatedCanEt;
import com.raullorca.FCProject.repository.CandidatosRepository;
import com.raullorca.FCProject.repository.EtiquetaRepository;
import com.raullorca.FCProject.repository.RelatedCanEtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatedCanEtServiceImpl implements RelatedCanEtService {

    @Autowired
    RelatedCanEtRepository repo;
    @Autowired
    CandidatosRepository candidatosRepository;
    @Autowired
    EtiquetaRepository etiquetaRepository;

    public RelatedCanEtServiceImpl (RelatedCanEtRepository repo){
        this.repo = repo;

    }
    @Override
    public List<RelatedCanEt> findAll(){
        return repo.findAll();
    }

    @Override
    public Optional<RelatedCanEt>findById(Long id){
        return repo.findById(id);
    }
    @Override
    public List<RelatedCanEt> findByCandidatoId(Candidato candidato) {
        return null;
    }

    @Override
    public List<RelatedCanEt> findByEtiquetaId(Etiqueta etiquetaId) {
        return repo.findByEtiquetaId(etiquetaId);
    }

    @Override
    public RelatedCanEt addRelation(RelatedCanEt relationIds, Candidato candidato, Etiqueta etiqueta) {
        relationIds.setCandidato(candidato);
        relationIds.setEtiqueta(etiqueta);
        return relationIds;
    }

    @Override
    public void createRelation(Candidato candidato, Etiqueta etiqueta){
        RelatedCanEt newRelation = new RelatedCanEt();
        newRelation.setEtiqueta(etiqueta);
        newRelation.setCandidato(candidato);
        repo.save(newRelation);
    }

}
