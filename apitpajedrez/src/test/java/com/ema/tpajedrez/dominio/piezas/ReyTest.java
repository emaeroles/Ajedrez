package com.ema.tpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dominio.piezas.Pieza;
import com.grupo5.apitpajedrez.dominio.piezas.Rey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReyTest {
    @Test
    public void testMovPosibles() {

        Pieza rey = new Rey();
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 28, 24, 0, 0, 0, 0, 34, 38, 1},
                {1, 29, 24, 0, 0, 0, 0, 34, 39, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        Posicion posicion = new Posicion(5,1);

        List<int[]> movimientosPosibles = rey.movPosibles(tablero, posicion);
        Assertions.assertEquals(0, movimientosPosibles.size());

        tablero[5][2] = 34;

        List<int[]> movimientosPosibles2 = rey.movPosibles(tablero, posicion);
        Assertions.assertEquals(1, movimientosPosibles2.size());

        tablero[5][7] = 24;
        Posicion posicion2 = new Posicion(5,8);

        List<int[]> movimientosPosibles3 = rey.movPosibles(tablero, posicion2);
        Assertions.assertEquals(1, movimientosPosibles3.size());
    }
}
