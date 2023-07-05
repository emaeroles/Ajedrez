package com.grupo5.fronttpajedrez.models;

public class Jugador {

    private Long id;
    private String nickname;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }
    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }
    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }
    public int getPartidasEmpatadas() {
        return partidasEmpatadas;
    }
    public void setPartidasEmpatadas(int partidasEmpatadas) {
        this.partidasEmpatadas = partidasEmpatadas;
    }
}
