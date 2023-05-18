package com.consultoriaService.service.Impl;

import com.consultoriaService.entity.Cliente;
import com.consultoriaService.payload.ClienteDto;
import com.consultoriaService.service.ClienteService;

public class ClienteImpl implements ClienteService {

    @Override
    public ClienteDto createCliente(long consultingId, ClienteDto clienteDto) {
       
        Cliente cliente = mapToEntity(clienteDto);


        return null;
    }

    private Cliente mapToEntity(ClienteDto clienteDto){

        Cliente cliente = new Cliente();

        cliente.setName(clienteDto.getName());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setPhone(clienteDto.getPhone());

        return cliente;
    }
    
}
