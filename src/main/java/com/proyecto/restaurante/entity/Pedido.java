package com.proyecto.restaurante.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer total;
    
    private Boolean pagado;

    @OneToMany(mappedBy = "pedido")
    private List<Detalle> detalles;
    
    private LocalDateTime fecha;
}
