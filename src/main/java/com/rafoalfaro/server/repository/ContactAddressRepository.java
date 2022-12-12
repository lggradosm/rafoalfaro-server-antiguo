package com.rafoalfaro.server.repository;

import com.rafoalfaro.server.domain.ContactAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress,Long> {
}
