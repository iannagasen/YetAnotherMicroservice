package dev.agasen.product.controller;

import dev.agasen.product.service.ProductService;
import dev.agasen.product.viewmodel.ProductListVm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   ProductService productService;

   @Test
   void testGetProductsByCategories_Success() throws Exception {
      // Mock the response from the service
      List<ProductListVm> mockProducts = List.of(
         new ProductListVm(1L, "Product 1", "Description 1", 100.0, ZonedDateTime.now()),
         new ProductListVm(2L, "Product 2", "Description 2", 200.0, ZonedDateTime.now())
      );
      when(productService.getProductsByCategories(anyList())).thenReturn(mockProducts);

      // perform the get request and verify the response
      mockMvc.perform(
         MockMvcRequestBuilders.get("/backoffice/products/by-categories")
            .param("ids", "1", "2")
            .contentType(MediaType.APPLICATION_JSON)
      )
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(jsonPath("$[0].id").value(1L))
         .andExpect(jsonPath("$[0].name").value("Product 1"))
         .andExpect(jsonPath("$[0].slug").value("Description 1"))
         .andExpect(jsonPath("$[0].price").value(100.0))
         .andExpect(jsonPath("$[1].id").value(2L))
         .andExpect(jsonPath("$[1].name").value("Product 2"))
         .andExpect(jsonPath("$[1].slug").value("Description 2"))
         .andExpect(jsonPath("$[1].price").value(200.0));

      // verify the interaction with the service
      verify(productService, times(1)).getProductsByCategories(anyList());
   }

}