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
public class Customer {
    @Id
    @SequenceGenerator(
            name = "cust_seq",
            sequenceName = "CUST_SEQ",
            allocationSize=1
            )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
    private Integer custId;

    private String custName;
    private Boolean primeMember;
    private Long custNum;
    private String custEmail;

    @ManyToMany
    @JoinTable(name = "Customer_Product",
    joinColumns = @JoinColumn(name = "cust_id"),
    inverseJoinColumns = @JoinColumn(name = "prod_id"))
    private List<Product> prodList;
}
