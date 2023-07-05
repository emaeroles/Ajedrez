package com.ema.tpajedrez.services;

import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.entities.JugadorEntity;
import com.grupo5.apitpajedrez.repositories.JugadorRepository;
import com.grupo5.apitpajedrez.services.JugadorService;
import com.grupo5.apitpajedrez.services.impl.JugadorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class JugadorServiceImplTest {
    @Mock
    private JugadorRepository jugadorRepository;
    @InjectMocks
    private JugadorService jugadorService = new JugadorServiceImpl();
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void getJugadorTest() throws Exception{
        JugadorEntity je = new JugadorEntity(1l, "Nombre", 0,0,0);
        when(jugadorRepository.findById(org.mockito.ArgumentMatchers.any())).thenReturn(Optional.of(je));
        Jugador resultado = new Jugador();
        given(modelMapper.map(je, Jugador.class)).willReturn(resultado);
        resultado = jugadorService.get(1l);
        Assertions.assertNotNull(resultado);
    }
    @Test
    public void getAllTest() throws Exception{
        JugadorEntity je1 = new JugadorEntity(1l, "Nombre1", 0,0,0);
        JugadorEntity je2 = new JugadorEntity(2l, "Nombre2", 0,0,0);
        List<JugadorEntity> lstJE = new ArrayList<>();
        lstJE.add(je1);
        lstJE.add(je2);
        when(jugadorRepository.findAll()).thenReturn(lstJE);
        List<Jugador> resultado = new ArrayList<>();

        Jugador j = new Jugador();
        given(modelMapper.map(je1, Jugador.class)).willReturn(j);
        resultado = jugadorService.getAll();

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(resultado.size(), 2);
    }
}
