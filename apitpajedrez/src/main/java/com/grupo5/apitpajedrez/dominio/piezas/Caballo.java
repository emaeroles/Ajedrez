package com.grupo5.apitpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Caballo extends Pieza {
    @Override
    public List<int[]> movPosibles(int[][] tablero, Posicion posicion) {
        List<int[]> movimientosPosibles = new ArrayList<>();
        int[] movimientoPosible;
        int c = posicion.getColumn();
        int r = posicion.getRow();
        int turno = tablero[c][r] / 10;

        int[] x = { 1,1,-1,0,0, 1,-3,-1,1, 0, 0,-1};
        int[] y = {-1,0,-1,3,1,-1, 0, 0,1,-3,-1, 1};

        for (int i = 0; i < x.length; i++) {
            c += x[i];
            r += y[i];

            if (tablero[c][r] == 1 && (i == 0 || i == 3 || i == 6 || i == 9)) {
                for(int j = 0; j < 2; j++){
                    i++;
                    c += x[i];
                    r += y[i];
                }
            } else {
                if (tablero[c][r] == 0 && i != 0 && i != 3 && i != 6 && i != 9) {
                    tablero[c][r] += 100;
                    movimientoPosible = new int[]{c, r};
                    movimientosPosibles.add(movimientoPosible);
                } else if (tablero[c][r] / 10 == 2 && turno == 3 && i != 0 && i != 3 && i != 6 && i != 9) {
                    tablero[c][r] += 100;
                    movimientoPosible = new int[]{c, r};
                    movimientosPosibles.add(movimientoPosible);
                } else if (tablero[c][r] / 10 == 3 && turno == 2 && i != 0 && i != 3 && i != 6 && i != 9) {
                    tablero[c][r] += 100;
                    movimientoPosible = new int[]{c, r};
                    movimientosPosibles.add(movimientoPosible);
                }
            }
        }
        return movimientosPosibles;
    }
}
