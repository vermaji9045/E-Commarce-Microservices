package com.product.Service;

import com.product.Dto.ProductDto;
import com.product.Model.Product;
import com.product.mapper.ProductMapper;
import com.product.mapper.ProductRespone;
import com.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDto productDto)
    {
        Product product1=new Product();
        Product product= ProductMapper.mapToProduct(productDto, product1);
        productRepository.save(product);
    }

    public ProductDto getAllProduct(String name)
    {
        Product products =(Product) productRepository.findByName(name);

        return ProductMapper.mapToCardDto(products,new ProductDto());
    }


}
