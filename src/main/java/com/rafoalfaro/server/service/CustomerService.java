package com.rafoalfaro.server.service;

import com.rafoalfaro.server.domain.*;
import com.rafoalfaro.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NaturalCustomerRepository naturalCustomerRepository;
    @Autowired
    private BussinessCustomerRepository bussinessCustomerRepository;

    @Autowired
    private ContactPhoneRepository contactPhoneRepository;
    @Autowired
    private ContactAddressRepository contactAddressRepository;

    @Autowired private PhoneService phoneService;

    @Autowired private AddressService addressService;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        List<Phone> phoneList;
        List<Address> addressList;
        ContactPhone contactPhone = new ContactPhone();
        ContactAddress contactAddress = new ContactAddress();
        BussinessCustomer bussinessCustomer;
        NaturalCustomer naturalCustomer ;
        contactPhone = contactPhoneRepository.save(contactPhone);
        contactAddress = contactAddressRepository.save(contactAddress);
        if(customer.getNaturalCustomer()!=null) {
            naturalCustomer = customer.getNaturalCustomer();
            phoneList = savePhoneList(naturalCustomer.getContactPhone().getPhoneList(), contactPhone);
            addressList = saveAddressList(naturalCustomer.getContactAddress().getAddresList(), contactAddress);
            contactPhone.setPhoneList(phoneList);
            contactAddress.setAddresList(addressList);
            naturalCustomer.setContactAddress(contactAddress);
            naturalCustomer.setContactPhone(contactPhone);
            naturalCustomer = naturalCustomerRepository.save(naturalCustomer);
            customer.setNaturalCustomer(naturalCustomer);
            customer.setType("natural");

        }
        else{
            bussinessCustomer = customer.getBussinessCustomer();
            phoneList = savePhoneList(bussinessCustomer.getContactPhone().getPhoneList(), contactPhone);
            addressList = saveAddressList(bussinessCustomer.getContactAddress().getAddresList(), contactAddress);
            contactPhone.setPhoneList(phoneList);
            contactAddress.setAddresList(addressList);
            bussinessCustomer.setContactAddress(contactAddress);
            bussinessCustomer.setContactPhone(contactPhone);
            bussinessCustomer = bussinessCustomerRepository.save(bussinessCustomer);
            customer.setBussinessCustomer(bussinessCustomer);
            customer.setType("bussiness");
        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    private List<Phone> savePhoneList(List<Phone> phoneList,ContactPhone contactPhone){
        return phoneService.saveAll(phoneList, contactPhone);
    }

    private List<Address> saveAddressList(List<Address> addressList,ContactAddress contactAddress){
        return addressService.saveAll(addressList,contactAddress);
    }
}
