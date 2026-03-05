package com.ecommerce.project.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long prductId;
    @NotBlank
    private String productName;
    private String image;
    private Integer quantity;
    private double price;
    private double specialPrice;
    private double discount;
}
