package com.proyecto.restaurante.controller;

import com.proyecto.restaurante.entity.Detalle;
import com.proyecto.restaurante.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/detalle")
@CrossOrigin(origins = "*")
public class DetalleController {

    @Autowired
    private DetalleService detalleService;

    @GetMapping("/")
    public ResponseEntity<List<Detalle>> obtenerDetalles() {
        List<Detalle> detallesList = detalleService.obtenerTodosLosDetalles();
        return ResponseEntity.status(HttpStatus.OK).body(detallesList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Detalle> obtenerDetallesPorId(@PathVariable Long id) {
        Detalle detalle = detalleService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalle);
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> generarDetalle(@RequestBody Detalle detalle){
        detalleService.generarDetalle(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> borrarDetallePorId(@PathVariable Long id){
        detalleService.eliminarDetalle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Detalle> actualizarDetalle(@PathVariable Long id, @RequestBody Detalle detalle) {
        Detalle detalleActualizado = detalleService.actualizarDetalle(id, detalle);
        return ResponseEntity.ok(detalleActualizado);
    }
}
