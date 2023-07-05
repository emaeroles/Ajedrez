package com.grupo5.apitpajedrez.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TouchDto {
    private int accion;
    private String notAlgebraica;
    private int turno;
    private int estado;

    public TouchDto(int accion, int turno, int estado){
        this.accion = accion;
        this.notAlgebraica = "";
        this.turno = turno;
        this.estado = estado;
    }
    public TouchDto(int accion, String notAlgebraica, int turno, int estado) {
        this.accion = accion;
        this.notAlgebraica = notAlgebraica;
        this.turno = turno;
        this.estado = estado;
    }

}

