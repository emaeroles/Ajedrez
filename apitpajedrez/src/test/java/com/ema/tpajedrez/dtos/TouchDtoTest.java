package com.ema.tpajedrez.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TouchDtoTest {
    @Test
    public void testConstructor(){
        com.grupo5.apitpajedrez.dtos.TouchDto t = new com.grupo5.apitpajedrez.dtos.TouchDto(0, "A6", 2, 00);

        Assertions.assertEquals(0, t.getAccion());
        Assertions.assertEquals("A6", t.getNotAlgebraica());
        Assertions.assertEquals(2, t.getTurno());
        Assertions.assertEquals(00, t.getEstado());
    }
}
