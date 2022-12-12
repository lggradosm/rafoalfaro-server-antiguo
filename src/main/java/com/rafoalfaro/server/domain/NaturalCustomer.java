package com.rafoalfaro.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "natural_customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class NaturalCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dni")
    private String dni;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_phone_id",referencedColumnName = "id")
    private ContactPhone contactPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_address_id",referencedColumnName = "id")
    private ContactAddress contactAddress;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public NaturalCustomer(String name, String lastName, String dni, ContactPhone contactPhone, ContactAddress contactAddress) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.contactPhone = contactPhone;
        this.contactAddress = contactAddress;
    }
}
