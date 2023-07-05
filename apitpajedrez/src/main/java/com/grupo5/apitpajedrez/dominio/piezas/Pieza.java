package com.grupo5.apitpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;

import java.util.List;

public abstract class Pieza {
    public List<int[]> movPosibles(int[][] tablero, Posicion posicion) {
        return null;
    }

    public void borrarPosibles(int[][] tablero, List<int[]> movimientosPosibles){
        for(int[] movimientoPosible : movimientosPosibles){
            tablero[movimientoPosible[0]][movimientoPosible[1]] -= 100;
        }

    }
}
