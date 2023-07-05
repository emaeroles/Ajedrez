package com.grupo5.apitpajedrez.controllers;

import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.JugadorDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import com.grupo5.apitpajedrez.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("jugador")
@Validated
public class JugadorController {
    @Autowired
    JugadorService jugadorService;

    @GetMapping("/get/{id}")
    public Jugador get(@PathVariable Long id) throws Exception {
        return jugadorService.get(id);
    }

    @GetMapping("/get-all")
    public List<Jugador> getAll() throws Exception {
        return jugadorService.getAll();
    }

    @PostMapping("/save")
    public LongResponseDto save(@RequestBody @Valid JugadorDto jugadorDto) throws Exception {
        return jugadorService.save(jugadorDto);
    }

    @PutMapping("/update/{id}")
    public BoolResponseDto update(@PathVariable Long id, @RequestBody JugadorDto jugadorDto) throws Exception {
        return jugadorService.update(id, jugadorDto);
    }

    @DeleteMapping("/delete/{id}")
    public BoolResponseDto delete(@PathVariable Long id) throws Exception {
        return jugadorService.delete(id);
    }
}
