package com.apimicroservice.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apimicroservice.demo.clients.ClienteClient;
import com.apimicroservice.demo.clients.ProductosClient;
import com.apimicroservice.demo.dto.ClienteDTO;
import com.apimicroservice.demo.dto.ItemRequestDTO;
import com.apimicroservice.demo.dto.ProductoDTO;
import com.apimicroservice.demo.dto.VentaRequestDTO;
import com.apimicroservice.demo.model.DetalleVenta;
import com.apimicroservice.demo.model.Venta;
import com.apimicroservice.demo.repository.VentaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VentaService {
    private final VentaRepository repository;
    private final ClienteClient client;
    private final ProductosClient productoClient;

    public Venta createVenta(VentaRequestDTO request) {
        // Validar cliente
        ClienteDTO cliente = client.getClienteById(request.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente con ID " + request.getClienteId() + " no encontrado.");
        }

        var venta = new Venta();
        venta.setClienteId(request.getClienteId());

        List<DetalleVenta> detalles = new ArrayList<>();
        Double total = 0.0;

        for(ItemRequestDTO itemReq : request.getDetalles()) {
            ProductoDTO prod = productoClient.getProductoById(itemReq.getProductoId());

            if (prod.stock() < itemReq.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + prod.name() + 
                                         ". Disponible: " + prod.stock());
            }

            DetalleVenta detalle = new DetalleVenta();
            detalle.setProductoId(prod.id());
            detalle.setCantidad(itemReq.getCantidad());

            detalle.setPrecioUnitario(prod.precio());
            detalle.setVenta(venta);
            total += detalle.getPrecioUnitario() * detalle.getCantidad();
            detalles.add(detalle);
        }

        venta.setDetalles(detalles);
        venta.setTotal_amount(total);
        
        return repository.save(venta);
    }
}
