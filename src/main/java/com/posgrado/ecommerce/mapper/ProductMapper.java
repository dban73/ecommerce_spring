package com.posgrado.ecommerce.mapper;

import com.posgrado.ecommerce.dto.ProductDTO;
import com.posgrado.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product convertToProduct(ProductDTO productDTO) {
    Product product = new Product();
    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setImageUrl(productDTO.getImageUrl());
    product.setStock(productDTO.getStock());
    product.setActive(productDTO.isActive());
    product.setPrice(productDTO.getPrice());
    return product;
  }

}
