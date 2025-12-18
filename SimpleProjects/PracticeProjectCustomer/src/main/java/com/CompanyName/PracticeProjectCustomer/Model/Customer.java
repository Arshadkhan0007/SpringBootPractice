package com.CompanyName.PracticeProjectCustomer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customerSeq",
            sequenceName = "CUSTOMER_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
    private Integer customerId;
    private String customerName;

    @ManyToMany
    @JoinTable(
            name = "Customer_Product",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<Product> productList;

    private String customerEmail;
    private String customerUsername;
    private String customerPassword;
}
