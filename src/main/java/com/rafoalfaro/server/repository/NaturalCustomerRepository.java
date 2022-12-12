package com.rafoalfaro.server.repository;

import com.rafoalfaro.server.domain.NaturalCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturalCustomerRepository extends JpaRepository<NaturalCustomer,Long> {
}
