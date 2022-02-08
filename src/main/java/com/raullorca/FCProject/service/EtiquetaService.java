package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Etiqueta;

import java.util.List;
import java.util.Optional;

public interface EtiquetaService {
    List<Etiqueta> findAll();
    Etiqueta findByEtiqueta(String etiqueta);
    void createEtiqueta(Etiqueta etiqueta);
    Boolean existsByEtiqueta(String etiqueta);

}
