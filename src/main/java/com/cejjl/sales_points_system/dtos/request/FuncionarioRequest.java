package com.cejjl.sales_points_system.dtos.request;

import com.cejjl.sales_points_system.models.funcionario.Enums.CargoEnum;
import com.cejjl.sales_points_system.models.funcionario.Enums.StatusEnum;

import java.util.UUID;

public record FuncionarioRequest(Long matricula, String nome, CargoEnum cargo, StatusEnum status, UUID postoId) {
}
