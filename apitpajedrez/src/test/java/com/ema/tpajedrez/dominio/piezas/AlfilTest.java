package com.ema.tpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dominio.piezas.Alfil;
import com.grupo5.apitpajedrez.dominio.piezas.Pieza;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AlfilTest {
    @Test
    public void testMovPosibles() {

        Pieza alfil = new Alfil();
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 28, 24, 0, 0, 0, 0, 34, 38, 1},
                {1, 29, 24, 0, 0, 0, 0, 34, 39, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 26, 0, 24, 0, 0, 0, 34, 36, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        Posicion posicion = new Posicion(6,1);

        List<int[]> movimientosPosibles = alfil.movPosibles(tablero, posicion);
        Assertions.assertEquals(2, movimientosPosibles.size());
        Assertions.assertArrayEquals(new int[] {8, 3}, movimientosPosibles.get(1));

        Posicion posicion2 = new Posicion(6,8);
        tablero[7][7] = 24;

        List<int[]> movimientosPosibles2 = alfil.movPosibles(tablero, posicion2);
        Assertions.assertEquals(1, movimientosPosibles2.size());

        Posicion posicion3 = new Posicion(6,1);
        tablero[7][2] = 34;

        List<int[]> movimientosPosibles3 = alfil.movPosibles(tablero, posicion3);
        Assertions.assertEquals(1, movimientosPosibles3.size());
    }
}
