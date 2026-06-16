package com.apimicroservice.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "product_details")
@RequiredArgsConstructor
public class ProductDetails {
    //TODO: Hay que definir los dedtalles, basarme en el .xls que esta en documentos con fecha reciente.
    //Seguir añadiendo mas coccsas a la entity principal de producto, y luego hacer un mapeo entre ambas entidades.
    //Hacer que quede como el de Akasia.
}
