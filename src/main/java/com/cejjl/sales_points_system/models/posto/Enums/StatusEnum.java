package com.cejjl.sales_points_system.models.posto.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;
}