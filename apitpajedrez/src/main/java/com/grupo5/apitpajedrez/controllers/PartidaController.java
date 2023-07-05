package com.grupo5.apitpajedrez.controllers;

import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import com.grupo5.apitpajedrez.dtos.PartidaDto;
import com.grupo5.apitpajedrez.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("partida")
@Validated
public class PartidaController {
    @Autowired
    PartidaService partidaService;

    @GetMapping("/get-nueva")
    public PartidaDto getNueva(@RequestParam Long idB,
                               @RequestParam Long idN,
                               @RequestParam @Pattern(regexp = "^[a-zA-Z0-9_-]+$") String nombrePartida) throws Exception {
        return partidaService.getNueva(idB, idN, nombrePartida);
    }

    @GetMapping("/get-bbdd/{id}")
    public PartidaDto get(@PathVariable Long id) throws Exception {
        return partidaService.getBBDD(id);
    }

    @GetMapping("/get-all")
    public List<PartidaDto> getAll() throws Exception {
        return partidaService.getAll();
    }

    @PostMapping("/save")
    public LongResponseDto save() throws Exception {
        return partidaService.save();
    }

    @PutMapping("/update")
    public BoolResponseDto update() throws Exception {
        return partidaService.update();
    }

    @DeleteMapping("/delete/{id}")
    public BoolResponseDto delete(@PathVariable Long id) throws Exception {
        return partidaService.delete(id);
    }
}
