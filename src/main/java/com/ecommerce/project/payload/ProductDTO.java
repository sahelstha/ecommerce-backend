package com.ecommerce.project.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    @NotBlank
    private String productName;
    private String image;
    private Integer quantity;
    private Double price;
    private Double specialPrice;
    private Double discount;
}
