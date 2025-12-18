package com.CompanyName.SimpleProjectCustomer.Model;

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
            name = "prod_seq",
            sequenceName = "PROD_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
    private Integer prodId;

    private String prodName;
    private Float prodRating;
    private String Description;

    @ManyToMany(mappedBy = "prodList")
    private List<Customer> custList;
}
