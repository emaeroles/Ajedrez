package com.grupo5.apitpajedrez.services.impl;

import com.grupo5.apitpajedrez.dominio.Juego;
import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.JugadorDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import com.grupo5.apitpajedrez.entities.JugadorEntity;
import com.grupo5.apitpajedrez.entities.PartidaEntity;
import com.grupo5.apitpajedrez.repositories.JugadorRepository;
import com.grupo5.apitpajedrez.repositories.PartidaRepository;
import com.grupo5.apitpajedrez.services.JugadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Jugador get(Long id) throws Exception {
        JugadorEntity je;
        try{
            je = jugadorRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            throw new Exception("La base de datos Fallo", ex);
        }
        if(je != null){
            return modelMapper.map(je, Jugador.class);
        } else {
            throw new Exception("Jugador inexistente");
        }
    }

    @Override
    public List<Jugador> getAll() throws Exception {
        List<JugadorEntity> lje;
        try{
            lje = jugadorRepository.findAll();
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
        List<Jugador> lj = new ArrayList<>();
        for(JugadorEntity je : lje){
            lj.add(modelMapper.map(je, Jugador.class));
        }
        return lj;
    }

    @Override
    public LongResponseDto save(JugadorDto jugadorDto) throws Exception {
        try {
            JugadorEntity je = modelMapper.map(jugadorDto, JugadorEntity.class);
            return new LongResponseDto(jugadorRepository.save(je).getId());
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
    }

    @Override
    public BoolResponseDto update(Long id, JugadorDto jugadorDto) throws Exception {
        JugadorEntity je;
        try {
            je = jugadorRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            throw new Exception("La base de datos Fallo", ex);
        }
        if(je != null){
            je = modelMapper.map(jugadorDto, JugadorEntity.class);
            je.setId(id);
            jugadorRepository.save(je);
            return new BoolResponseDto(true);
        } else {
            throw new Exception("Jugador inexistente");
        }
    }

    @Override
    public void updateDatos(Jugador jugador) throws Exception {
        JugadorEntity je;
        try{
            je = modelMapper.map(jugador, JugadorEntity.class);
            jugadorRepository.save(je);
        } catch (Exception ex) {
            throw new Exception("La base de datos Fallo", ex);
        }

    }

    @Override
    public BoolResponseDto delete(Long id) throws Exception {
        JugadorEntity je;
        List<PartidaEntity> lpe;
        try {
            je = jugadorRepository.findById(id).orElse(null);
            lpe = partidaRepository.findAll();
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
        if (je != null) {
            if(Juego.obtenerInstancia() != null){
                if(Juego.obtenerInstancia().getPartida().getJugadorNegras().getId() == id ||
                        Juego.obtenerInstancia().getPartida().getJugadorBlancas().getId() == id){
                    throw new Exception("El jugador no se puede eliminar porque está en la partida actual");
                }
            }
            for(PartidaEntity pe : lpe){
                if(pe.getJugadorBlancas().getId() == je.getId() || pe.getJugadorNegras().getId() == je.getId()){
                    throw new Exception("El jugador no se puede eliminar porque está en un partida " + pe.getId());
                }
            }
            jugadorRepository.deleteById(id);
            return new BoolResponseDto(true);
        } else {
            throw new Exception("Jugador inexistente");
        }
    }
}
