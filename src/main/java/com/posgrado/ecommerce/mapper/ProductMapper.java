package com.posgrado.ecommerce.mapper;

import com.posgrado.ecommerce.dto.PageDTO;
import com.posgrado.ecommerce.dto.ProductDTO;
import com.posgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
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

  public PageDTO<Product> convertToPageDTO(Page<Product> page) {
    PageDTO<Product> dto = new PageDTO<>();
    dto.setContent(page.getContent());
    dto.setPageNumber(page.getNumber());
    dto.setPageSize(page.getSize());
    dto.setTotalPages(page.getTotalPages());
    dto.setTotalElements(page.getTotalElements());
    dto.setLast(page.isLast());
    return dto;
  }

}
