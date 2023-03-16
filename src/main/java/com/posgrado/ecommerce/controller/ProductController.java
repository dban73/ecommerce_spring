package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.PageDTO;
import com.posgrado.ecommerce.dto.ProductDTO;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Product")
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  @ApiOperation("Add product")
  @PostMapping
  public ResponseEntity<Product> save(@Valid @RequestBody ProductDTO productDTO) {
    Product product = productService.save(productDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(product);
  }

  @ApiOperation("getProduct by id")
  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable UUID id) {
    Product product = productService.getById(id);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }

  @ApiOperation("Get Products by category Id")
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<Product>> getAllByCategoryId(@PathVariable UUID categoryId) {
    List<Product> products = productService.getAllByCategory(categoryId);
    return ResponseEntity.ok(products);
  }

  @ApiIgnore
  @GetMapping("/pageable")
  public ResponseEntity<Page<Product>> getById(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "20") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsPageable(pageable));
  }

  @ApiOperation("Get filtered products with pagination")
  @GetMapping
  public ResponseEntity<PageDTO<Product>> getFilteredProducts(
      @RequestParam(required = false) Double minPrice,
      @RequestParam(required = false) Double maxPrice,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "id") String sortField,
      @RequestParam(defaultValue = "asc") String sortOrder) {
    if (minPrice == null) {
      minPrice = Double.MIN_VALUE;
    }
    if (maxPrice == null) {
      maxPrice = Double.MAX_VALUE;
    }
    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
    Pageable pageable = PageRequest.of(page, size, sort);
    PageDTO<Product> filteredPage = productService.getFilteredProducts(minPrice, maxPrice,
        pageable);
    return ResponseEntity.status(HttpStatus.OK).body(filteredPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID productId,
      @RequestBody ProductDTO productTarget) {
    Product product = productService.updateProduct(productId, productTarget);
    return ResponseEntity.ok(product);
  }
}
