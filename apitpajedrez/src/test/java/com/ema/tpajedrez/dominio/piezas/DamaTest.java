package com.ema.tpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dominio.piezas.Dama;
import com.grupo5.apitpajedrez.dominio.piezas.Pieza;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DamaTest {
    @Test
    public void testMovPosibles() {

        Pieza reina = new Dama();
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 27, 0, 0, 0, 0, 0, 34, 37, 1},
                {1, 28, 0, 0, 0, 0, 0, 34, 38, 1},
                {1, 29, 0, 0, 0, 0, 0, 34, 39, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        Posicion posicion = new Posicion(4, 1);

        List<int[]> movimientosPosibles = reina.movPosibles(tablero, posicion);
        Assertions.assertEquals(13, movimientosPosibles.size());
        Assertions.assertArrayEquals(new int[]{1, 4}, movimientosPosibles.get(8));
    }
}
