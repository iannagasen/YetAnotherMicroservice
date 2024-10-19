package dev.agasen.product.controller;

import dev.agasen.product.service.ProductService;
import dev.agasen.product.viewmodel.ProductListVm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;

   @GetMapping("/backoffice/products/by-categories")
   public List<ProductListVm> getProductsByCategories(@RequestParam("ids") List<Long> categoryIds) {
      return productService.getProductsByCategories(categoryIds);
   }

}
