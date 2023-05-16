package com.consultoriaService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoriaService.payload.ConsultDto;
import com.consultoriaService.service.ConsultService;

@RestController
@RequestMapping("/api/consult")
public class ConsultController {

    private ConsultService consultService;

    public ConsultController(ConsultService consultService){

        this.consultService = consultService;
    }

    @PostMapping
    public ResponseEntity<ConsultDto> createConsult(@RequestBody ConsultDto consultDto){

        ConsultDto response = consultService.createConsult(consultDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ConsultDto> getAllConsults(){

        List<ConsultDto> response = consultService.getAllConsults();

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDto> getConsultById(@PathVariable(name = "id") Long consultId){

        ConsultDto response = consultService.getConsulById(consultId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
