package com.rafoalfaro.server.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bussiness_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class BussinessCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bussiness_name")
    private String bussinessName;
    @Column(name = "ruc")
    private String ruc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_phone_id",referencedColumnName = "id")
    private ContactPhone contactPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_address_id",referencedColumnName = "id")
    private ContactAddress contactAddress;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public BussinessCustomer(String bussinessName, String ruc, ContactPhone contactPhone, ContactAddress contactAddress) {
        this.bussinessName = bussinessName;
        this.ruc = ruc;
        this.contactPhone = contactPhone;
        this.contactAddress = contactAddress;
    }
}
