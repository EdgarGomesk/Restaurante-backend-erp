package com.proyecto.restaurante.controller;

import com.proyecto.restaurante.entity.Pedido;
import com.proyecto.restaurante.service.PedidoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/")
    public ResponseEntity<Boolean> guardarPedido(@RequestBody Pedido pedido) {
        pedidoService.guardarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @GetMapping("/")
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        List<Pedido> listaPedidos = pedidoService.traerPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidos);
    }

    @GetMapping("/dia")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorFecha(@RequestParam("fecha") String fechaStr) {
        LocalDate fecha = LocalDate.parse(fechaStr);
        LocalDateTime startDate = fecha.atStartOfDay();
        LocalDateTime endDate = fecha.atTime(LocalTime.MAX);
        List<Pedido> listaPedidos = pedidoService.obtenerPedidosEntreFechas(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidos);
    }
}
