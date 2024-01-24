package dev.pradyumna.ProductService.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @GetMapping
    public String getAllProducts(){
        return " This is the product";
    }

//    localhost:8080/products/"{Id}"
//    localhost:8080/api/v1/products/"{Id}"

    @GetMapping("/{Id}")
    public String getProductsById(@PathVariable("Id") Long id){
        return "Here is product id: " + id;
    }

    @DeleteMapping("{Id}")
    public void deleteProductsById(){

    }

    @PostMapping("{Id}")
    public void createProductById(){

    }

    @PutMapping("{Id}")
    public void updateProductById(){

    }


}
