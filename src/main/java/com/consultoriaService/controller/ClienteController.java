package com.consultoriaService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.consultoriaService.payload.ClienteDto;
import com.consultoriaService.service.ClienteService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/consult/{consultingId}/cliente")
    public ResponseEntity<ClienteDto> createCliente(@PathVariable(value = "consultingId") Long consultingId, @Valid @RequestBody ClienteDto clienteDto){

        ClienteDto response = clienteService.createCliente(consultingId, clienteDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/consult/{consultingId}/cliente")
    public List<ClienteDto> getAllClientes(@PathVariable(value = "consultingId") long consultingId){

        List<ClienteDto> response = clienteService.getAllClientes(consultingId);

        return response;
    }

    @GetMapping("/consult/{consultingId}/cliente/{clienteId}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable(value = "consultingId") long consultingId, @PathVariable(value = "clienteId") long clienteId){

        ClienteDto response = clienteService.getClienteById(consultingId, clienteId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/consult/{consultingId}/cliente/{clienteId}")
    public ResponseEntity<ClienteDto> updatdeClienteById(@PathVariable(value = "consultingId") long consultingId, @PathVariable(value = "clienteId") long clienteId, @Valid @RequestBody ClienteDto clienteDto){

        ClienteDto response = clienteService.updateClienteByid(consultingId, clienteId, clienteDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/consult/{consultingId}/cliente/{clienteId}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "consultingId") long consultingId, @PathVariable(value = "clienteId") long clienteId){

        clienteService.deleteClienteByid(consultingId, clienteId);

        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
    }
    
}
