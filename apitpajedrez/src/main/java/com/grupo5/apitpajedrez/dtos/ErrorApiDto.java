package com.grupo5.apitpajedrez.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorApiDto {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
}
