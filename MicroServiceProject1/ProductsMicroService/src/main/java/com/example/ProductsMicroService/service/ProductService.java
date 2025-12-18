package com.example.ProductsMicroService.service;

import com.example.ProductsMicroService.dto.ProductRequestdto;
import com.example.ProductsMicroService.dto.ProductResponseDto;
import com.example.ProductsMicroService.entity.Product;
import com.example.ProductsMicroService.exception.ProductDidNotExistException;
import com.example.ProductsMicroService.exception.ProductNotFoundException;
import com.example.ProductsMicroService.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponseDto createOrder(ProductRequestdto productRequestdto){

        Product product=productRepository.findByName(productRequestdto.getName());

        if(product.getName()==null){

            throw new ProductDidNotExistException("The request product did not exists"+productRequestdto.getName());

        }


        double totalAmount=product.getPrice()* productRequestdto.getQuantity();

        return new ProductResponseDto("Order Success",product.getName(), totalAmount);


    }

    public Product getProductById(int id){

        Product product=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Request Product did not Found"));


        return product;

    }



}
