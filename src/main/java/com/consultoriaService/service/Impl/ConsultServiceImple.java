package com.consultoriaService.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.consultoriaService.entity.Consulting;
import com.consultoriaService.exception.ResourceNotFoundException;
import com.consultoriaService.payload.ConsultDto;
import com.consultoriaService.repository.ConsultRepository;
import com.consultoriaService.service.ConsultService;

@Service
public class ConsultServiceImple implements ConsultService {

    private ConsultRepository consultRepository;

    public ConsultServiceImple(ConsultRepository consultRepository){
        
        this.consultRepository = consultRepository;
    }    

    @Override
    public ConsultDto createConsult(ConsultDto consultDto) {

        Consulting consult = matToEntity(consultDto);

        Consulting consultSaved = consultRepository.save(consult);
       
        return mapToDto(consultSaved);
    }

    @Override
    public List<ConsultDto> getAllConsults() {
       
        List<Consulting> consults = consultRepository.findAll();

        List<ConsultDto> consultsResponse = consults.stream().map( consult -> mapToDto(consult)).collect(Collectors.toList());

        return consultsResponse;
    }

    @Override
    public ConsultDto getConsulById(Long consultId) {
        
        Consulting consult = consultRepository.findById(consultId).orElseThrow(()-> new ResourceNotFoundException("consultId", "id", consultId));

        return mapToDto(consult);
    }

    @Override
    public ConsultDto updateConsultById(Long consultId, ConsultDto consultDto) {
        
        Consulting consulting = consultRepository.findById(consultId).orElseThrow(()-> new ResourceNotFoundException("consultId", "id", consultId));

        

        consulting.setName(consultDto.getName());
        consulting.setEmail(consultDto.getEmail());
        consulting.setPhone(consultDto.getPhone());
        consulting.setType(consultDto.getType());

        Consulting updateConsult = consultRepository.save(consulting);

        return mapToDto(updateConsult);
    }

    Consulting matToEntity(ConsultDto consultDto){

        Consulting consulting = new Consulting();

        consulting.setName(consultDto.getName());
        consulting.setPhone(consultDto.getPhone());
        consulting.setType(consultDto.getType());
        consulting.setEmail(consultDto.getEmail());

        return consulting;
    }

    ConsultDto mapToDto(Consulting consult){

        ConsultDto consultDto = new ConsultDto();

        consultDto.setId(consult.getId());
        consultDto.setEmail(consult.getEmail());
        consultDto.setPhone(consult.getPhone());
        consultDto.setType(consult.getType());
        consultDto.setName(consult.getName());

        return consultDto;
    }

   

   

    
    
}
