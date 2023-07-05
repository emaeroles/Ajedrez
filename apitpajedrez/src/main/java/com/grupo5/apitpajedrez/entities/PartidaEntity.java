package com.grupo5.apitpajedrez.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "partidas")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tableroSrt;
    @ManyToOne
    private JugadorEntity jugadorBlancas;
    @ManyToOne
    private JugadorEntity jugadorNegras;
    private int turno;
    private int noTurno;
    private boolean juegoFinalizado;
    private String listaDeMovimientosSrt;

}
