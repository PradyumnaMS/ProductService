package dev.pradyumna.ProductService.services;

import dev.pradyumna.ProductService.dtos.FakeStoreProductDto;
import dev.pradyumna.ProductService.dtos.GenericProductDto;
import dev.pradyumna.ProductService.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("fakeStoreProductService")
// we are annotating with @service to create instance of the same
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
//    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";

//    private String createProductRequestUrl = "https://fakestoreapi.com/products";

    private String productRequestBaseUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto
    convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){

        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
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
    public GenericProductDto getProductById(Long id) throws NotFoundException {
//        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
//        we are sending the request to fakeStore url via url variable name getProductRequestUrl
//        the response type is like the class fakeStoreProductDto class.
//        It has id as variable
//        response entity is of the type <fakeStoreProductDto>
//        response.getStatusCode();
//        return "hello";

//        this DTO that I have gotten have to convert into product
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Url entered is wrong");
        }

//        return "Here is product id: " + id;
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
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

            answer.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }

//        return response.getBody();
        return answer;
    }


//    for delete method we need it to return a response body.
//    that's why we are seeing the internal implementation of getforEntity.
//    Excecute method which is defined in Rest Template

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback =
                restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

//        GenericProductDto product = new GenericProductDto();
//        product.setImage(fakeStoreProductDto.getImage());

//        here we are converting fakestore into product
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        GenericProductDto product = new GenericProductDto();

    return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }
}

