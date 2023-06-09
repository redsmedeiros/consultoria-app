package com.consultoriaService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultoriaService.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    public Cliente findByEmail(String email);
}
