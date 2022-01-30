package com.raullorca.FCProject.entity;

import javax.persistence.*;

@Table(name = "RelatedTable")
@Entity
public class RelatedCanEt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relación con candidato
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidatos")
    private Candidato candidato;

    //Relación con etiquetas
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "etiquetas")

    private Etiqueta etiqueta;

    public RelatedCanEt(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidatoId) {
        this.candidato = candidatoId;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }
}
