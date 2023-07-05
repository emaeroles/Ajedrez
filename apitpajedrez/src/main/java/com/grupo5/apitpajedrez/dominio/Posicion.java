package com.grupo5.apitpajedrez.dominio;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posicion {
    private String notAlgebraica;
    private int column;
    private int row;

    public Posicion(String posicionStr){
        notAlgebraica = posicionStr;
        dividir();
    }

    public Posicion(int c, int r){
        column = c;
        row = r;
        juntar();
    }

    private void dividir(){
        String cStr = notAlgebraica.substring(0, 1);
        row = Integer.parseInt(notAlgebraica.substring(1, 2));

        switch (cStr){
            case "a": column = 1;
                break;
            case "b": column = 2;
                break;
            case "c": column = 3;
                break;
            case "d": column = 4;
                break;
            case "e": column = 5;
                break;
            case "f": column = 6;
                break;
            case "g": column = 7;
                break;
            case "h": column = 8;
                break;
        }
    }

    private void juntar(){

        switch (column){
            case 1: notAlgebraica = "a" + row;
                break;
            case 2: notAlgebraica = "b" + row;
                break;
            case 3: notAlgebraica = "c" + row;
                break;
            case 4: notAlgebraica = "d" + row;
                break;
            case 5: notAlgebraica = "e" + row;
                break;
            case 6: notAlgebraica = "f" + row;
                break;
            case 7: notAlgebraica = "g" + row;
                break;
            case 8: notAlgebraica = "h" + row;
                break;
        }
    }
}
