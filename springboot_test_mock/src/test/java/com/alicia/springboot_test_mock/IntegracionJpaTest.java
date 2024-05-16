package com.alicia.springboot_test_mock;

import com.alicia.springboot_test_mock.models.Cuenta;
import com.alicia.springboot_test_mock.repositories.CuentaRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integracion_jpa")
@DataJpaTest
public class IntegracionJpaTest {
    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    void testFindById(){
        Optional<Cuenta> cuenta = cuentaRepository.findById(1L);
        assertTrue(cuenta.isPresent());
        assertEquals("Alicia", cuenta.orElseThrow().getPersona());
    }

    @Test
    void testFindByPersona(){
        Optional<Cuenta> cuenta = Optional.ofNullable(cuentaRepository.findByPersona("Alicia")).orElseThrow();
        assertTrue(cuenta.isPresent());
        assertEquals("Alicia", cuenta.orElseThrow().getPersona());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }
    @Test
    void testFindByPersonaThrowException(){
        Optional<Cuenta> cuenta = Optional.ofNullable(cuentaRepository.findByPersona("rojo")).orElseThrow();
        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);
        assertFalse(cuenta.isPresent());
    }
    @Test
    void testFindAll(){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());
    }

    @Test
    void testSave(){
        Cuenta cuentaPepe = new Cuenta(null,"Pepe",new BigDecimal("3000"));
        Cuenta save = cuentaRepository.save(cuentaPepe);

        Cuenta cuenta = cuentaRepository.findById(save.getId()).orElseThrow();

        assertEquals("Pepe", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testUpdate(){
        Cuenta cuentaPepe = new Cuenta(null,"Pepe",new BigDecimal("3000"));
        Cuenta cuenta = cuentaRepository.save(cuentaPepe);

        assertEquals("Pepe", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());

        cuenta.setSaldo(new BigDecimal("4000"));
        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);

        assertEquals("Pepe", cuentaActualizada.getPersona());
        assertEquals("4000", cuentaActualizada.getSaldo().toPlainString());
    }

    @Test
    void testDelete(){
        Cuenta cuenta = cuentaRepository.findById(2L).orElseThrow();
        assertEquals("Jhon", cuenta.getPersona());

        cuentaRepository.delete(cuenta);

        assertThrows(NoSuchElementException.class, () -> cuentaRepository.findByPersona("Jhon").orElseThrow());
    }
}
