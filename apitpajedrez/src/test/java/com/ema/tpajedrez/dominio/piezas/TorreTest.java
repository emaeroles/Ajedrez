package com.ema.tpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dominio.piezas.Pieza;
import com.grupo5.apitpajedrez.dominio.piezas.Torre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TorreTest {
    @Test
    public void testMovPosibles() {

        Pieza torre = new Torre();
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 25, 0, 0, 0, 0, 0, 34, 35, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 28, 24, 0, 0, 0, 0, 34, 38, 1},
                {1, 29, 24, 0, 0, 0, 0, 34, 39, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        Posicion posicion = new Posicion(1, 1);

        List<int[]> movimientosPosibles = torre.movPosibles(tablero, posicion);
        Assertions.assertEquals(6, movimientosPosibles.size());
        Assertions.assertArrayEquals(new int[]{1, 7}, movimientosPosibles.get(5));

        Posicion posicion2 = new Posicion(1, 8);
        tablero[1][7] = 24;

        List<int[]> movimientosPosibles2 = torre.movPosibles(tablero, posicion2);
        Assertions.assertEquals(1, movimientosPosibles2.size());
    }
}
