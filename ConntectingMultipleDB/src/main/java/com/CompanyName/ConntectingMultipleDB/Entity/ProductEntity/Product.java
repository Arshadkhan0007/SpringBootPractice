package com.CompanyName.ConntectingMultipleDB.Entity.ProductEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Products")
public class Product {
    @Id
    @SequenceGenerator(
            name = "productSeq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    private Integer productid;
    private String productName;
    private String productDesc;
}
