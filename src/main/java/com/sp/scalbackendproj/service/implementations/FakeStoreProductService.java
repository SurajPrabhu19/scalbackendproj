package com.sp.scalbackendproj.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.scalbackendproj.dto.GenericProductDto;
import com.sp.scalbackendproj.exception.NotFoundException;
import com.sp.scalbackendproj.service.contracts.IProductService;
import com.sp.scalbackendproj.thirdPartyClients.IProductServiceClient;
import com.sp.scalbackendproj.thirdPartyClients.fakestore.FakeStoreProductDto;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {

    private String baseRequestUrlWithId = "https://fakestoreapi.com/products/{id}";
    private String baseRequestUrl = "https://fakestoreapi.com/products/";
    // private String requestUrlWithName =
    // "https://fakestoreapi.com/products/{id}/{name}";

    @Autowired
    private IProductServiceClient<FakeStoreProductDto> iProductServiceClient;

    public FakeStoreProductService(IProductServiceClient<FakeStoreProductDto> iProductServiceClient) {
        super();
        this.iProductServiceClient = iProductServiceClient;
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

    public FakeStoreProductDto convertGenericProductDtoToFakeStoreDto(GenericProductDto genericProductDto) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(genericProductDto.getId());
        fakeStoreProductDto.setCategory(genericProductDto.getCategory());
        fakeStoreProductDto.setDescription(genericProductDto.getDescription());
        fakeStoreProductDto.setImage(genericProductDto.getImage());
        fakeStoreProductDto.setPrice(genericProductDto.getPrice());
        fakeStoreProductDto.setTitle(genericProductDto.getTitle());
        return fakeStoreProductDto;
    }

    // GET REQUESTS: -----------------------------------------------------------
    @Override
    public List<GenericProductDto> getAllProducts() {

        List<FakeStoreProductDto> listOfProducts = iProductServiceClient.getAllProducts();
        List<GenericProductDto> allProducts = new ArrayList<>();
        for (FakeStoreProductDto item : listOfProducts) {
            allProducts.add(convertFakeStoreDtoToGenericProductDto(item));
        }

        return allProducts;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = iProductServiceClient.getProductById(id);
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }

    // POST REQUEST:
    // -------------------------------------------------------------------------
    @Override
    // @JsonProperty
    public GenericProductDto createProduct(GenericProductDto productDto) throws Exception {
        FakeStoreProductDto fakeStoreProductDto = convertGenericProductDtoToFakeStoreDto(productDto);
        fakeStoreProductDto = iProductServiceClient.createProduct(fakeStoreProductDto);
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }

    // DELETE MAPPING: ---------------------------------------------------------
    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException {

        FakeStoreProductDto fakeStoreProductDto = iProductServiceClient.deleteProductById(id);

        if (fakeStoreProductDto == null)
            throw new NotFoundException("Product with " + id + " Not Found");
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public Object getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

}
