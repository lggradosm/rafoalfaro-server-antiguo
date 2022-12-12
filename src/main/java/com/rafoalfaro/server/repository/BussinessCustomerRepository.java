package com.rafoalfaro.server.repository;

import com.rafoalfaro.server.domain.BussinessCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BussinessCustomerRepository extends JpaRepository<BussinessCustomer,Long> {
}
