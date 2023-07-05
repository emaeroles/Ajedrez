package com.grupo5.apitpajedrez.services;

import com.grupo5.apitpajedrez.dtos.TouchDto;
import org.springframework.stereotype.Service;

@Service
public interface JuegoService {
    TouchDto getTouch(String posicionStr) throws Exception;
    TouchDto pedidoEmpate() throws Exception;
    TouchDto respuestaEmpate(boolean respuesta) throws Exception;
}
