package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.entity.Category;
import java.util.UUID;

public interface CategoryService {

  Category getById(UUID id);
}
