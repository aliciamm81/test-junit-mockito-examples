package com.alicia.springboot_test_mock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDto {

    private Long idCuentaOrigen;
    private Long idCuentaDestino;
    private BigDecimal monto;
    private Long idBanco;
}
