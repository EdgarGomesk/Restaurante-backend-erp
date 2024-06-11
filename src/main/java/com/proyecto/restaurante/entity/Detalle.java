package com.proyecto.restaurante.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    @JsonBackReference
    private Pedido pedido;

    private Integer cantidad;
}
