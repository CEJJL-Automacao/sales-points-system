package com.cejjl.sales_points_system.repositories.produto;

import com.cejjl.sales_points_system.models.produto.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, UUID> {
}
