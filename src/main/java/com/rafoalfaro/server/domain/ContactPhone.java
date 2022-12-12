package com.rafoalfaro.server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contact_phones")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ContactPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "contactPhone", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Phone> phoneList;

    public ContactPhone(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }


}
