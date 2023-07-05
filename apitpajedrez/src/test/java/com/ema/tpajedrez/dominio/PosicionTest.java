package com.ema.tpajedrez.dominio;

import com.grupo5.apitpajedrez.dominio.Posicion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class PosicionTest {

    @Test
    public void testDivision() {

        Posicion posicion = new Posicion("a3");
        Assertions.assertEquals(1, posicion.getColumn());
        Assertions.assertEquals(3, posicion.getRow());

        posicion = new Posicion("b2");
        Assertions.assertEquals(2, posicion.getColumn());
        Assertions.assertEquals(2, posicion.getRow());

        posicion = new Posicion("c4");
        Assertions.assertEquals(3, posicion.getColumn());
        Assertions.assertEquals(4, posicion.getRow());

        posicion = new Posicion("d7");
        Assertions.assertEquals(4, posicion.getColumn());
        Assertions.assertEquals(7, posicion.getRow());

        posicion = new Posicion("e6");
        Assertions.assertEquals(5, posicion.getColumn());
        Assertions.assertEquals(6, posicion.getRow());

        posicion = new Posicion("f2");
        Assertions.assertEquals(6, posicion.getColumn());
        Assertions.assertEquals(2, posicion.getRow());

        posicion = new Posicion("g6");
        Assertions.assertEquals(7, posicion.getColumn());
        Assertions.assertEquals(6, posicion.getRow());

        posicion = new Posicion("h7");
        Assertions.assertEquals(8, posicion.getColumn());
        Assertions.assertEquals(7, posicion.getRow());
    }

    @Test
    public void testJuntar() throws Exception{

        Posicion posicion = new Posicion();
        posicion.setColumn(4);
        posicion.setRow(2);

        Method metodo = Posicion.class.getDeclaredMethod("juntar");
        metodo.setAccessible(true);
        metodo.invoke(posicion);

        Assertions.assertEquals ("d2", posicion.getNotAlgebraica());
        posicion = new Posicion("f5", 6, 5);
        Assertions.assertEquals("f5", posicion.getNotAlgebraica());
    }

}
