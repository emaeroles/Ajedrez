package com.ema.tpajedrez.controllers;

import com.grupo5.apitpajedrez.controllers.JugadorController;
import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.JugadorDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import com.grupo5.apitpajedrez.services.JugadorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JugadorControllerTest {
    @Mock
    private JugadorService jugadorService;

    @InjectMocks
    private JugadorController jugadorController;

    @Test
    public void getTest() throws Exception {
        Mockito.when(jugadorService.get(1l)).thenReturn(new Jugador());

        Jugador rta = jugadorController.get(1l);

        Assertions.assertNotNull(rta);
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(jugadorService.getAll()).thenReturn(new ArrayList<>());

        List<Jugador> rta = jugadorController.getAll();

        Assertions.assertNotNull(rta);
    }

    @Test
    public void saveTest() throws Exception {
        JugadorDto jDto = new JugadorDto();
        Mockito.when(jugadorService.save(jDto)).thenReturn(new LongResponseDto());

        LongResponseDto rta = jugadorController.save(jDto);

        Assertions.assertNotNull(rta);
    }

    @Test
    public void updateTest() throws Exception {
        JugadorDto jDto = new JugadorDto();
        Mockito.doReturn(new BoolResponseDto()).when(jugadorService).update(1l, jDto);

        BoolResponseDto rta = jugadorController.update(1l, jDto);

        Assertions.assertNotNull(rta);
    }

    @Test
    public void deleteTest() throws Exception {
        Mockito.when(jugadorService.delete(1l)).thenReturn(new BoolResponseDto());

        BoolResponseDto rta = jugadorController.delete(1l);

        Assertions.assertNotNull(rta);
    }
}
