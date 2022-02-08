package com.raullorca.FCProject.repository;

import com.raullorca.FCProject.entity.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
    Optional<Etiqueta> findById(Long id);
    Etiqueta findByEtiqueta(String etiqueta);
    boolean existsByEtiqueta(String etiqueta);

}
