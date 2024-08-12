package com.product.mapper;

import com.product.Dto.ProductDto;
import com.product.Model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductMapper {

    public static ProductDto mapToCardDto(Product product,ProductDto productDto)
    {
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());


        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto,Product product)
    {
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return product;
    }
}
