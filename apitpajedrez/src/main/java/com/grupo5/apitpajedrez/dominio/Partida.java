package com.grupo5.apitpajedrez.dominio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partida {
    private Long id;
    private String nombre;
    private int[][] tablero;
    private String tableroSrt;
    private Jugador jugadorBlancas;
    private Jugador jugadorNegras;
    private int turno;
    private int noTurno;
    private boolean juegoFinalizado;
    private List<String> listaDeMovimientos;
    private String listaDeMovimientosSrt;

    public Partida(Jugador jugadorBlancas, Jugador jugadorNegras, String nombrePartida){
        this.nombre = nombrePartida;
        tablero = new int[10][10];
        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        turno = 2;
        noTurno = 3;
        juegoFinalizado = false;
        listaDeMovimientos = new ArrayList<>();
        iniciarTablero();
        colocarPiezas();
    }

    public int getContenidoEscaque(Posicion posicion){
        return tablero[posicion.getColumn()][posicion.getRow()];
    }
    public void setContenidoEscaque(Posicion posicion, int valor){
        tablero[posicion.getColumn()][posicion.getRow()] = valor;
    }

    public void cambiarTurno(){
        if(turno == 2){
            turno = 3;
            noTurno = 2;
        } else {
            turno = 2;
            noTurno = 3;
        }
    }

    private void iniciarTablero(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i == 0 || j == 0 || i == 9 || j == 9){
                    tablero[i][j] = 1;
                }
            }
        }
    }

    private void colocarPiezas(){

        for (int i = 0; i < 2; i++) {
            int t;
            int p;
            if(i == 0){
                t = 2;
                p = 24;
            } else {
                t = 7;
                p = 34;
            }

            for (int j = 1; j <= 8; j++) {
                tablero[j][t] = p;
            }
        }

        tablero[1][1] = 25;
        tablero[2][1] = 26;
        tablero[3][1] = 27;
        tablero[4][1] = 28;
        tablero[5][1] = 29;
        tablero[6][1] = 27;
        tablero[7][1] = 26;
        tablero[8][1] = 25;

        tablero[1][8] = 35;
        tablero[2][8] = 36;
        tablero[3][8] = 37;
        tablero[4][8] = 38;
        tablero[5][8] = 39;
        tablero[6][8] = 37;
        tablero[7][8] = 36;
        tablero[8][8] = 35;
    }

    public void toJson(){
        Gson gson = new Gson();
        tableroSrt = gson.toJson(tablero);
        listaDeMovimientosSrt = gson.toJson(listaDeMovimientos);
    }

    public void formJson(){
        Gson gson = new Gson();
        tablero = gson.fromJson(tableroSrt, int[][].class);
        listaDeMovimientos = gson.fromJson(listaDeMovimientosSrt, new TypeToken<List<String>>() {}.getType());
    }
}
