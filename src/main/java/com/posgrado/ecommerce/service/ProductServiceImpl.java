package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.ProductDTO;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.exception.EntityNotFoundException;
import com.posgrado.ecommerce.mapper.ProductMapper;
import com.posgrado.ecommerce.repository.ProductRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private CategoryService categoryService;
  private ProductRepository productRepository;

  private ProductMapper productMapper;

  @Override
  public Product save(ProductDTO productDTO) {
    Category category = categoryService.getById(productDTO.getCategoryId());
    Product product = productMapper.convertToProduct(productDTO);
    product.setCategory(category);
    return productRepository.save(product);
  }

  @Override
  public Product getById(UUID id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product", id));
  }
}
