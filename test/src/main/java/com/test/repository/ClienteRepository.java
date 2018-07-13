package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
