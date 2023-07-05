package com.grupo5.apitpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Rey extends Pieza {
    @Override
    public List<int[]> movPosibles(int[][] tablero, Posicion posicion) {
        List<int[]> movimientosPosibles = new ArrayList<>();
        int[] movimientoPosible;
        int c = posicion.getColumn();
        int r = posicion.getRow();
        int turno = tablero[c][r] / 10;

        int[] x = {0, 0, -1, 1, -1, -1, 1, 1};
        int[] y = {-1, 1, 0, 0, -1, 1, -1, 1};

        for (int i = 0; i < x.length; i++) {
            c = posicion.getColumn();
            r = posicion.getRow();

            c += x[i];
            r += y[i];

            if (tablero[c][r] == 0) {
                tablero[c][r] += 100;
                movimientoPosible = new int[]{c, r};
                movimientosPosibles.add(movimientoPosible);
            } else if (tablero[c][r] / 10 == 2 && turno == 3) {
                tablero[c][r] += 100;
                movimientoPosible = new int[]{c, r};
                movimientosPosibles.add(movimientoPosible);
            } else if (tablero[c][r] / 10 == 3 && turno == 2) {
                tablero[c][r] += 100;
                movimientoPosible = new int[]{c, r};
                movimientosPosibles.add(movimientoPosible);
            }
        }
        return movimientosPosibles;
    }
}
