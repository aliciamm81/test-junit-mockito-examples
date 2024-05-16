package com.alicia.springboot_test_mock.repositories;

import com.alicia.springboot_test_mock.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoRepository extends JpaRepository<Banco,Long> {

}
