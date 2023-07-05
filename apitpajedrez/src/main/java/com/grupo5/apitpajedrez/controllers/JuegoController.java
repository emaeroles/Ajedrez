package com.grupo5.apitpajedrez.controllers;

import com.grupo5.apitpajedrez.dtos.TouchDto;
import com.grupo5.apitpajedrez.services.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("juego")
@Validated
public class JuegoController {
    @Autowired
    JuegoService juegoService;

    @GetMapping("/get-touch/{posicionStr}")
    public TouchDto getTouch(@PathVariable @Pattern(regexp = "^[a-h][1-8]$",
            message = "Debe ser una coordenada de notación algebraica") String posicionStr) throws Exception {
        return juegoService.getTouch(posicionStr);
    }

    @GetMapping("/pedido-empate")
    public TouchDto pedidoEmpate() throws Exception {
        return juegoService.pedidoEmpate();
    }

    @GetMapping("/respuesta-empate/{respuesta}")
    public TouchDto respuestaEmpate(@PathVariable boolean respuesta) throws Exception {
        return juegoService.respuestaEmpate(respuesta);
    }
}
