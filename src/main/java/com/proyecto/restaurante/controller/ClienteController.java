package com.proyecto.restaurante.controller;

import com.proyecto.restaurante.entity.Cliente;
import com.proyecto.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<Boolean> guardarCliente(@RequestBody Cliente cliente) {
        clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> listaClientes = clienteService.traerClientes();
        return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarClienteId(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
