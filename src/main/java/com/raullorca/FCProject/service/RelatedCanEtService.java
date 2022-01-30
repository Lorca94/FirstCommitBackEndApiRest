package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.entity.RelatedCanEt;
import com.raullorca.FCProject.repository.RelatedCanEtRepository;

import java.util.List;
import java.util.Optional;

public interface RelatedCanEtService {
    List<RelatedCanEt> findAll();
    Optional<RelatedCanEt> findById(Long id);
    List<RelatedCanEt> findByCandidatoId(Candidato candidato);
    List<RelatedCanEt> findByEtiquetaId(Etiqueta etiquetaId);

    RelatedCanEt addRelation(RelatedCanEt relationIds, Candidato candidato, Etiqueta etiqueta);
    void createRelation(Candidato candidato, Etiqueta etiqueta);
}
