package dev.pradyumna.ProductService.controllers;

import dev.pradyumna.ProductService.dtos.GenericProductDto;
import dev.pradyumna.ProductService.models.Product;
import dev.pradyumna.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/products")
public class ProductController {

//    we need to get service inside of controller - By DI
//@Autowired - its field injection - never use this

    private ProductService productService;
//    the object of this will be created by spring - but annotate with @service


//this is DI by constructor injection
//this is the best
//    u r telling to put an object of ProductService
//    but there are 2 classes of ProductService
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }


// this is DI by setter injection
//@Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping
    public String getAllProducts(){
        return " This is the product";
    }

//    localhost:8080/products/"{Id}"
//    localhost:8080/api/v1/products/"{Id}"

    @GetMapping("/{id}")
    public GenericProductDto getProductsById(@PathVariable("id") Long id){
//        return "Here is product id: " + id;
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductsById(){

    }
//what ever is there in the request body convert it into genericproductdto
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
//        return "Created new product with name " + product.getTitle();
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }


}
