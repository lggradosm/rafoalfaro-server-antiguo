package com.rafoalfaro.server.repository;

import com.rafoalfaro.server.domain.ContactPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPhoneRepository extends JpaRepository<ContactPhone,Long> {
}
