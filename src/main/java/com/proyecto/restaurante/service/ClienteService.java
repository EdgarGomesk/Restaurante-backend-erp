package com.proyecto.restaurante.service;

import com.proyecto.restaurante.entity.Cliente;
import com.proyecto.restaurante.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void crearCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public List<Cliente> traerClientes() {
        return clienteRepository.findAll();
    }
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
