package dev.pradyumna.ProductService.services;

import dev.pradyumna.ProductService.dtos.FakeStoreProductDto;
import dev.pradyumna.ProductService.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("fakeStoreProductService")
// we are annotating with @service to create instance of the same
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
//    private String createProductRequestUrl = "https://fakestoreapi.com/products";

    private String productRequestBaseUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    //    @Override
//    public Product getProductById(Long id) {
//        return new Product();
//        return null;

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response =  restTemplate.postForEntity(
                productRequestBaseUrl, product, GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto getProductById(Long id) {
//        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
//        we are sending the request to fakeStore url via url variable name getProductRequestUrl
//        the response type is like the class fakeStoreProductDto class.
//        It has id as variable
//        response entity is of the type <fakeStoreProductDto>
//        response.getStatusCode();
//        return "hello";

//        this DTO that I have gotten have to convert into product
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

//        return "Here is product id: " + id;
        return product;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
//        for the below code
//        because of generics, where at run time it will take ArrayList.class
//        which cant convert it into JSON
//        ResponseEntity<List<FakeStoreProductDto>> response =
//                restTemplate.getForEntity(productRequestBaseUrl, ArrayList<FakeStoreProductDto.class>);
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
//        getbody() is the main data type for <List<FakeStoreProductDto>>

//        its expecting a generic product Dto, i'm having a fakeStoreProductDto

        List<GenericProductDto> answer = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()) {
            GenericProductDto product = new GenericProductDto();
            product.setImage(fakeStoreProductDto.getImage());
            product.setDescription(fakeStoreProductDto.getDescription());
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setPrice(fakeStoreProductDto.getPrice());
            product.setCategory(fakeStoreProductDto.getCategory());

            answer.add(product);
        }

//        return response.getBody();
        return answer;
    }
}

