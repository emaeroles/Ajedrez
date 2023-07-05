package com.grupo5.apitpajedrez.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "jugadores")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;
}
