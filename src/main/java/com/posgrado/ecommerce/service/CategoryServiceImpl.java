package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.repository.CategoryRepository;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;

  @Override
  public Category getById(UUID id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found"));
  }
}
