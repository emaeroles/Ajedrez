package com.grupo5.apitpajedrez.services.impl;

import com.grupo5.apitpajedrez.dominio.Juego;
import com.grupo5.apitpajedrez.dominio.Jugador;
import com.grupo5.apitpajedrez.dominio.Partida;
import com.grupo5.apitpajedrez.dtos.BoolResponseDto;
import com.grupo5.apitpajedrez.dtos.LongResponseDto;
import com.grupo5.apitpajedrez.dtos.PartidaDto;
import com.grupo5.apitpajedrez.entities.JugadorEntity;
import com.grupo5.apitpajedrez.entities.PartidaEntity;
import com.grupo5.apitpajedrez.repositories.JugadorRepository;
import com.grupo5.apitpajedrez.repositories.PartidaRepository;
import com.grupo5.apitpajedrez.services.PartidaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaServiceImpl implements PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PartidaDto getNueva(Long idBlancas, Long idNegras, String nombrePartida) throws Exception {
        if(idBlancas == idNegras){
            throw new Exception("El jugador no puede repetirse");
        }
        JugadorEntity jugadorEntityB;
        JugadorEntity jugadorEntityN;
        try{
            jugadorEntityB = jugadorRepository.findById(idBlancas).orElse(null);
            jugadorEntityN = jugadorRepository.findById(idNegras).orElse(null);
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
        if(jugadorEntityB != null && jugadorEntityN != null){
            Jugador jugadorBlancas = modelMapper.map(jugadorEntityB, Jugador.class);
            Jugador jugadorNegras = modelMapper.map(jugadorEntityN, Jugador.class);
            Partida partida = Juego.crearInstancia(jugadorBlancas, jugadorNegras, nombrePartida).getPartida();
            PartidaDto partidaDto = modelMapper.map(partida, PartidaDto.class);
            return partidaDto;
        } else {
            throw new Exception("Jugador o jugadores no existentes");
        }
    }

    @Override
    public PartidaDto getBBDD(Long id) throws Exception {
        PartidaEntity pe;
        try{
            pe = partidaRepository.findById(id).orElse(null);
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
        if(pe != null){
            Partida partida = modelMapper.map(pe, Partida.class);
            partida.formJson();
            PartidaDto partidaDto = modelMapper.map(Juego.crearInstanciaBBDD(partida).getPartida(), PartidaDto.class);
            return partidaDto;
        } else {
            throw new Exception("Partida inexistente");
        }
    }

    @Override
    public List<PartidaDto> getAll() throws Exception {
        List<PartidaEntity> lpe;
        try{
            lpe = partidaRepository.findAll();
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
        List<PartidaDto> lp = new ArrayList<>();
        for(PartidaEntity pe : lpe){
            Partida partida = modelMapper.map(pe, Partida.class);
            partida.formJson();
            PartidaDto partidaDto = modelMapper.map(partida, PartidaDto.class);
            lp.add(partidaDto);
        }
        return lp;
    }

    @Override
    public LongResponseDto save() throws Exception {
        if(Juego.obtenerInstancia() == null){
            throw new Exception("Partida inexistente");
        }
        if(Juego.obtenerInstancia().getPartida().getId() != null){
            throw new Exception("La partida ya existe");
        }
        Partida partida = Juego.obtenerInstancia().getPartida();
        partida.toJson();
        PartidaEntity pe = modelMapper.map(partida, PartidaEntity.class);
        try{
            pe = partidaRepository.save(pe);
            Juego.obtenerInstancia().getPartida().setId(pe.getId());
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
        return new LongResponseDto(pe.getId());
    }

    @Override
    public BoolResponseDto update() throws Exception {
        if(Juego.obtenerInstancia() == null){
            throw new Exception("Partida inexistente");
        }
        if(Juego.obtenerInstancia().getPartida().getId() == null){
            throw new Exception("Primero debe guardar la partida");
        }
        Partida partida = Juego.obtenerInstancia().getPartida();
        partida.toJson();
        PartidaEntity pe = modelMapper.map(partida, PartidaEntity.class);
        try{
            partidaRepository.save(pe);
            return new BoolResponseDto(true);
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
    }

    @Override
    public BoolResponseDto delete(Long id) throws Exception {
        PartidaEntity pe;
        try {
            pe = partidaRepository.findById(id).orElse(null);
        } catch (Exception ex){
            throw new Exception("La base de datos Fallo", ex);
        }
        if(pe != null){
            if(pe.getId() == Juego.obtenerInstancia().getPartida().getId()){
                throw new Exception("No se puede eliminar, partida en juego");
            }
            partidaRepository.deleteById(id);
            return new BoolResponseDto(true);
        } else {
            throw new Exception("Partida inexistente");
        }
    }
}
