package com.cejjl.sales_points_system.dtos.request;

import com.cejjl.sales_points_system.models.funcionario.Enums.StatusEnum;

public record PostoRequest(String nome, StatusEnum isAtivo) {
}
