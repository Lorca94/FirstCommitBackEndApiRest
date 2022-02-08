package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.repository.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtiquetaServiceImpl implements EtiquetaService{

    // Repositories a usar
    @Autowired
    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository){
        this.etiquetaRepository=etiquetaRepository;
    }

    // ====================Obtención de etiquetas====================
    @Override
    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    @Override
    public Etiqueta findByEtiqueta(String etiqueta) {
        return etiquetaRepository.findByEtiqueta(etiqueta);
    }
    // ====================Existencia de etiquetas====================
    @Override
    public Boolean existsByEtiqueta(String etiqueta) {
        return etiquetaRepository.existsByEtiqueta(etiqueta);
    }

    // ====================Creacción de etiquetas====================
    @Override
    public void createEtiqueta(Etiqueta etiqueta){
        etiquetaRepository.save(etiqueta);
    }


}
