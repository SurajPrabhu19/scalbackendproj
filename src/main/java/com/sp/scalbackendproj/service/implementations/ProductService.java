package com.sp.scalbackendproj.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.scalbackendproj.dto.GenericProductDto;
import com.sp.scalbackendproj.service.contracts.IProductService;

@Service("productService") // this is the qualifier to distinguish various implm of ProductService
public class ProductService implements IProductService {

    @Override
    public GenericProductDto getProductById(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getProductById'");
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProductById'");
    }

}
