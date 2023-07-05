package com.ema.tpajedrez.dominio;


import com.grupo5.apitpajedrez.dominio.Juego;
import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.dominio.Partida;
import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dominio.piezas.*;
import com.grupo5.apitpajedrez.dtos.TouchDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class JuegoTest {

    @Test
    public void instanciasTest(){
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();

        Partida partida = new Partida(j1, j2, "partidaTest");

        Juego j = Juego.crearInstancia(j1, j2, "partidaTest");
        Assertions.assertEquals(Juego.obtenerInstancia(), j);

        j.setPartida(partida);

        Assertions.assertEquals(Juego.obtenerInstancia().getPartida(), Juego.crearInstanciaBBDD(partida).getPartida());
    }

    @Test
    public void pedidoEmpateTest(){
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();

        Juego j = Juego.crearInstancia(j1, j2, "partidaTest");
        TouchDto t = new TouchDto(0, 2, 00);

        Assertions.assertEquals(t.getEstado(), j.pedidoEmpate().getEstado());
        Assertions.assertEquals(t.getAccion(), j.pedidoEmpate().getAccion());
        Assertions.assertEquals(t.getTurno(), j.pedidoEmpate().getTurno());

        t.setEstado(99);
        j.getPartida().setJuegoFinalizado(true);

        Assertions.assertEquals(t.getEstado(), j.pedidoEmpate().getEstado());
        Assertions.assertEquals(t.getAccion(), j.pedidoEmpate().getAccion());
        Assertions.assertEquals(t.getTurno(), j.pedidoEmpate().getTurno());
    }

    @Test
    public void rtaPedidoEmpateTest(){
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();

        Juego j = Juego.crearInstancia(j1, j2, "partidaTest");
        TouchDto t = new TouchDto(0, 3, 10);

        j.pedidoEmpate();
        j.getPartida().cambiarTurno();

        TouchDto touchFalse = j.respuestaEmpate(false);

        Assertions.assertEquals(t.getEstado(), touchFalse.getEstado());
        Assertions.assertEquals(t.getAccion(), touchFalse.getAccion());
        Assertions.assertEquals(t.getTurno(), touchFalse.getTurno());

        j.pedidoEmpate();
        j.getPartida().cambiarTurno();
        t.setEstado(11);
        t.setTurno(2);

        TouchDto touchTrue = j.respuestaEmpate(true);

        Assertions.assertEquals(t.getEstado(), touchTrue.getEstado());
        Assertions.assertEquals(t.getAccion(), touchTrue.getAccion());
        Assertions.assertEquals(t.getTurno(), touchTrue.getTurno());

        t.setEstado(99);
        t.setTurno(3);
        j.getPartida().cambiarTurno();
        TouchDto touch3 = j.respuestaEmpate(true);

        Assertions.assertEquals(t.getEstado(), touch3.getEstado());
        Assertions.assertEquals(t.getAccion(), touch3.getAccion());
        Assertions.assertEquals(t.getTurno(), touch3.getTurno());
    }

    @Test
    public void testGetPiea(){
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();

        Juego j = Juego.crearInstancia(j1, j2, "partidaTest");


        Assertions.assertEquals(new Peon().getClass(), j.getPieza(54).getClass());
        Assertions.assertEquals(new Torre().getClass(), j.getPieza(25).getClass());
        Assertions.assertEquals(new Caballo().getClass(), j.getPieza(36).getClass());
        Assertions.assertEquals(new Alfil().getClass(), j.getPieza(37).getClass());
        Assertions.assertEquals(new Dama().getClass(), j.getPieza(68).getClass());
        Assertions.assertEquals(new Rey().getClass(), j.getPieza(29).getClass());
    }

    @Test
    public void movimientosTest(){
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();

        Juego j = Juego.crearInstancia(j1, j2, "partidaTest");

        j.setContenidoPosAnterior(24);
        j.hacerMovimiento(new Posicion(5, 2), new Posicion(5, 3));
        Assertions.assertEquals(0, j.getPartida().getContenidoEscaque(new Posicion(5, 3)));

        j.volverMovimiento(new Posicion(5, 2), new Posicion(5, 3));
        Assertions.assertEquals(24, j.getPartida().getContenidoEscaque(new Posicion(5, 2)));
    }

    @Test
    public void finConJaqueMTest() throws Exception{
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();

        Juego j = Juego.crearInstancia(j1, j2, "partidaTest");

        Method metodo = Juego.class.getDeclaredMethod("finConJaqueMate");
        metodo.setAccessible(true);
        metodo.invoke(j);

        Assertions.assertTrue(j.getPartida().isJuegoFinalizado());

        j.getPartida().cambiarTurno();

        metodo = Juego.class.getDeclaredMethod("finConJaqueMate");
        metodo.setAccessible(true);
        metodo.invoke(j);

        Assertions.assertTrue(j.getPartida().isJuegoFinalizado());
    }


    @Test
    public void clickCasilleroTest(){
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();

        Juego j = Juego.crearInstancia(j1, j2, "partidaTest");

        Posicion p = new Posicion(3, 3);
        TouchDto tD = new TouchDto(6, 2, 10);

        Assertions.assertEquals(tD.getEstado() ,j.clickCasillero(p).getEstado());
        Assertions.assertEquals(tD.getAccion() ,j.clickCasillero(p).getAccion());
        Assertions.assertEquals(tD.getTurno() ,j.clickCasillero(p).getTurno());

        p = new Posicion(2, 2);
        Assertions.assertEquals(10 ,j.clickCasillero(p).getEstado());
        Assertions.assertEquals(1 ,j.clickCasillero(p).getAccion());
        Assertions.assertEquals(2 ,j.clickCasillero(p).getTurno());
        Assertions.assertEquals("Pb2" ,j.clickCasillero(p).getNotAlgebraica());

        j.setDetenido(true);
        Assertions.assertEquals(90 ,j.clickCasillero(p).getEstado());
        Assertions.assertEquals(0 ,j.clickCasillero(p).getAccion());
        Assertions.assertEquals(2 ,j.clickCasillero(p).getTurno());

        j.getPartida().setJuegoFinalizado(true);
        Assertions.assertEquals(99 ,j.clickCasillero(p).getEstado());
        Assertions.assertEquals(0 ,j.clickCasillero(p).getAccion());
        Assertions.assertEquals(2 ,j.clickCasillero(p).getTurno());

        Juego ju2 = Juego.crearInstancia(j1, j2, "partidaTest");
        ju2.getPartida().setContenidoEscaque(p, 100);

        ju2.clickCasillero(new Posicion(2, 1));

        Assertions.assertEquals(10 ,ju2.clickCasillero(p).getEstado());
        Assertions.assertEquals(7 ,ju2.clickCasillero(p).getAccion());
        Assertions.assertEquals("b2" ,ju2.clickCasillero(p).getNotAlgebraica());
        Assertions.assertEquals(3 ,ju2.clickCasillero(p).getTurno());

        ju2.getPartida().setContenidoEscaque(p, 100);


        ju2.getPartida().setContenidoEscaque(new Posicion(4, 8), 0);
        ju2.getPartida().setContenidoEscaque(new Posicion(5, 7), 0);
        ju2.getPartida().setContenidoEscaque(new Posicion(5, 6), 28);

        p = new Posicion(1, 7);
        ju2.getPartida().setContenidoEscaque(p, 134);
        ju2.getPartida().setTurno(3);
        ju2.clickCasillero(new Posicion(1, 8));

        Assertions.assertEquals(10 ,ju2.clickCasillero(p).getEstado());
        Assertions.assertEquals(3 ,ju2.clickCasillero(p).getAccion());
        Assertions.assertEquals("a7" ,ju2.clickCasillero(p).getNotAlgebraica());
        Assertions.assertEquals(3 ,ju2.clickCasillero(p).getTurno());


        ju2.getPartida().setContenidoEscaque(new Posicion(4, 5), 28);
        ju2.getPartida().setTurno(2);
        ju2.getPartida().setNoTurno(3);
        ju2.clickCasillero(new Posicion(4, 5));
        p = new Posicion(5, 6);
        ju2.getPartida().setContenidoEscaque(p, 100);


        Assertions.assertEquals(40 ,ju2.clickCasillero(p).getEstado());
        Assertions.assertEquals(7 ,ju2.clickCasillero(p).getAccion());
        Assertions.assertEquals("e6" ,ju2.clickCasillero(p).getNotAlgebraica());
        Assertions.assertEquals(3 ,ju2.clickCasillero(p).getTurno());


        ju2.getPartida().setContenidoEscaque(new Posicion(4, 8), 34);
        ju2.getPartida().setContenidoEscaque(new Posicion(5, 8), 39);
        ju2.getPartida().setContenidoEscaque(new Posicion(6, 8), 34);
        ju2.getPartida().setContenidoEscaque(new Posicion(5, 7), 0);
        ju2.getPartida().setContenidoEscaque(new Posicion(5, 6), 0);
        ju2.getPartida().setContenidoEscaque(new Posicion(4, 5), 28);

        ju2.getPartida().setTurno(2);
        ju2.getPartida().setNoTurno(3);
        ju2.clickCasillero(new Posicion(4, 5));
        p = new Posicion(5, 5);
        ju2.getPartida().setContenidoEscaque(p, 100);


        Assertions.assertEquals(50 ,ju2.clickCasillero(p).getEstado());
        Assertions.assertEquals(0 ,ju2.clickCasillero(p).getAccion());
        Assertions.assertEquals("" ,ju2.clickCasillero(p).getNotAlgebraica());
        Assertions.assertEquals(3 ,ju2.clickCasillero(p).getTurno());



        Juego ju3 = Juego.crearInstancia(j1, j2, "partidaTest");
        ju3.clickCasillero(new Posicion(2, 1));

        p = new Posicion(2, 2);
        ju3.getPartida().setContenidoEscaque(p, 0);

        Assertions.assertEquals(10 ,ju3.clickCasillero(p).getEstado());
        Assertions.assertEquals(3 ,ju3.clickCasillero(p).getAccion());
        Assertions.assertEquals("b2" ,ju3.clickCasillero(p).getNotAlgebraica());
        Assertions.assertEquals(2 ,ju3.clickCasillero(p).getTurno());
    }
}
