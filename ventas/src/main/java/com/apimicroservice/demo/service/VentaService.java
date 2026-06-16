package com.apimicroservice.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apimicroservice.demo.clients.ClienteClient;
import com.apimicroservice.demo.clients.ProductosClient;
import com.apimicroservice.demo.dto.ClienteDTO;
import com.apimicroservice.demo.dto.ItemRequestDTO;
import com.apimicroservice.demo.dto.ProductoDTO;
import com.apimicroservice.demo.dto.VentaDTO;
import com.apimicroservice.demo.dto.VentaRequestDTO;
import com.apimicroservice.demo.model.DetalleVenta;
import com.apimicroservice.demo.model.Venta;
import com.apimicroservice.demo.repository.VentaMapper;
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
    private final VentaMapper mapper;

    public VentaDTO createVenta(VentaRequestDTO request) {
        // Validar cliente
        ClienteDTO cliente = client.getClienteById(request.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente con ID " + request.getClienteId() + " no encontrado.");
        }
        List<Long> productoIds = request.getDetalles().stream()
                .map(ItemRequestDTO::productoId)
                .toList();
        List<ProductoDTO> productosDisponibles = productoClient.getProductosBatch(productoIds);
        Map<Long, ProductoDTO> productoMap = productosDisponibles.stream()
                .collect(Collectors.toMap(ProductoDTO::id, p -> p));

        var venta = new Venta();
        venta.setClienteId(request.getClienteId());
        List<DetalleVenta> detalles = new ArrayList<>();
        Double total = 0.0;

        for(ItemRequestDTO itemReq : request.getDetalles()) {
            ProductoDTO prod = productoMap.get(itemReq.productoId());
            if (prod == null) {
                throw new RuntimeException("El producto con ID " + itemReq.productoId() + " no existe.");
            }
            if (prod.stock() < itemReq.cantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + prod.name() + 
                                        ". Disponible: " + prod.stock());
            }

            DetalleVenta detalle = new DetalleVenta();
            detalle.setProductoId(prod.id());
            detalle.setCantidad(itemReq.cantidad());
            detalle.setPrecioUnitario(prod.price());
            detalle.setVenta(venta);
            total += detalle.getPrecioUnitario() * detalle.getCantidad();
            detalles.add(detalle);
        }

        venta.setDetalles(detalles);
        
        Map<Long, Double> stockReductions = request.getDetalles().stream()
                .filter(item -> item != null)
                .collect(Collectors.toMap(
                    ItemRequestDTO::productoId,
                    item -> (double) item.cantidad(),
                    (existing, newValue) -> existing + newValue
                ));
        productoClient.reduceStockBatch(stockReductions);

        venta.setTotal_amount(total);
        Venta savedVenta = repository.save(venta);
        return mapper.toDTO(savedVenta);
    }
}
