package com.alicia.springboot_test_mock.services;

import com.alicia.springboot_test_mock.models.Cuenta;

import java.math.BigDecimal;
import java.util.List;

public interface ICuentaService {

    List<Cuenta> findAll();
    Cuenta findById(Long id);
    Cuenta save(Cuenta cuenta);
    void deleteById(Long id);
    int revisarTotalTranferencia(Long bancoId);
    BigDecimal revisarSaldo(Long cuentaId);
    void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto,
                    Long bancoId);
}
