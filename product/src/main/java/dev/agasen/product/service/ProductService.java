package dev.agasen.product.service;

import dev.agasen.product.repository.ProductRepository;
import dev.agasen.product.viewmodel.ProductListVm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository productRepository;

   public List<ProductListVm> getProductsByCategories(List<Long> categoryIds) {
      return productRepository.getProductsByCategories(categoryIds).stream()
            .map(ProductListVm::fromModel)
            .toList();
   }


}
