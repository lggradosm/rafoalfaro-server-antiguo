package com.rafoalfaro.server.service;

import com.rafoalfaro.server.domain.Address;
import com.rafoalfaro.server.domain.ContactAddress;
import com.rafoalfaro.server.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired private AddressRepository addressRepository;

    public List<Address> saveAll(List<Address> addressList, ContactAddress contactAddress){
        for (Address address:addressList){
            address.setContactAddress(contactAddress);
        }
        return addressRepository.saveAll(addressList);
    }
}
