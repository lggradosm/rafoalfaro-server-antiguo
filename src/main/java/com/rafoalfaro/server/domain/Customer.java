package com.rafoalfaro.server.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bussiness_customer_id")
    private BussinessCustomer bussinessCustomer;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "natural_customer_id" )
    private NaturalCustomer naturalCustomer;
    private String type;
    public Customer(String type, NaturalCustomer naturalCustomer) {
        this.type = type;
        this.naturalCustomer = naturalCustomer;
    }

}
