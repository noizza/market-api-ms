package com.apimicroservice.demo.repository;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.apimicroservice.demo.dto.DetalleVentaDTO;
import com.apimicroservice.demo.dto.VentaDTO;
import com.apimicroservice.demo.model.DetalleVenta;
import com.apimicroservice.demo.model.Venta;

@Mapper(componentModel = "spring")
public interface VentaMapper {
    VentaDTO toDTO(Venta venta);
    List<VentaDTO> toDTOList(List<Venta> ventas);

    @Mapping(source = "barcode", target = "productoId")
    DetalleVentaDTO toDetalleDTO(DetalleVenta detalle);
}
