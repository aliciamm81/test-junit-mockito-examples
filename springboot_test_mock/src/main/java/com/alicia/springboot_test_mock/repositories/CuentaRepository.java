package com.alicia.springboot_test_mock.repositories;

import com.alicia.springboot_test_mock.models.Banco;
import com.alicia.springboot_test_mock.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    Optional<Cuenta> findByPersona(String persona);
}
