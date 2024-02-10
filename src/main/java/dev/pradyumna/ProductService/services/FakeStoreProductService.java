package dev.pradyumna.ProductService.services;

import dev.pradyumna.ProductService.dtos.FakeStoreProductDto;
import dev.pradyumna.ProductService.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service("fakeStoreProductService")
// we are annotating with @service to create instance of the same
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    //    @Override
//    public Product getProductById(Long id) {
//        return new Product();
//        return null;

    @Override
    public String getProductById(Long id) {
//        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
//        we are sending the request to fakeStore url via url variable name getProductRequestUrl
//        the response type is like the class fakeStoreProductDto class.
//        It has id as variable
//        response entity is of the type <fakeStoreProductDto>
//        response.getStatusCode();
//        return "hello";
        return "Here is product id: " + id;
    }
}

