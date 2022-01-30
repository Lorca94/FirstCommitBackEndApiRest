package com.raullorca.FCProject.service;

import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.repository.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtiquetaServiceImpl implements EtiquetaService{

    @Autowired
    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository){
        this.etiquetaRepository=etiquetaRepository;
    }

    @Override
    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    @Override
    public Optional<Etiqueta> findById(Long id) {
        return etiquetaRepository.findById(id);
    }

    @Override
    public List<Etiqueta> findByEtiqueta(String etiqueta) {
        List<Etiqueta> etiquetas = etiquetaRepository.findByEtiqueta(etiqueta);
        if (!etiquetas.isEmpty()){
            return etiquetas;
        }
        return null;
    }

    @Override
    public void createEtiqueta(Etiqueta etiqueta){
        etiquetaRepository.save(etiqueta);
    }
}
