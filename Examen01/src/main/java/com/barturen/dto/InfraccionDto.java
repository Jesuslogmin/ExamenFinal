package com.barturen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfraccionDto {
    private int id;
    private String dni;
    private String fecha;
    private String placa;
    private String infraccion;
    private String descripcion;
}