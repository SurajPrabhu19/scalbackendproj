package com.sp.scalbackendproj.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.sp.scalbackendproj.dto.FakeStoreProductDto;
import com.sp.scalbackendproj.dto.GenericProductDto;
import com.sp.scalbackendproj.exception.NotFoundException;
import com.sp.scalbackendproj.service.contracts.IProductService;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String baseRequestUrlWithId = "https://fakestoreapi.com/products/{id}";
    private String baseRequestUrl = "https://fakestoreapi.com/products/";
    // private String requestUrlWithName =
    // "https://fakestoreapi.com/products/{id}/{name}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        super();
        this.restTemplateBuilder = restTemplateBuilder;
    }

    // MAPPING OBJECTS:
    public GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        return genericProductDto;
    }

    // GET REQUESTS: -----------------------------------------------------------

    @Override
    public List<GenericProductDto> getAllProducts() {
        // return List.of(new GenericProductDto(), new GenericProductDto());

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List> response = restTemplate.getForEntity(baseRequestUrl, List.class);

        List<FakeStoreProductDto> listOfProducts = response.getBody();
        List<GenericProductDto> allProducts = new ArrayList<>();
        for (FakeStoreProductDto item : listOfProducts) {
            allProducts.add(convertFakeStoreDtoToGenericProductDto(item));
        }

        return allProducts;
        // return null;
        // return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws Exception {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(baseRequestUrl + id,
                FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        if (fakeStoreProductDto == null)
            throw new NotFoundException("Item not found");

        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);

    }

    // POST REQUEST:
    // -------------------------------------------------------------------------
    @Override
    // @JsonProperty
    public GenericProductDto createProduct(GenericProductDto productDto) throws Exception {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'createProduct'");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(baseRequestUrl, productDto,
                GenericProductDto.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new Exception("response.getStatusCode(): " + response.getStatusCode());

        return response.getBody();
    }

    // DELETE MAPPING: ---------------------------------------------------------
    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate
                .responseEntityExtractor(GenericProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(baseRequestUrlWithId, HttpMethod.DELETE,
                requestCallback, responseExtractor);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if (fakeStoreProductDto == null)
            throw new NotFoundException("Product with " + id + " Not Found");
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public Object getById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // RestTemplate restTemplate1 = restTemplateBuilder.build();

        // return restTemplate.getForEntity(baseRequestUrl, FakeStoreProductDto.class,
        // id).getBody();
        return restTemplate.getForEntity(baseRequestUrl + id, Object.class, id);
        // return new FakeStoreProductDto();

    }
}
