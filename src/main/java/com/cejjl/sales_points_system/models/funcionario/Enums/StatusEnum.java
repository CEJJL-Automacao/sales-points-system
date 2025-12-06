package com.cejjl.sales_points_system.models.funcionario.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    ATIVO("Ativo"),
    INATIVO("Inativo"),
    FERIAS("Em Férias"),
    AFASTADO("Afastado"),
    DESLIGADO("Desligado");

    private final String descricao;
}