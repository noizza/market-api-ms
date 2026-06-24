package com.apimicroservice.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apimicroservice.demo.clients.ClienteClient;
import com.apimicroservice.demo.clients.ProductosClient;
import com.apimicroservice.demo.dto.ClienteDTO;
import com.apimicroservice.demo.dto.ItemRequestDTO;
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
    private final ProductosClient productosClient;
    private final VentaMapper mapper;

    public VentaDTO createVenta(VentaRequestDTO request) {
        ClienteDTO cliente = client.getClienteById(request.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente con ID " + request.getClienteId() + " no encontrado.");
        } 

        Map<Long, Integer> itemsToReduce = request.getDetalles().stream()
            .collect(Collectors.groupingBy(
                ItemRequestDTO::barcode,
                Collectors.summingInt(ItemRequestDTO::cantidad)
            ));

        productosClient.reduceStockBatch(itemsToReduce);

        List<DetalleVenta> detalles = new ArrayList<>();
        double totalVenta = 0.0;

        for (ItemRequestDTO item : request.getDetalles()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setBarcode(item.barcode());
            detalle.setCantidad(item.cantidad());
            
            var productoInfo = productosClient.getProductoByBarcode(item.barcode()); 
            
            if (productoInfo == null || productoInfo.price() == null) {
                throw new RuntimeException("No se pudo obtener el precio para el producto: " + item.barcode());
            }

            detalle.setPrecioUnitario(productoInfo.price());
            detalle.setSubTotal(detalle.getPrecioUnitario() * item.cantidad());            
            totalVenta += detalle.getSubTotal();
            detalles.add(detalle);
        }

        Venta venta = new Venta();
        venta.setTotal_amount(totalVenta);
        venta.setDate(LocalDateTime.now());
        venta.setClienteId(request.getClienteId());
        
        for (DetalleVenta det : detalles) {
            det.setVenta(venta);
        }
        venta.setDetalles(detalles);

        Venta ventaGuardada = repository.save(venta);

        return mapper.toDTO(ventaGuardada);
    }
}
