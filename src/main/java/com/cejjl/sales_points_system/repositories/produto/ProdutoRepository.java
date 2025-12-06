package com.cejjl.sales_points_system.repositories.produto;

import com.cejjl.sales_points_system.models.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    List<Produto> findByGrupoId(UUID grupoId);

}
