package com.ema.tpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dominio.piezas.Peon;
import com.grupo5.apitpajedrez.dominio.piezas.Pieza;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PeonTest {
    @Test
    public void testMovPosibles() {

        Pieza peon = new Peon();

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

        Posicion posicion = new Posicion(5,2);

        List<int[]> movimientosPosibles = peon.movPosibles(tablero, posicion);
        Assertions.assertEquals(2, movimientosPosibles.size());
        Assertions.assertArrayEquals(new int[] {5, 3}, movimientosPosibles.get(0));

        posicion = new Posicion(5,2);
        tablero[6][3] = 34;

        movimientosPosibles = peon.movPosibles(tablero, posicion);
        Assertions.assertEquals(3, movimientosPosibles.size());

        posicion = new Posicion(5,7);
        tablero[6][6] = 24;

        movimientosPosibles = peon.movPosibles(tablero, posicion);
        Assertions.assertEquals(1, movimientosPosibles.size());

        posicion = new Posicion(5,7);
        tablero[5][6] = 0;

        movimientosPosibles = peon.movPosibles(tablero, posicion);
        Assertions.assertEquals(1, movimientosPosibles.size());
    }
}
