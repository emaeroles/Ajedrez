package com.grupo5.apitpajedrez.dominio;

import com.grupo5.apitpajedrez.dominio.piezas.*;
import com.grupo5.apitpajedrez.dtos.TouchDto;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Juego {

    private Partida partida;
    private Pieza pieza;
    private Posicion posicionActual;
    private Posicion posicionAnterior;
    private int contenidoPosAnterior;
    private int contenidoPosActual;
    private List<int[]> movimientosPosibles;
    private boolean segundoTouch;
    private boolean detenido;
    private Jaque jaque;
    private JaqueMate jaqueMate;

    private static Juego instancia;
    public static Juego crearInstancia(Jugador jugadorBlancas, Jugador jugadorNegras, String nombrePartida){
        instancia = new Juego(jugadorBlancas, jugadorNegras, nombrePartida);
        return instancia;
    }
    public static Juego crearInstanciaBBDD(Partida partida){
        instancia = new Juego(partida);
        return instancia;
    }
    public static Juego obtenerInstancia(){
        return instancia;
    }

    private Juego(Partida partida){
        this.partida = partida;
        this.segundoTouch = false;
        this.detenido = false;
        this.jaque = new Jaque(partida.getTablero());
        this.jaqueMate = new JaqueMate(partida.getTablero());
    }
    private Juego(Jugador jugadorBlancas, Jugador jugadorNegras, String nombrePartida) {
        partida = new Partida(jugadorBlancas, jugadorNegras, nombrePartida);
        this.segundoTouch = false;
        this.detenido = false;
        this.jaque = new Jaque(partida.getTablero());
        this.jaqueMate = new JaqueMate(partida.getTablero());
    }

    public TouchDto clickCasillero(Posicion posicion) {
        contenidoPosActual = partida.getContenidoEscaque(posicion);
        posicionActual = posicion;
        if(partida.isJuegoFinalizado()){
            return new TouchDto(0, partida.getTurno(), 99);
        } else if(detenido){
            return new TouchDto(0, partida.getTurno(), 90);
        } else if (contenidoPosActual / 10 == partida.getTurno()) {
            if (segundoTouch) {
                pieza.borrarPosibles(partida.getTablero(), movimientosPosibles);
            }
            pieza = getPieza(contenidoPosActual);
            movimientosPosibles = pieza.movPosibles(partida.getTablero(), posicionActual);
            posicionAnterior = posicionActual;
            contenidoPosAnterior = partida.getContenidoEscaque(posicionActual);
            segundoTouch = true;
            NotAlgebraica notAlgebraica = new NotAlgebraica(posicionAnterior, posicionActual, contenidoPosAnterior, contenidoPosActual);
            return new TouchDto(1, notAlgebraica.getPiezaPsocion(), partida.getTurno(), 10);
        } else if (contenidoPosActual >= 100) {
            NotAlgebraica notAlgebraica = new NotAlgebraica(posicionAnterior, posicionActual, contenidoPosAnterior, contenidoPosActual);
            pieza.borrarPosibles(partida.getTablero(), movimientosPosibles);
            hacerMovimiento(posicionActual, posicionAnterior);
            segundoTouch = false;
            if (jaque.verJaque(partida.getTurno()).size() != 0 && contenidoPosAnterior / 10 == partida.getTurno()) {
                volverMovimiento(posicionAnterior, posicionActual);
                movimientosPosibles = pieza.movPosibles(partida.getTablero(), posicionAnterior);
                segundoTouch = true;
                return new TouchDto(3, posicionActual.getNotAlgebraica(), partida.getTurno(), 10);
            } else if (jaque.verJaque(partida.getNoTurno()).size() != 0) {
                if(jaqueMate.verJaqueMate(partida.getTurno(), partida.getNoTurno())){
                    partida.cambiarTurno();
                    finConJaqueMate();
                    partida.getListaDeMovimientos().add(notAlgebraica.movAlgebraico(50));
                    return new TouchDto(2, notAlgebraica.movAlgebraico(50), partida.getTurno(), 50);
                } else {
                    partida.cambiarTurno();
                    partida.getListaDeMovimientos().add(notAlgebraica.movAlgebraico(40));
                    return new TouchDto(2, notAlgebraica.movAlgebraico(40), partida.getTurno(), 40);
                }
            } else {
                partida.cambiarTurno();
                partida.getListaDeMovimientos().add(notAlgebraica.movAlgebraico(2));
                return new TouchDto(2, notAlgebraica.movAlgebraico(2), partida.getTurno(), 10);
            }
        } else if(!segundoTouch && contenidoPosActual == 0) {
            return new TouchDto(6, posicionActual.getNotAlgebraica(), partida.getTurno(), 10);
        } else if(!segundoTouch && contenidoPosActual % 10 != partida.getTurno()) {
            return new TouchDto(7, posicionActual.getNotAlgebraica(), partida.getTurno(), 10);
        } else {
            return new TouchDto(3, posicionActual.getNotAlgebraica(), partida.getTurno(), 10);
        }
    }

    public TouchDto pedidoEmpate(){
        if(getPartida().isJuegoFinalizado()){
            return new TouchDto(0, Juego.obtenerInstancia().getPartida().getTurno(), 99);
        } else {
            setDetenido(true);
            return new TouchDto(0, Juego.obtenerInstancia().getPartida().getTurno(), 00);
        }
    }

    public TouchDto respuestaEmpate(boolean respuesta){
        if(respuesta && isDetenido() && !getPartida().isJuegoFinalizado()){
            finConEmpate();
            return new TouchDto(0, Juego.obtenerInstancia().getPartida().getTurno(), 11);
        } else if(getPartida().isJuegoFinalizado()) {
            return new TouchDto(0, Juego.obtenerInstancia().getPartida().getTurno(), 99);
        } else {
            setDetenido(false);
            return new TouchDto(0, Juego.obtenerInstancia().getPartida().getTurno(), 10);
        }
    }

    public void hacerMovimiento(Posicion posicionActual, Posicion posicionVieja){
        partida.setContenidoEscaque(posicionActual, contenidoPosAnterior);
        partida.setContenidoEscaque(posicionVieja, 0);
    }
    public void volverMovimiento(Posicion posicionVieja, Posicion posicionActual){
        partida.setContenidoEscaque(posicionVieja, contenidoPosAnterior);
        partida.setContenidoEscaque(posicionActual, contenidoPosActual);
    }

    public Pieza getPieza(int numPieza){
        Pieza pieza = null;
        int opcion = numPieza % 10;
        switch (opcion){
            case 4:
                pieza = new Peon();
                break;
            case 5:
                pieza = new Torre();
                break;
            case 6:
                pieza = new Caballo();
                break;
            case 7:
                pieza = new Alfil();
                break;
            case 8:
                pieza = new Dama();
                break;
            case 9:
                pieza = new Rey();
                break;
        }
        return pieza;
    }

    private void finConJaqueMate(){
        if(partida.getNoTurno() == 2){
            partida.getJugadorBlancas().sumarPartidaGanada();
            partida.getJugadorNegras().sumarPartidaPerdida();
            partida.setJuegoFinalizado(true);
        } else {
            partida.getJugadorNegras().sumarPartidaGanada();
            partida.getJugadorBlancas().sumarPartidaPerdida();
            partida.setJuegoFinalizado(true);
        }
    }

    public void finConEmpate(){
        partida.getJugadorBlancas().sumarPartidaEmpatada();
        partida.getJugadorNegras().sumarPartidaEmpatada();
        partida.setJuegoFinalizado(true);
    }
}
