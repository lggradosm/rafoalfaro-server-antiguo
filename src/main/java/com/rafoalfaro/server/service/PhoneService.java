package com.rafoalfaro.server.service;

import com.rafoalfaro.server.domain.ContactPhone;
import com.rafoalfaro.server.domain.Phone;
import com.rafoalfaro.server.repository.ContactPhoneRepository;
import com.rafoalfaro.server.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> saveAll(List<Phone> phoneList,ContactPhone contactPhone){
        for (Phone phone:phoneList) {
            phone.setContactPhone(contactPhone);
        }
        return phoneRepository.saveAll(phoneList);
    }
}
