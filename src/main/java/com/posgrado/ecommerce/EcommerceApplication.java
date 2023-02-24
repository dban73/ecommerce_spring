package com.posgrado.ecommerce;

import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

  @Autowired
  CategoryRepository categoryRepository;

  public static void main(String[] args) {
    SpringApplication.run(EcommerceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Category category = new Category();
    category.setName("TRAVEL");
    category.setDescription("Mochillas para viajar");

    Category category1 = new Category();
    category1.setName("SPORT");
    category1.setDescription("Mochillas para deporte");

    categoryRepository.save(category);
    categoryRepository.save(category1);
  }
}
