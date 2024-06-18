package com.proyecto.restaurante.service;

import com.proyecto.restaurante.dto.PedidoDto;
import com.proyecto.restaurante.entity.Pedido;
import com.proyecto.restaurante.repository.PedidoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void guardarPedido(Pedido pedido) {
        pedido.setFecha(LocalDateTime.now());
        pedidoRepository.save(pedido);
    }

    public List<Pedido> traerPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        Optional<Pedido> pedidoExistenteOptional = pedidoRepository.findById(id);
        if (pedidoExistenteOptional.isPresent()) {
            Pedido pedidoExistente = pedidoExistenteOptional.get();

            pedidoExistente.setTotal(pedidoActualizado.getTotal());
            pedidoExistente.setPagado(pedidoActualizado.getPagado());

            return pedidoRepository.save(pedidoExistente);
        } else {
            throw new RuntimeException("Pedido con ID " + id + " no encontrado");
        }
    }
    
    public List<Pedido> obtenerPedidosEntreFechas(LocalDateTime startDate, LocalDateTime endDate) {
        return pedidoRepository.findPedidosEntreFechas(startDate, endDate);
    }

    public List<Pedido> obtenerPedidosPagados() {
        return pedidoRepository.findPedidosPagados();
    }

    public List<PedidoDto> obtenerPedidosNoPagados() {
        List<Pedido> listPedidosNoPagados = pedidoRepository.findPedidosNoPagados();
        return listPedidosNoPagados.stream().map(pedido -> new PedidoDto(pedido.getId(), pedido.getTotal(), pedido.getPagado(), pedido.getFecha())).collect(Collectors.toList());
    }

}
