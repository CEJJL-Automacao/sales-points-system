package com.cejjl.sales_points_system.repositories.venda;

import com.cejjl.sales_points_system.models.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID> {
}
