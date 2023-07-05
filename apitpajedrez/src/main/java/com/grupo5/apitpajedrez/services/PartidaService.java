package com.grupo5.apitpajedrez.services;

import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import com.grupo5.apitpajedrez.dtos.PartidaDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PartidaService {
    PartidaDto getNueva(Long idBlancas, Long idNegras, String nombrePartida) throws Exception;
    PartidaDto getBBDD(Long id) throws Exception;
    List<PartidaDto> getAll() throws Exception;
    LongResponseDto save() throws Exception;
    public BoolResponseDto update() throws Exception;
    BoolResponseDto delete(Long id) throws Exception;
}
