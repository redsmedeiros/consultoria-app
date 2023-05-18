package com.consultoriaService.service;

import com.consultoriaService.payload.ClienteDto;

public interface ClienteService {
    
    ClienteDto createCliente(long consultingId, ClienteDto clienteDto);
}
