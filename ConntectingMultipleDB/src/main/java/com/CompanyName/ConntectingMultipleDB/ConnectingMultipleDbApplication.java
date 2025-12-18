package com.CompanyName.ConntectingMultipleDB;

import com.CompanyName.ConntectingMultipleDB.Entity.ProductEntity.Product;
import com.CompanyName.ConntectingMultipleDB.Entity.UserEntity.User;
import com.CompanyName.ConntectingMultipleDB.Service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class ConnectingMultipleDbApplication implements CommandLineRunner {

    private final ProductService productService;

    public ConnectingMultipleDbApplication(ProductService productService) {
        this.productService = productService;
    }

    public static void main(String[] args) {
		SpringApplication.run(ConnectingMultipleDbApplication.class, args);
	}

    @Override
    public void run(String... args) {
        List<User> userList = Arrays.asList(
                new User(null, "Alex", "alex123@gmail.com"),
                new User(null, "Benson", "benson456@gmail.com"),
                new User(null, "Charlie", "charles789@gmail.com"),
                new User(null, "Earl", "earl987@gmail.com")
        );

        productService.addAllUserData(userList);

        List<Product> productList = Arrays.asList(
                new Product(null, "Apple", "Fruit"),
                new Product(null, "Banana", "Fruit"),
                new Product(null, "Cap", "Clothing"),
                new Product(null, "Doll", "Toy")
        );

        productService.addAllProdData(productList);

        productService.displayData();
    }
}
