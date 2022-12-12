package com.rafoalfaro.server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="phones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_phone_id", referencedColumnName = "id")
    @JsonBackReference
    private ContactPhone contactPhone;

    public Phone(String description, ContactPhone contactPhone) {
        this.description = description;
        this.contactPhone = contactPhone;
    }
}
