package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Etiqueta;

import java.util.List;
import java.util.Optional;

public interface EtiquetaService {
    List<Etiqueta> findAll();
    Optional<Etiqueta> findById(Long id);
    List<Etiqueta> findByEtiqueta(String etiqueta);
    void createEtiqueta(Etiqueta etiqueta);

}
