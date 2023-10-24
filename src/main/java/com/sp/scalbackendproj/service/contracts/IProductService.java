package com.sp.scalbackendproj.service.contracts;

import java.util.List;

import com.sp.scalbackendproj.dto.GenericProductDto;
import com.sp.scalbackendproj.exception.NotFoundException;

public interface IProductService {

    public Object getById(Long id);

    public GenericProductDto getProductById(Long id) throws NotFoundException;

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto createProduct(GenericProductDto productDto) throws Exception;

    public GenericProductDto deleteProductById(Long id) throws NotFoundException;
}
