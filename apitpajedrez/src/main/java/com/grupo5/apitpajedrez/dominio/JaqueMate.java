package com.grupo5.apitpajedrez.dominio;

import com.grupo5.apitpajedrez.dominio.piezas.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JaqueMate {

    private int[][] tablero;
    private int turno;
    private int noTurno;
    private Posicion posicionRey;


    public JaqueMate(int[][] tablero) {
        this.tablero = tablero;
        this.turno = 0;
        this.noTurno = 0;
        this.posicionRey = null;
    }

    public boolean verJaqueMate(int turno, int noTurno){

        this.turno = turno;
        this.noTurno = noTurno;

        Jaque jaque = new Jaque(tablero);
        posicionRey = buscarReyTurno(tablero, noTurno);

        if(verSiEscapa()){
            return false;
        }

        if(jaque.verJaque(noTurno).size() == 1){
            Posicion amenaza = jaque.verJaque(noTurno).get(0);

            if(verSiSeCome(amenaza)){
                return false;
            }
            if(verSiSeTapa(amenaza)){
                return false;
            }
        }
        return true;
    }

    private Posicion buscarReyTurno(int[][] tablero, int noTurno){
        for(int i = 1; i < 9; i++){
            for(int j = 0; j < 10; j++){
                if(tablero[i][j] / 10 == noTurno && tablero[i][j] % 10 == 9){
                    return new Posicion(i, j);
                }
            }
        }
        return null;
    }

    private boolean verSiEscapa(){

        int c;
        int r;

        int[] x = {0, 0, -1, 1, -1, -1, 1, 1};
        int[] y = {-1, 1, 0, 0, -1, 1, -1, 1};

        for (int i = 0; i < x.length; i++) {
            c = posicionRey.getColumn();
            r = posicionRey.getRow();

            c += x[i];
            r += y[i];

            int[] posicion = new int[] {c,r};

            if(tablero[c][r] == 0){
                if(!damaTorreAlfil(posicion, false) && !caballos(posicion) && !peones(posicion)){
                    return true;
                }
            }
        }
        return false;
    }


    private boolean verSiSeCome(Posicion amenaza){
        List<int[]> posiblesAmenazas;

        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                posiblesAmenazas = new ArrayList<>();
                if(tablero[i][j] / 10 == noTurno && (tablero[i][j] % 10 == 4 || tablero[i][j] % 10 == 5 ||
                        tablero[i][j] % 10 == 6 || tablero[i][j] % 10 == 7 || tablero[i][j] % 10 == 8 ||
                        tablero[i][j] % 10 == 9)){
                    posiblesAmenazas = posiblesAmenazas(i, j);
                }

                for(int[] posibleAmenaza : posiblesAmenazas){
                    if(posibleAmenaza[0] == amenaza.getColumn() && posibleAmenaza[1] == amenaza.getRow()){
                        quePieza(4).borrarPosibles(tablero, posiblesAmenazas);
                        return true;
                    }
                }
                quePieza(4).borrarPosibles(tablero, posiblesAmenazas);
            }
        }
        return false;
    }

    private List<int[]> posiblesAmenazas(int i, int j){
        Posicion posicion1 = new Posicion(i, j);
        return quePieza(tablero[i][j] % 10).movPosibles(tablero, posicion1);
    }

    private Pieza quePieza(int opcion){
        Pieza pieza = null;
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

    private boolean verSiSeTapa(Posicion amenaza){

        List<int[]> coinsidencias = new ArrayList<>();
        List<int[]> movimientosAmenaza = quePieza(tablero[amenaza.getColumn()][amenaza.getRow()] % 10).movPosibles(tablero, amenaza);
        quePieza(4).borrarPosibles(tablero, movimientosAmenaza);
        List<int[]> visionesRey = quePieza(8).movPosibles(tablero, posicionRey);
        quePieza(4).borrarPosibles(tablero, visionesRey);

        for(int[] movimiento : movimientosAmenaza){
            for(int[] vision : visionesRey){
                if(movimiento[0] == vision[0] && movimiento[1] == vision[1]){
                    coinsidencias.add(movimiento);
                }
            }
        }

        for(int[] coinsidencia : coinsidencias){
            if(damaTorreAlfil(coinsidencia, true)){
                return true;
            }
        }

        return false;
    }

    private boolean damaTorreAlfil(int[] posicion, boolean amigo){

        int turnoUno;
        int turnoDos;
        if(amigo){
            turnoUno = 3;
            turnoDos = 2;
        } else {
            turnoUno = 2;
            turnoDos = 3;
        }

        int c;
        int r;

        int[] x = {0, 0, -1, 1, -1, -1, 1, 1};
        int[] y = {-1, 1, 0, 0, -1, 1, -1, 1};

        for (int i = 0; i < x.length; i++) {
            c = posicion[0];
            r = posicion[1];

            do {
                c += x[i];
                r += y[i];
                if ((tablero[c][r] / 10 == turnoUno && noTurno == 3) || (tablero[c][r] / 10 == turnoDos && noTurno == 2)) {
                    if((x[i] == 0 || y[i] == 0) && (tablero[c][r] % 10 == 5 || tablero[c][r] % 10 == 8)){
                        return true;
                    } else if ((x[i] != 0 && y[i] != 0) && (tablero[c][r] % 10 == 7 || tablero[c][r] % 10 == 8)) {
                        return true;
                    } else {
                        break;
                    }
                } else if(tablero[c][r] > 0){
                    break;
                }
            } while (tablero[c][r] != 1);
        }
        return false;
    }

    private boolean caballos(int[] posicion){
        int[] x = { 1,1,-1,0,0, 1,-3,-1,1, 0, 0,-1};
        int[] y = {-1,0,-1,3,1,-1, 0, 0,1,-3,-1, 1};

        int c = posicion[0];
        int r = posicion[1];

        for (int i = 0; i < x.length; i++) {
            c += x[i];
            r += y[i];

            if (tablero[c][r] == 1 && (i == 0 || i == 3 || i == 6 || i == 9)) {
                for(int j = 0; j < 2; j++){
                    i++;
                    c += x[i];
                    r += y[i];
                }
            } else {
                if (tablero[c][r] / 10 == 2 && noTurno == 3 && tablero[c][r] % 10 == 6
                        && i != 0 && i != 3 && i != 6 && i != 9) {
                    return true;
                } else if (tablero[c][r] / 10 == 3 && noTurno == 2 && tablero[c][r] % 10 == 6
                        && i != 0 && i != 3 && i != 6 && i != 9) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean peones(int[] posicion){
        int c;
        int r;

        if(noTurno == 2){
            c = posicion[0] + 1;
            r = posicion[1] + 1;
            for(int i = 0; i < 2; i++){
                if(tablero[c][r] / 10 == 3 && tablero[c][r] % 10 == 4){
                    return true;
                }
                c -= 2;
            }
        }
        if(noTurno == 3){
            c = posicion[0] - 1;
            r = posicion[1] - 1;
            for(int i = 0; i < 2; i++){
                if(tablero[c][r] / 10 == 2 && tablero[c][r] % 10 == 4){
                    return true;
                }
                c += 2;
            }
        }
        return false;
    }
}
