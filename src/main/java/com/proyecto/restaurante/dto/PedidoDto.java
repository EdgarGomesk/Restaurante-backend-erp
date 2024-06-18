package com.proyecto.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PedidoDto {
    private Long id;
    private Integer total;
    private Boolean pagado;
    private LocalDateTime fecha;
}
