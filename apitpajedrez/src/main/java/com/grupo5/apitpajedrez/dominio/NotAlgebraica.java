package com.grupo5.apitpajedrez.dominio;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class NotAlgebraica {

    private Posicion posicionAnterior;
    private Posicion posicionActual;
    private int contenidoPosAnterior;
    private int contenidoPosActual;


    public NotAlgebraica(Posicion posicionAnterior, Posicion posicionActual, int contenidoPosAnterior, int contenidoPosActual){
        this.posicionAnterior = posicionAnterior;
        this.posicionActual = posicionActual;
        this.contenidoPosAnterior = contenidoPosAnterior;
        this.contenidoPosActual = contenidoPosActual - 100;
    }

    public String movAlgebraico(int estado){
        String mueveCome;
        String jaqueOMate;
        if(contenidoPosAnterior == 0 || contenidoPosActual == 0){
            mueveCome = " ";
        } else {
            mueveCome = " x ";
        }
        if(estado == 40){
            jaqueOMate = " +";
        } else if(estado == 50) {
            jaqueOMate = " #";
        } else {
            jaqueOMate = "";
        }
        return convertPieza(contenidoPosAnterior) + posicionAnterior.getNotAlgebraica() + mueveCome +
                convertPieza(contenidoPosActual) + posicionActual.getNotAlgebraica() + jaqueOMate;
    }

    public String getPiezaPsocion(){
        return convertPieza(contenidoPosAnterior) + posicionAnterior.getNotAlgebraica();
    }

    private String convertPieza(int pieza){
        String salida;
        switch (pieza % 10){
            case 4: salida = "P";
                break;
            case 5: salida = "T";
                break;
            case 6: salida = "C";
                break;
            case 7: salida = "A";
                break;
            case 8: salida = "D";
                break;
            case 9: salida = "R";
                break;
            default: salida = "";
                break;
        }
        return salida;
    }
}
