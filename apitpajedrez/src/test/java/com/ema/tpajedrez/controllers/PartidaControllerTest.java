package com.ema.tpajedrez.controllers;

import com.grupo5.apitpajedrez.controllers.PartidaController;
import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import com.grupo5.apitpajedrez.dtos.PartidaDto;
import com.grupo5.apitpajedrez.services.PartidaService;
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
public class PartidaControllerTest {
    @Mock
    private PartidaService partidaService;

    @InjectMocks
    private PartidaController partidaController;

    @Test
    public void getNuevaTest() throws Exception {
        Mockito.when(partidaService.getNueva(1l, 2l, "partidaPrueba")).thenReturn(new PartidaDto());
        PartidaDto rta = partidaController.getNueva(1l, 2l, "partidaPrueba");
        Assertions.assertNotNull(rta);
    }
    @Test
    public void getTest() throws Exception {
        Mockito.when(partidaService.getBBDD(1l)).thenReturn(new PartidaDto());
        PartidaDto rta = partidaController.get(1l);
        Assertions.assertNotNull(rta);
    }
    @Test
    public void getAllTest() throws Exception {
        Mockito.when(partidaService.getAll()).thenReturn(new ArrayList<>());
        List<PartidaDto> rta = partidaController.getAll();
        Assertions.assertNotNull(rta);
    }
    @Test
    public void saveTest() throws Exception {
        PartidaDto pDto = new PartidaDto();
        Mockito.when(partidaService.save()).thenReturn(new LongResponseDto());

        LongResponseDto rta = partidaController.save();

        Assertions.assertNotNull(rta);
    }
    @Test
    public void updateTest() throws Exception {
        PartidaDto pDto = new PartidaDto();
        Mockito.doReturn(new BoolResponseDto()).when(partidaService).update();

        BoolResponseDto rta = partidaController.update();

        Assertions.assertNotNull(rta);
    }

    @Test
    public void deleteTest() throws Exception {
        Mockito.when(partidaService.delete(1l)).thenReturn(new BoolResponseDto());

        BoolResponseDto rta = partidaController.delete(1l);

        Assertions.assertNotNull(rta);
    }
}
