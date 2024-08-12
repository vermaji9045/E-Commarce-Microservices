package com.product.Controller;

import com.product.Dto.ProductDto;
import com.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/product",produces = {MediaType.APPLICATION_JSON_VALUE})

public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPorduct(@RequestBody ProductDto productDto)
{
    productService.addProduct(productDto);

}

@GetMapping("/fetch")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> fetchProduct(@RequestParam String name)
{
    ProductDto productDto=productService.getAllProduct(name);
    return ResponseEntity.status(HttpStatus.OK).body(productDto);
}
}
