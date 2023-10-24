package com.sp.scalbackendproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sp.scalbackendproj.dto.ExceptionDto;
import com.sp.scalbackendproj.dto.GenericProductDto;
import com.sp.scalbackendproj.exception.NotFoundException;
import com.sp.scalbackendproj.service.contracts.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") IProductService iProductService) {
        super();
        this.productService = iProductService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {

        return productService.getProductById(id);
        // return "The Product has a Id: " + id;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        // return productService.deleteProductById(id);
        return new ResponseEntity<GenericProductDto>(productService.deleteProductById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public String createProduct(@RequestBody GenericProductDto productDto) {
        // return "Create a new Product: " + UUID.randomUUID();
        // return productService.createProduct(productDto);
        return productDto.getTitle();
    }

    @PutMapping("{id}")
    public void updateProductId(@PathVariable("id") Long id) throws NotFoundException {

    }

    // EXCEPTIONS:
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException) {
        // System.out.println("Item Not found");
        return new ResponseEntity(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}