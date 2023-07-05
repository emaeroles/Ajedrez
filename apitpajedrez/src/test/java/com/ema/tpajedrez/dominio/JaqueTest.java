package com.ema.tpajedrez.dominio;

import com.grupo5.apitpajedrez.dominio.Jaque;
import com.grupo5.apitpajedrez.dominio.Posicion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JaqueTest {
    @Test
    public void verJaqueTest(){
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 25, 0, 0, 0, 0, 0, 34, 35, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 28, 24, 0, 0, 0, 0, 34, 0, 1},
                {1, 29, 0, 0, 0, 0, 0, 38, 39, 1},
                {1, 0, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        Jaque jaque = new Jaque(tablero);
        List<Posicion> resVerJaque = jaque.verJaque(2);

        Assertions.assertEquals(1, resVerJaque.size());

        Posicion esperado = new Posicion(5, 7);
        Posicion resultado = new Posicion(resVerJaque.get(0).getColumn(), resVerJaque.get(0).getRow());
        Assertions.assertArrayEquals(new int[] {esperado.getColumn(), esperado.getRow()}, new int[] {resultado.getColumn(), resultado.getRow()});

        //caballo negro
        tablero[4][3] = 36;
        jaque = new Jaque(tablero);
        resVerJaque = jaque.verJaque(2);

        Assertions.assertEquals(2, resVerJaque.size());

        //caballo blanco
        tablero[4][3] = 26;
        jaque = new Jaque(tablero);
        resVerJaque = jaque.verJaque(3);

        Assertions.assertEquals(0, resVerJaque.size());


        //alfil
        tablero[6][2] = 0;
        tablero[7][3] = 37;
        jaque = new Jaque(tablero);
        resVerJaque = jaque.verJaque(2);

        Assertions.assertEquals(2, resVerJaque.size());

        // peon
        tablero[6][7] = 24;
        jaque = new Jaque(tablero);
        resVerJaque = jaque.verJaque(3);

        Assertions.assertEquals(1, resVerJaque.size());

        // peon
        tablero[6][2] = 34;
        jaque = new Jaque(tablero);
        resVerJaque = jaque.verJaque(2);

        Assertions.assertEquals(2, resVerJaque.size());

        // rey negro
        tablero[6][2] = 39;
        jaque = new Jaque(tablero);
        resVerJaque = jaque.verJaque(2);

        Assertions.assertEquals(2, resVerJaque.size());

        // rey blanco
        tablero[6][7] = 29;
        jaque = new Jaque(tablero);
        resVerJaque = jaque.verJaque(3);

        Assertions.assertEquals(1, resVerJaque.size());
    }
}
