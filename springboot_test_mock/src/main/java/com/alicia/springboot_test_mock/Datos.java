package com.alicia.springboot_test_mock;

import com.alicia.springboot_test_mock.models.Banco;
import com.alicia.springboot_test_mock.models.Cuenta;

import java.math.BigDecimal;
import java.util.Optional;

public class Datos {

    public static Optional<Cuenta> crearCuenta001 (){
        return Optional.of(new Cuenta(1L,"Alicia", new BigDecimal("1000")));
    }

    public static Optional<Cuenta> crearCuenta002 (){
        return Optional.of(new Cuenta(2L,"Pepe", new BigDecimal("2000")));
    }

    public static Optional<Banco> crearBanco (){
        return Optional.of(new Banco(1L,"BBVA", 0));
    }
}
