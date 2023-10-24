package com.sp.scalbackendproj.thirdPartyClients;

import java.util.List;

import com.sp.scalbackendproj.exception.NotFoundException;

public interface IProductServiceClient<T> {
    public T createProduct(Object product);

    public T deleteProductById(Long id) throws NotFoundException;

    public List<T> getAllProducts();

    public T getProductById(Long id) throws NotFoundException;
}
