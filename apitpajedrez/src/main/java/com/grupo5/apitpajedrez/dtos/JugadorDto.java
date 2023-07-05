package com.grupo5.apitpajedrez.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorDto {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String nickname;
}
