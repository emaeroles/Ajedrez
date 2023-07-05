package com.grupo5.apitpajedrez.dominio.piezas;

import com.grupo5.apitpajedrez.dominio.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza{

    @Override
    public List<int[]> movPosibles(int[][] tablero, Posicion posicion){
        List<int[]> movimientosPosibles = new ArrayList<>();
        int[] movimientoPosible;
        int c = posicion.getColumn();
        int r = posicion.getRow();
        int turno = tablero[c][r] / 10;
        int pasos;

        if(tablero[c][r] / 10 == 2 && posicion.getRow() == 2 || tablero[c][r] / 10 == 3 && posicion.getRow() == 7){
            pasos = 2;
        } else {
            pasos = 1;
        }

        if(turno == 2){
            do{
                r++;
                if(tablero[c][r] == 0){
                    tablero[c][r] += 100;
                    movimientoPosible = new int[] {c,r};
                    movimientosPosibles.add(movimientoPosible);
                    pasos--;
                }
            }while(tablero[c][r] == 100 && pasos > 0);

            c = posicion.getColumn() + 1;
            r = posicion.getRow() + 1;
            for(int i = 0; i < 2; i++){
                if(tablero[c][r] / 10 == 3 && tablero[c][r] != 1){
                    tablero[c][r] += 100;
                    movimientoPosible = new int[] {c,r};
                    movimientosPosibles.add(movimientoPosible);
                }
                c -= 2;
            }
            c = posicion.getColumn();
            r = posicion.getRow();
        }

        if(turno == 3){
            do{
                r--;
                if(tablero[c][r] == 0){
                    tablero[c][r] += 100;
                    movimientoPosible = new int[] {c,r};
                    movimientosPosibles.add(movimientoPosible);
                    pasos--;
                }
            }while(tablero[c][r] == 100 && pasos > 0);

            c = posicion.getColumn() - 1;
            r = posicion.getRow() - 1;
            for(int i = 0; i < 2; i++){
                if(tablero[c][r] / 10 == 2 && tablero[c][r] != 1){
                    tablero[c][r] += 100;
                    movimientoPosible = new int[] {c,r};
                    movimientosPosibles.add(movimientoPosible);
                }
                c += 2;
            }
        }
        return movimientosPosibles;
    }
}
