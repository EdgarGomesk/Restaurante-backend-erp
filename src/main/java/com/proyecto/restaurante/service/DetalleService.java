package com.proyecto.restaurante.service;

import com.proyecto.restaurante.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.restaurante.entity.Detalle;
import com.proyecto.restaurante.exception.CantidadNovalidaException;
import com.proyecto.restaurante.exception.IdNoEncontradoException;
import com.proyecto.restaurante.exception.PedidoNuloException;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleService {

    @Autowired
    private DetalleRepository detalleRepository;

    public void generarDetalle(Detalle detalle) {
        if(detalle.getCantidad() == null || detalle.getCantidad() < 1) {
            throw new CantidadNovalidaException("Se debe ingresar una cantidad valida mayor a cero");
        }
        if(detalle.getPedido() == null){
        throw new PedidoNuloException("El detalle debe estar relacionado con un pedido");
        }
        if(detalle.getProducto() == null) {
        throw new IdNoEncontradoException("No se ha seleccionado ningun producto a despachar");
        }
        detalleRepository.save(detalle);
    }
    public List<Detalle> obtenerTodosLosDetalles() {
        return detalleRepository.findAll();
    }
    public void eliminarDetalle(Long id){
        detalleRepository.deleteById(id);
    }
    public Detalle buscarPorId(Long id) {
        Optional<Detalle> dt = detalleRepository.findById(id);
        if (dt.isEmpty()) {
            throw new IdNoEncontradoException("El id de la entidad 'Detalle' no existe en la base de datos");
        } else {
        return dt.get();
        }
    }
    
    public Detalle actualizarDetalle(Long id, Detalle detalleActualizado) {
        Optional<Detalle> detalleExistenteOptional = detalleRepository.findById(id);
        if (detalleExistenteOptional.isPresent()) {
            Detalle detalleExistente = detalleExistenteOptional.get();

            detalleExistente.setProducto(detalleActualizado.getProducto());
            detalleExistente.setCantidad(detalleActualizado.getCantidad());

            return detalleRepository.save(detalleExistente);
        } else {
            throw new RuntimeException("Detalle con ID " + id + " no encontrado");
        }
    }
}
