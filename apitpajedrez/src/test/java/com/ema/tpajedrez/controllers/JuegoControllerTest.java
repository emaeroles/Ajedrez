package com.ema.tpajedrez.controllers;

import com.grupo5.apitpajedrez.controllers.JuegoController;
import com.grupo5.apitpajedrez.dtos.TouchDto;
import com.grupo5.apitpajedrez.services.JuegoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JuegoControllerTest {
    @Mock
    private JuegoService juegoService;

    @InjectMocks
    private JuegoController juegoController;

    @Test
    public void getTouchTest() throws Exception {
        Mockito.when(juegoService.getTouch("A2")).thenReturn(new TouchDto());

        TouchDto rta = juegoController.getTouch("A2");

        Assertions.assertNotNull(rta);
    }

    @Test
    public void pedidoEmpateTest() throws Exception {
        Mockito.when(juegoService.pedidoEmpate()).thenReturn(new TouchDto());

        TouchDto rta = juegoController.pedidoEmpate();

        Assertions.assertNotNull(rta);
    }

    @Test
    public void respuestaEmpateTest() throws Exception {
        Mockito.when(juegoService.respuestaEmpate(true)).thenReturn(new TouchDto());

        TouchDto rta = juegoController.respuestaEmpate(true);

        Assertions.assertNotNull(rta);
    }
}
