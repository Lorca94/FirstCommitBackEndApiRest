package com.raullorca.FCProject.repository;

import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.entity.RelatedCanEt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedCanEtRepository extends JpaRepository<RelatedCanEt,Long> {
    List<RelatedCanEt> findAll();
    List<RelatedCanEt> findByCandidatoId(Candidato candidato);
    List<RelatedCanEt> findByEtiquetaId(Etiqueta etiqueta);


}
