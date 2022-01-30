package com.raullorca.FCProject.security.payload;


public class RegisterCandidatoRequest {
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String ciudad;
    private String pais;
    private Boolean presencialidad;
    private Boolean traslado;
    private String etiqueta;

    private String imagen;
    private String curriculum;

    private String appUserRel;


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Boolean getPresencialidad() {
        return presencialidad;
    }

    public void setPresencialidad(Boolean presencialidad) {
        this.presencialidad = presencialidad;
    }

    public Boolean getTraslado() {
        return traslado;
    }

    public void setTraslado(Boolean traslado) {
        this.traslado = traslado;
    }

    public String getEtiqueta() { return etiqueta; }

    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getAppUserRel() {
        return appUserRel;
    }

    public void setAppUserRel(String appUserRel) {
        this.appUserRel = appUserRel;
    }

}
