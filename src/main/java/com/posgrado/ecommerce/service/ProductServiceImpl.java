package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.PageDTO;
import com.posgrado.ecommerce.dto.ProductDTO;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.exception.EntityNotFoundException;
import com.posgrado.ecommerce.mapper.ProductMapper;
import com.posgrado.ecommerce.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @Override
  public List<Product> getAllByCategory(UUID categoryId) {
    return productRepository.findAllByCategoryId(categoryId);
  }

  @Override
  public Page<Product> getProductsPageable(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public PageDTO<Product> getFilteredProducts(Double minPrice, Double maxPrice, Pageable pageable) {
    Page<Product> page = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    return productMapper.convertToPageDTO(page);
  }

  @Override
  public Product updateProduct(UUID productId, ProductDTO productDTO) {
    Product productTarget = productRepository.findById(productId)
        .orElseThrow(() -> new EntityNotFoundException("Product", productId));
    productTarget.setName(productDTO.getName());
    productTarget.setDescription(productDTO.getDescription());
    productTarget.setPrice(productDTO.getPrice());
    productTarget.setImageUrl(productDTO.getImageUrl());
    productTarget.setStock(productDTO.getStock());
    productTarget.setActive(productDTO.isActive());
    return productRepository.save(productTarget);
  }
}
