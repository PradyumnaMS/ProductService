package dev.pradyumna.ProductService.services;

import dev.pradyumna.ProductService.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    //    Product getProductById(Long id);
//    String getProductById(Long id);
//    we dont want to return a fakeStoreProductDto
//    which is heavily dependent on external API
//    we want to return a product object( which is you)
    GenericProductDto getProductById(Long id);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);
}
