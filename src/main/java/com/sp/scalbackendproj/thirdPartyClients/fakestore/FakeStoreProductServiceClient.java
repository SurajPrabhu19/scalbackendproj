package com.sp.scalbackendproj.thirdPartyClients.fakestore;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.sp.scalbackendproj.exception.NotFoundException;
import com.sp.scalbackendproj.thirdPartyClients.IProductServiceClient;

@Service
public class FakeStoreProductServiceClient implements IProductServiceClient {

    private final RestTemplate restTemplate;
    private final static String BASE_URL = "https://fakestoreapi.com/products/";

    private final static Class<FakeStoreProductDto> T = FakeStoreProductDto.class;

    public FakeStoreProductServiceClient(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    @Override
    public FakeStoreProductDto createProduct(Object product) {

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(BASE_URL, product,
                FakeStoreProductDto.class);

        return responseEntity.getBody();
    }

    @Override
    public FakeStoreProductDto deleteProductById(Long id) {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate
                .responseEntityExtractor(FakeStoreProductDto.class);
        return restTemplate.execute(BASE_URL + id, HttpMethod.DELETE, requestCallback, responseExtractor, id).getBody();
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDto>> responseEntity = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
                });
        return responseEntity.getBody();
    }

    @Override
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(BASE_URL + id,
                FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        if (fakeStoreProductDto == null)
            throw new NotFoundException("Item not found");

        return fakeStoreProductDto;
    }

}
