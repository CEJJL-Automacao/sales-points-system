package com.cejjl.sales_points_system.repositories.funcionario;

import com.cejjl.sales_points_system.models.funcionario.Funcionario;
import com.cejjl.sales_points_system.models.posto.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    List<Funcionario> findByPosto(Posto posto);

    boolean existsByMatriculaAndPosto(Long matricula, Posto postoReal);
}
