package com.grupo5.apitpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Alfil extends Pieza{
    @Override
    public List<int[]> movPosibles(int[][] tablero, Posicion posicion){
        List<int[]> movimientosPosibles = new ArrayList<>();
        int[] movimientoPosible;
        int c = posicion.getColumn();
        int r = posicion.getRow();
        int turno = tablero[c][r] / 10;

        int[] x = {-1, -1, 1, 1};
        int[] y = {-1, 1, -1, 1};

        for (int i = 0; i < x.length; i++) {
            c = posicion.getColumn();
            r = posicion.getRow();

            do {
                c += x[i];
                r += y[i];
                if (tablero[c][r] == 0) {
                    tablero[c][r] += 100;
                    movimientoPosible = new int[] {c,r};
                    movimientosPosibles.add(movimientoPosible);
                } else if (tablero[c][r] / 10 == 2) {
                    if(turno == 3){
                        tablero[c][r] += 100;
                        movimientoPosible = new int[] {c,r};
                        movimientosPosibles.add(movimientoPosible);
                        break;
                    } else {
                        break;
                    }
                } else if (tablero[c][r] / 10 == 3) {
                    if(turno == 2){
                        tablero[c][r] += 100;
                        movimientoPosible = new int[] {c,r};
                        movimientosPosibles.add(movimientoPosible);
                        break;
                    } else {
                        break;
                    }
                }

            } while (tablero[c][r] != 1);
        }
        return movimientosPosibles;
    }
}
