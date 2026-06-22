package com.apimicroservice.demo.repository;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.apimicroservice.demo.dto.ProductDTO;
import com.apimicroservice.demo.dto.prProductDTO;
import com.apimicroservice.demo.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "promotionId", source = "promotion.id")
    ProductDTO toDTO(Product producto);

    @Mapping(target = "unitId", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "promotion", ignore = true)
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    Product toEntity(ProductDTO dto);

    List<ProductDTO> toDTOList(List<Product> productos);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "promotionId", source = "promotion.id")
    prProductDTO toPrDTO(Product producto);
    List<prProductDTO> toPrDTOList(List<Product> productos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unitId", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "promotion", ignore = true)
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDTO(ProductDTO dto, @MappingTarget Product producto);
}
