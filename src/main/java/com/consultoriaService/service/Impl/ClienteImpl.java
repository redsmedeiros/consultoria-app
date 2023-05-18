package com.consultoriaService.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoriaService.entity.Cliente;
import com.consultoriaService.entity.Consulting;
import com.consultoriaService.exception.ResourceNotFoundException;
import com.consultoriaService.payload.ClienteDto;
import com.consultoriaService.repository.ClienteRepository;
import com.consultoriaService.repository.ConsultRepository;
import com.consultoriaService.service.ClienteService;

@Service
public class ClienteImpl implements ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public ConsultRepository consultRepository;

    @Override
    public ClienteDto createCliente(long consultingId, ClienteDto clienteDto) {
       
        Cliente cliente = mapToEntity(clienteDto);

        Consulting consult = consultRepository.findById(consultingId).orElseThrow(()-> new ResourceNotFoundException("consultId", "id", consultingId));

        Cliente clientExist = clienteRepository.findByEmail(cliente.getEmail());

        if(clientExist != null){
            throw new ResourceNotFoundException(null, null, null);
        }

        cliente.setConsulting(consult);

        Cliente clienteSaved = clienteRepository.save(cliente);        

        return mapToDto(clienteSaved);
    }

    private Cliente mapToEntity(ClienteDto clienteDto){

        Cliente cliente = new Cliente();

        cliente.setName(clienteDto.getName());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setPhone(clienteDto.getPhone());

        return cliente;
    }

    private ClienteDto  mapToDto(Cliente cliente){

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setId(cliente.getId());
        clienteDto.setName(cliente.getName());
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setPhone(cliente.getPhone());

        return clienteDto;
    }
    
}
