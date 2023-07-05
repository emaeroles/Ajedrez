package com.ema.tpajedrez.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JaqueMateTest {
    @Test
    public void verJaqueMateTest(){
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 25, 0, 0, 0, 0, 0, 34, 35, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 24, 24, 0, 0, 0, 0, 34, 0, 1},
                {1, 29, 0, 0, 0, 0, 0, 38, 39, 1},
                {1, 24, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        com.grupo5.apitpajedrez.dominio.JaqueMate jaqueM = new com.grupo5.apitpajedrez.dominio.JaqueMate(tablero);
        boolean resVerJaqueM = jaqueM.verJaqueMate(3, 2);

        Assertions.assertEquals(true, resVerJaqueM);
    }
}
