package com.ema.tpajedrez.services;

import com.grupo5.apitpajedrez.dominio.Partida;
import com.grupo5.apitpajedrez.dtos.PartidaDto;
import com.grupo5.apitpajedrez.entities.JugadorEntity;
import com.grupo5.apitpajedrez.entities.PartidaEntity;
import com.grupo5.apitpajedrez.repositories.PartidaRepository;
import com.grupo5.apitpajedrez.services.PartidaService;
import com.grupo5.apitpajedrez.services.impl.PartidaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartidaServiceImplTest {
    @Mock
    private PartidaRepository partidaRepository;
    @InjectMocks
    private PartidaService partidaService = new PartidaServiceImpl();
    @Mock
    private ModelMapper modelMapper;
    @Test
    public void getPartidaTest() throws Exception{

        JugadorEntity jeb = new JugadorEntity(1l, "Blancas", 0,0,0);
        JugadorEntity jen = new JugadorEntity(2l, "Negras", 0,0,0);
        PartidaEntity pe = new PartidaEntity(1l, "Primera", "", jeb, jen, 2, 3, false,"");
        when(partidaRepository.findById(org.mockito.ArgumentMatchers.any())).thenReturn(Optional.of(pe));

        Partida partida = new Partida();
        given(modelMapper.map(pe, Partida.class)).willReturn(partida);

        PartidaDto resultado = new PartidaDto();
        given(modelMapper.map(partida, PartidaDto.class)).willReturn(resultado);

        resultado = partidaService.getBBDD(1l);
        Assertions.assertNotNull(resultado);
    }

    @Test
    public void getAllTest() throws Exception{
        JugadorEntity jeb = new JugadorEntity(1l, "Blancas", 0,0,0);
        JugadorEntity jen = new JugadorEntity(2l, "Negras", 0,0,0);
        PartidaEntity pe1 = new PartidaEntity(1l, "Primera", "", jeb, jen, 2, 3, false,"");
        List<PartidaEntity> lstPE = new ArrayList<>();
        lstPE.add(pe1);

        when(partidaRepository.findAll()).thenReturn(lstPE);

        Partida partida = new Partida();
        given(modelMapper.map(pe1, Partida.class)).willReturn(partida);

        PartidaDto partidaDto = new PartidaDto();
        given(modelMapper.map(partida, PartidaDto.class)).willReturn(partidaDto);

        List<PartidaDto> resultado = partidaService.getAll();
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(resultado.size(), 1);
    }
}
