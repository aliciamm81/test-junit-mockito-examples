package com.alicia.springboot_test_mock.services;

import com.alicia.springboot_test_mock.models.Banco;
import com.alicia.springboot_test_mock.models.Cuenta;
import com.alicia.springboot_test_mock.repositories.BancoRepository;
import com.alicia.springboot_test_mock.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private BancoRepository bancoRepository;

//    public CuentaService(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
//        this.cuentaRepository = cuentaRepository;
//        this.bancoRepository = bancoRepository;
//    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public int revisarTotalTranferencia(Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        return banco.getTotalTransferencias();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElse(null);
        return cuenta.getSaldo();
    }

    @Override
    @Transactional
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto,
                           Long bancoId) {

        Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen).orElseThrow();
        cuentaOrigen.debito(monto);
        cuentaRepository.save(cuentaOrigen);

        Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino).orElseThrow();
        cuentaDestino.credito(monto);
        cuentaRepository.save(cuentaDestino);

        Optional<Banco> banco = bancoRepository.findById(bancoId);
        if (banco.isPresent()) {
            int totalTransferencias = banco.get().getTotalTransferencias();
            banco.get().setTotalTransferencias(++totalTransferencias);
            bancoRepository.save(banco.get());
        }

    }
}
