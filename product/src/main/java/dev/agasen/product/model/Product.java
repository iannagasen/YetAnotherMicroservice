package dev.agasen.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("javaarchitecture:S7027")
public class Product extends AbstractAuditEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   private String shortDescription;
   private String description;
   private String specification;
   private String sku;
   private String slug;
   private Double price;

   @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
   @Builder.Default
   private List<ProductCategory> productCategories = new ArrayList<>();

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (!(o instanceof Product)) {
         return false;
      }
      return id != null && id.equals(((Product) o).id);
   }

   @Override
   public int hashCode() {
      // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
      return getClass().hashCode();
   }


}
