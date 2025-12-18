package com.CompanyName.ConnectingMultipleDB1;

import com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Model.Product;
import com.CompanyName.ConnectingMultipleDB1.Service.ProductService;
import com.CompanyName.ConnectingMultipleDB1.Service.UserService;
import com.CompanyName.ConnectingMultipleDB1.UserDatabase.Model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ConnectingMultipleDb1Application implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;

    public ConnectingMultipleDb1Application(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    public static void main(String[] args) {
		SpringApplication.run(ConnectingMultipleDb1Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        List<User> userList = Arrays.asList(
                new User(101, "Alex"),
                new User(102, "Benson"),
                new User(103, "Charlie"),
                new User(104, "Danny"),
                new User(105, "Earl")
        );

        userService.saveAllUsers(userList);

        List<Product> productList = Arrays.asList(
                new Product(1001, "Apple", "Fruit"),
                new Product(1002, "Banana", "Fruit"),
                new Product(1003, "Cherries", "Fruit"),
                new Product(1004, "Door Mat", "Decoration"),
                new Product(1005, "Eggs", "Food")
        );
        productService.saveAllProducts(productList);

        userService.displayAllUsers();
        productService.displayAllProducts();
    }
}
