package com.proyecto.restaurante.service;

import com.proyecto.restaurante.entity.Producto;
import com.proyecto.restaurante.exception.IdNoEncontradoException;
import com.proyecto.restaurante.exception.NombreNoValidoException;
import com.proyecto.restaurante.exception.PrecioNoValidoException;
import com.proyecto.restaurante.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public void crearProducto(Producto producto) {
        if(producto.getPrecio() <= 0) {
            throw new PrecioNoValidoException("El precio debe ser un valor positivo diferente de cero");
        }
        if(producto.getNombre() == null || producto.getNombre().equals("")) {
            throw new NombreNoValidoException("El nombre ingresado no puedo estar vacio ni ser nulo");
        }
        if (producto.getNombre().length() < 3) {
            throw new NombreNoValidoException("El nombre ingresado debe tener un minimo de 4 caracteres");
        }
        
        productoRepository.save(producto);
    }

    public List<Producto> traerProductos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        Optional<Producto> productoConsultado = productoRepository.findById(id);
        if (productoConsultado.isEmpty()) {
            throw new IdNoEncontradoException("El id de la entidad 'Producto' no existe en la base de datos");
        } else {
            return productoConsultado.get();
        }
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> productoExistenteOptional = productoRepository.findById(id);
        if (productoExistenteOptional.isPresent()) {
            Producto productoExistente = productoExistenteOptional.get();

            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setPrecio(productoActualizado.getPrecio());

            return productoRepository.save(productoExistente);
        } else {
            throw new RuntimeException("Producto con ID " + id + " no encontrado");
        }
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
