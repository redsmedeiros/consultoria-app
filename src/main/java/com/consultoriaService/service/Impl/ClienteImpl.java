package com.consultoriaService.service.Impl;

import com.consultoriaService.exception.ConsultApiException;
import com.consultoriaService.exception.EmailAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consultoriaService.entity.Cliente;
import com.consultoriaService.entity.Consulting;
import com.consultoriaService.exception.ResourceNotFoundException;
import com.consultoriaService.payload.ClienteDto;
import com.consultoriaService.repository.ClienteRepository;
import com.consultoriaService.repository.ConsultRepository;
import com.consultoriaService.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ClienteDto> getAllClientes(long consultingId) {

        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteDto> response = clientes.stream().map(cliente -> mapToDto(cliente)).collect(Collectors.toList());

        return response;
    }

    @Override
    public ClienteDto getClienteById(long consultingId, long clienteId) {

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(()-> new ResourceNotFoundException("clienteId", "id", clienteId));

        Consulting consulting = consultRepository.findById(consultingId).orElseThrow(()-> new ResourceNotFoundException("consultingId", "id", consultingId));

        if(!cliente.getConsulting().getId().equals(consulting.getId())){
            throw new ConsultApiException(HttpStatus.NOT_FOUND, "Consultor não encontrado");
        }

        return mapToDto(cliente);
    }

    @Override
    public ClienteDto updateClienteByid(long consultingId, long clienteId, ClienteDto clienteDto) {

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(()-> new ResourceNotFoundException("consultingID", "id", consultingId));

        Consulting consulting = consultRepository.findById(consultingId).orElseThrow(()-> new ResourceNotFoundException("consultingId", "id", consultingId));

        if(!cliente.getConsulting().getId().equals(consulting.getId())){
            throw new ConsultApiException(HttpStatus.NOT_FOUND, "Consultor não encontrado");
        }

        Cliente clienteExist = clienteRepository.findByEmail(clienteDto.getEmail());

        if(clienteExist != null){
            throw new EmailAlreadyExists("Email já cadastrado");
        }

        if(clienteDto.getName() != null){
            cliente.setName(clienteDto.getName());
        }

        if(clienteDto.getEmail() != null){
            cliente.setEmail(clienteDto.getEmail());
        }

        if(clienteDto.getPhone() != null){
            cliente.setPhone(cliente.getPhone());
        }

        Cliente clienteUpdated = clienteRepository.save(cliente);

        return mapToDto(clienteUpdated);
    }

    @Override
    public void deleteClienteByid(long consultingId, long clienteId) {

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(()-> new ResourceNotFoundException("deleteId", "id", clienteId));

        Consulting consulting = consultRepository.findById(consultingId).orElseThrow(()-> new ResourceNotFoundException("consultingId", "id", consultingId));

        if(!cliente.getConsulting().getId().equals(consulting.getId())){
            throw new ConsultApiException(HttpStatus.NOT_FOUND, "COnsultor não encontrado");
        }

        clienteRepository.deleteById(clienteId);
    }

    private Cliente mapToEntity(ClienteDto clienteDto){

        Cliente cliente = new Cliente();

        cliente.setId(clienteDto.getId());
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
