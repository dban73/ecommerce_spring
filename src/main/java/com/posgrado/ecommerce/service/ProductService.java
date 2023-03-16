package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.PageDTO;
import com.posgrado.ecommerce.dto.ProductDTO;
import com.posgrado.ecommerce.entity.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Product save(ProductDTO productDTO);

  Product getById(UUID id);

  List<Product> getAllByCategory(UUID categoryId);

  Page<Product> getProductsPageable(Pageable pageable);

  PageDTO<Product> getFilteredProducts(Double minPrice, Double maxPrice, Pageable pageable);

  Product updateProduct(UUID productId, ProductDTO productDTO);
}
