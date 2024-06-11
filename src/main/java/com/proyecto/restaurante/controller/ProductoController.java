package com.proyecto.restaurante.controller;

import com.proyecto.restaurante.entity.Producto;
import com.proyecto.restaurante.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/")
    public ResponseEntity<Boolean> guardarProducto(@RequestBody Producto producto) {
        productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerProductos() {
        List<Producto> listaProductos = productoService.traerProductos();
        return ResponseEntity.status(HttpStatus.OK).body(listaProductos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        productoService.actualizarProducto(id, producto);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarProductoId(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
