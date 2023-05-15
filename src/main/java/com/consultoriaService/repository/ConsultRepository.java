package com.consultoriaService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultoriaService.entity.Consulting;

public interface ConsultRepository extends JpaRepository<Consulting, Long>{
    
}
