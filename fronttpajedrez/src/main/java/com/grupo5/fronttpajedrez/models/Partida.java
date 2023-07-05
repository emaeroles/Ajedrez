package com.grupo5.fronttpajedrez.models;

import java.util.List;

public class Partida {
    private Long id;
    private String nombre;
    private int[][] tablero;
    private Jugador jugadorBlancas;
    private Jugador jugadorNegras;
    private int turno;
    private List<String> listaDeMovimientos;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int[][] getTablero() {
        return tablero;
    }
    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    public Jugador getJugadorBlancas() {
        return jugadorBlancas;
    }
    public void setJugadorBlancas(Jugador jugadorBlancas) {
        this.jugadorBlancas = jugadorBlancas;
    }
    public Jugador getJugadorNegras() {
        return jugadorNegras;
    }
    public void setJugadorNegras(Jugador jugadorNegras) {
        this.jugadorNegras = jugadorNegras;
    }
    public int getTurno() {
        return turno;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public List<String> getListaDeMovimientos() {
        return listaDeMovimientos;
    }
    public void setListaDeMovimientos(List<String> listaDeMovimientos) {
        this.listaDeMovimientos = listaDeMovimientos;
    }
}
