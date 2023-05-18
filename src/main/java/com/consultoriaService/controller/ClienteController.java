package com.consultoriaService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoriaService.payload.ClienteDto;
import com.consultoriaService.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    public ResponseEntity<ClienteDto> createCliente(Long consultingId, ClienteDto clienteDto){

        ClienteDto response = clienteService.createCliente(consultingId, clienteDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
}
