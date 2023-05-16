package com.consultoriaService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
}
