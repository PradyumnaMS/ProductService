package dev.pradyumna.ProductService.controllers;

import dev.pradyumna.ProductService.dtos.GenericProductDto;
import dev.pradyumna.ProductService.exceptions.NotFoundException;
import dev.pradyumna.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    // GET /products {}
    @GetMapping
//    public String getAllProducts(){
    public List<GenericProductDto> getAllProducts(){
//        return List.of(
//                new GenericProductDto(),
//                        new GenericProductDto()
//        );
        return productService.getAllProducts();

//        build the request object
//        call the method
//        return " This is the product";
    }

//    localhost:8080/products/"{Id}"
//    localhost:8080/api/v1/products/"{Id}"

    @GetMapping("/{id}")
    public GenericProductDto getProductsById(@PathVariable("id") Long id) throws NotFoundException {
//        return "Here is product id: " + id;
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
//    public void deleteProductsById(){
    public ResponseEntity<GenericProductDto> deleteProductsById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
//        return productService.deleteProduct(id);
//        here you are able to set the status code manually.
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
