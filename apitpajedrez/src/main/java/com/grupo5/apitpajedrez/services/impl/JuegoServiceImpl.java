package com.grupo5.apitpajedrez.services.impl;

import com.grupo5.apitpajedrez.dominio.Juego;
import com.grupo5.apitpajedrez.dominio.Posicion;
import com.grupo5.apitpajedrez.dtos.TouchDto;
import com.grupo5.apitpajedrez.services.JuegoService;
import com.grupo5.apitpajedrez.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuegoServiceImpl implements JuegoService {
    @Autowired
    JugadorService jugadorService;

    @Override
    public TouchDto getTouch(String posicionStr) throws Exception {
        if(Juego.obtenerInstancia() == null){
            throw new Exception("Debe iniciar una partida nueva o guardada");
        }
        Posicion posicion = new Posicion(posicionStr);
        TouchDto touch = Juego.obtenerInstancia().clickCasillero(posicion);
        if(touch.getEstado() == 50){
            try{
                jugadorService.updateDatos(Juego.obtenerInstancia().getPartida().getJugadorBlancas());
                jugadorService.updateDatos(Juego.obtenerInstancia().getPartida().getJugadorNegras());
            } catch (Exception ex) {
                throw new Exception("La base de datos Fallo", ex);
            }
        }
        return touch;
    }

    @Override
    public TouchDto pedidoEmpate() throws Exception {
        if(Juego.obtenerInstancia() == null){
            throw new Exception("Debe iniciar una partida nueva o guardada");
        }
        return Juego.obtenerInstancia().pedidoEmpate();
    }

    @Override
    public TouchDto respuestaEmpate(boolean respuesta) throws Exception {
        if(Juego.obtenerInstancia() == null){
            throw new Exception("Debe iniciar una partida nueva o guardada");
        }
        TouchDto touch = Juego.obtenerInstancia().respuestaEmpate(respuesta);
        if(touch.getEstado() == 11){
            try{
                jugadorService.updateDatos(Juego.obtenerInstancia().getPartida().getJugadorBlancas());
                jugadorService.updateDatos(Juego.obtenerInstancia().getPartida().getJugadorNegras());
            } catch (Exception ex) {
                throw new Exception("La base de datos Fallo", ex);
            }
        }
        return touch;
    }
}
