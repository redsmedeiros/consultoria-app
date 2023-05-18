package com.consultoriaService.service;

import com.consultoriaService.payload.ClienteDto;

import java.util.List;

public interface ClienteService {
    
    ClienteDto createCliente(long consultingId, ClienteDto clienteDto);
    List<ClienteDto> getAllClientes(long consultingId);

    ClienteDto getClienteById(long consultingId, long clienteId);

    ClienteDto updateClienteByid(long consultingId, long clienteId, ClienteDto clienteDto);
}
