package com.rafoalfaro.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contact_addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ContactAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "contactAddress",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Address> addresList;

    public ContactAddress(List<Address> addresList) {
        this.addresList = addresList;
    }
}
