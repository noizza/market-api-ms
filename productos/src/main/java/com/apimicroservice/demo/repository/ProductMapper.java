package com.apimicroservice.demo.repository;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.apimicroservice.demo.dto.ProductDTO;
import com.apimicroservice.demo.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product producto);
    Product toEntity(ProductDTO dto);
    List<ProductDTO> toDTOList(List<Product> productos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDTO(ProductDTO dto, @MappingTarget Product producto);
}
