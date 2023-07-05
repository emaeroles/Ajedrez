package com.grupo5.apitpajedrez.dominio;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {

    private Long id;
    @NotNull(message = "El nickname es requerido")
    private String nickname;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;

    public void sumarPartidaGanada(){
        partidasGanadas += 1;
    }
    public void sumarPartidaPerdida(){
        partidasPerdidas += 1;
    }
    public void sumarPartidaEmpatada() {
        partidasEmpatadas += 1;
    }
}
