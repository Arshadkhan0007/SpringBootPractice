package com.CompanyName.PracticeProjectCustomer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(
            name = "productSeq",
            sequenceName = "PRODUCT_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    private Integer productId;
    private String productName;
    private float productPrice;

    @ManyToMany(mappedBy = "productList")
    private List<Customer> customerList;
}
