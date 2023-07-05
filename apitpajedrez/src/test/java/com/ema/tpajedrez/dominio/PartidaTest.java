package com.ema.tpajedrez.dominio;

import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.dominio.Partida;
import com.grupo5.apitpajedrez.dominio.Posicion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class PartidaTest {
    private Partida p;

    @BeforeEach
    public void setUp(){
        p = new Partida();
    }

    @Test
    public void cambiarTurnoTest(){
        p.setTurno(2);
        p.cambiarTurno();

        Assertions.assertEquals(3, p.getTurno());

        p.cambiarTurno();
        Assertions.assertEquals(2, p.getTurno());
    }

    @Test
    public void inicioTableroTest() throws Exception {
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        p.setTablero(new int[10][10]);

        Method metodo = Partida.class.getDeclaredMethod("iniciarTablero");
        metodo.setAccessible(true);
        metodo.invoke(p);

        for (int i = 0; i < p.getTablero().length; i++) {
            for (int j = 0; j < p.getTablero().length; j++) {
                Assertions.assertEquals(tablero[i][j], p.getContenidoEscaque(new Posicion(i, j)));
            }
        }

    }

    @Test
    public void colocarPiezasTest() throws Exception {
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

        p.setTablero(new int[10][10]);

        Method inicio = Partida.class.getDeclaredMethod("iniciarTablero");
        inicio.setAccessible(true);
        inicio.invoke(p);


        Method colocar = Partida.class.getDeclaredMethod("colocarPiezas");
        colocar.setAccessible(true);
        colocar.invoke(p);


        for (int i = 0; i < p.getTablero().length; i++) {
            for (int j = 0; j < p.getTablero().length; j++) {
                Assertions.assertEquals(tablero[i][j], p.getTablero()[i][j]);
            }
        }

    }

    @Test
    public void argsConstructorTest(){
        int[][] tablero = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 27, 24, 0, 0, 24, 0, 34, 37, 1},
                {1, 28, 24, 0, 0, 0, 0, 34, 38, 1},
                {1, 29, 24, 0, 0, 0, 0, 34, 39, 1},
                {1, 27, 24, 0, 0, 0, 0, 34, 37, 1},
                {1, 26, 24, 0, 0, 0, 0, 34, 36, 1},
                {1, 25, 24, 0, 0, 0, 0, 34, 35, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();
        Partida partida = new Partida(j1, j2,"partidaTest");
        partida.setContenidoEscaque(new Posicion(3, 5), 24);

        Assertions.assertEquals(2 ,partida.getTurno());
        Assertions.assertEquals(3 ,partida.getNoTurno());
        Assertions.assertEquals(j1 ,partida.getJugadorBlancas());
        Assertions.assertEquals(j2 ,partida.getJugadorNegras());
        Assertions.assertEquals(new ArrayList<>(), partida.getListaDeMovimientos());
        Assertions.assertEquals("partidaTest" ,partida.getNombre());

        for (int i = 0; i < partida.getTablero().length; i++) {
            for (int j = 0; j < partida.getTablero().length; j++) {
                Assertions.assertEquals(tablero[i][j], partida.getTablero()[i][j]);
            }
        }
    }

    @Test
    public void probarJson(){
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();
        Partida partida = new Partida(j1, j2,"partidaTest");

        partida.toJson();
        Assertions.assertNotNull(partida.getTableroSrt());
        Assertions.assertNotNull(partida.getListaDeMovimientosSrt());

        partida.formJson();
        Assertions.assertNotNull(partida.getListaDeMovimientos());
        Assertions.assertNotNull(partida.getTablero());
    }

}
