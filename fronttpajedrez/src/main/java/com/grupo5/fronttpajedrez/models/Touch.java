package com.grupo5.fronttpajedrez.models;

public class Touch {
    private int accion;
    private String notAlgebraica;
    private int turno;
    private int estado;

    public Touch() { }

    public Touch(int accion, int turno, int estado){
        this.accion = accion;
        this.notAlgebraica = "";
        this.turno = turno;
        this.estado = estado;
    }
    public Touch(int accion, String notAlgebraica, int turno, int estado) {
        this.accion = accion;
        this.notAlgebraica = notAlgebraica;
        this.turno = turno;
        this.estado = estado;
    }

    public int getAccion() { return accion; }
    public void setAccion(int accion) {
        this.accion = accion;
    }
    public String getNotAlgebraica() {
        return notAlgebraica;
    }
    public void setNotAlgebraica(String notAlgebraica) {
        this.notAlgebraica = notAlgebraica;
    }
    public int getTurno() {
        return turno;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
}

