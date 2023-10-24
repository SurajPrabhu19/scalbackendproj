package com.sp.scalbackendproj.service.contracts;

import java.util.List;

import com.sp.scalbackendproj.dto.GenericProductDto;
import com.sp.scalbackendproj.exception.NotFoundException;

public interface IProductService {
    public GenericProductDto getProductById(Long id);

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto createProduct(GenericProductDto productDto) throws Exception;

    public GenericProductDto deleteProductById(Long id) throws NotFoundException;
}
