package com.ema.tpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dominio.piezas.Caballo;
import com.grupo5.apitpajedrez.dominio.piezas.Pieza;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CaballoTest {
    @Test
    public void testMovPosibles() {

        Pieza caballo = new Caballo();
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

        Posicion posicion = new Posicion(2, 1);

        List<int[]> movimientosPosibles = caballo.movPosibles(tablero, posicion);
        Assertions.assertEquals(2, movimientosPosibles.size());
        Assertions.assertArrayEquals(new int[]{3, 3}, movimientosPosibles.get(0));
        Assertions.assertArrayEquals(new int[]{1, 3}, movimientosPosibles.get(1));

        posicion = new Posicion(2, 1);
        tablero[1][3] = 34;

        movimientosPosibles = caballo.movPosibles(tablero, posicion);
        Assertions.assertEquals(1, movimientosPosibles.size());

        posicion = new Posicion(2, 8);
        tablero[1][6] = 24;

        movimientosPosibles = caballo.movPosibles(tablero, posicion);
        Assertions.assertEquals(2, movimientosPosibles.size());
    }
}
