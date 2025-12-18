package com.CompanyName.ConnectingMultipleDB1.ProductDatabase.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private int productId;
    private String productName;
    private String ProductDesc;
}
