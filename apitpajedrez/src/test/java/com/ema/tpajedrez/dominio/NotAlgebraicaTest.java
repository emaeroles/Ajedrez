package com.ema.tpajedrez.dominio;

import com.grupo5.apitpajedrez.dominio.NotAlgebraica;
import com.grupo5.apitpajedrez.dominio.Posicion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class NotAlgebraicaTest {
    @Test
    public void convertPiezaTest() throws Exception{
        NotAlgebraica nA = new NotAlgebraica();

        Method metodo = NotAlgebraica.class.getDeclaredMethod("convertPieza", int.class);
        metodo.setAccessible(true);

        Assertions.assertEquals("P",metodo.invoke(nA,54));
        Assertions.assertEquals("T",metodo.invoke(nA,35));
        Assertions.assertEquals("C",metodo.invoke(nA,46));
        Assertions.assertEquals("A",metodo.invoke(nA,77));
        Assertions.assertEquals("D",metodo.invoke(nA,98));
        Assertions.assertEquals("R",metodo.invoke(nA,19));

    }

    @Test
    public void movAlgebraicoTest(){
        NotAlgebraica nA = new NotAlgebraica(new Posicion(3, 5), new Posicion(6, 6), 39, 124);

        Assertions.assertEquals("Rc5", nA.getPiezaPsocion());
        Assertions.assertEquals("Rc5 x Pf6 +",nA.movAlgebraico(40));

        nA = new NotAlgebraica(new Posicion(3, 5), new Posicion(6, 6), 39, 100);
        Assertions.assertEquals("Rc5 f6 #",nA.movAlgebraico(50));

        nA = new NotAlgebraica(new Posicion(3, 5), new Posicion(6, 6), 25, 139);
        Assertions.assertEquals("Tc5 x Rf6",nA.movAlgebraico(0));
    }
}
