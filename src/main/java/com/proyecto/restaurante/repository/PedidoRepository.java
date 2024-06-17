/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.restaurante.repository;



import com.proyecto.restaurante.entity.Pedido;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    @Query("SELECT p FROM Pedido p WHERE p.fecha BETWEEN :startDate AND :endDate")
    public List<Pedido> findPedidosEntreFechas(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    @Query("SELECT p FROM Pedido p WHERE p.pagado = true")
    public List<Pedido> findPedidosPagados();
    @Query("SELECT p FROM Pedido p WHERE p.pagado = false")
    public List<Pedido> findPedidosNoPagados();
}
