package com.rafoalfaro.server.repository;

import com.rafoalfaro.server.domain.Employee;
import com.rafoalfaro.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public Employee findEmployeeByUser(User user);
}
