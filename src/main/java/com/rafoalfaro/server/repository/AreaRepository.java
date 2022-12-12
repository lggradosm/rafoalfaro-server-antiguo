package com.rafoalfaro.server.repository;

import com.rafoalfaro.server.domain.Area;
import com.rafoalfaro.server.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area,Long> {
}
