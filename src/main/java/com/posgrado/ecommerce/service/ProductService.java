package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.ProductDTO;
import com.posgrado.ecommerce.entity.Product;
import java.util.UUID;

public interface ProductService {

  Product save(ProductDTO productDTO);

  Product getById(UUID id);
}
