package dev.agasen.product.viewmodel;

import dev.agasen.product.model.Product;

import java.time.ZonedDateTime;

public record ProductListVm (
     Long id,
     String name,
     String slug,
     Double price,
     ZonedDateTime createadOn
) {
   public static ProductListVm fromModel(Product product) {
      return new ProductListVm(
            product.getId(),
            product.getName(),
            product.getSlug(),
            product.getPrice(),
            product.getCreatedOn()
      );
   }
}
