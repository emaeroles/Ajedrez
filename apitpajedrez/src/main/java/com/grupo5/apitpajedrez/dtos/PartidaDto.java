package com.grupo5.apitpajedrez.dtos;

import com.grupo5.apitpajedrez.dominio.Jugador;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDto {
    private Long id;
    private String nombre;
    private int[][] tablero;
    private Jugador jugadorBlancas;
    private Jugador jugadorNegras;
    private int turno;
    private List<String> listaDeMovimientos;

}
