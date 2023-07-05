package com.grupo5.apitpajedrez.services;

import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.JugadorDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JugadorService {
    Jugador get(Long id) throws Exception;
    List<Jugador> getAll() throws Exception;
    LongResponseDto save(JugadorDto jugadorDto) throws Exception;
    BoolResponseDto update(Long id, JugadorDto jugadorDto) throws Exception;
    void updateDatos(Jugador jugador) throws Exception;
    BoolResponseDto delete(Long id) throws Exception;
}
