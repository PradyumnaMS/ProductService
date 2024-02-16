package dev.pradyumna.ProductService.services;

import dev.pradyumna.ProductService.dtos.GenericProductDto;
import dev.pradyumna.ProductService.models.Product;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
//this is one more class of the same type of implementation
// qualifiers is the solution
public class SelfProductServiceImpl implements ProductService{
//    @Override
//    public Product getProductById(Long id) {
//        return null;
//    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

//    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return null;
    }

}
