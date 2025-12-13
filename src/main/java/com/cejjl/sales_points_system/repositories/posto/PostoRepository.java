package com.cejjl.sales_points_system.repositories.posto;

import com.cejjl.sales_points_system.models.posto.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostoRepository extends JpaRepository<Posto, UUID> {

}
