package com.consultoriaService.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<ConsultDto> getAllConsults(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
       
        Page<Consulting> consults = consultRepository.findAll(pageable);

        List<Consulting> listOfConsultings = consults.getContent();

        List<ConsultDto> consultsResponse = listOfConsultings.stream().map( consult -> mapToDto(consult)).collect(Collectors.toList());

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

        if(consultDto.getEmail() != null){

            Consulting consultExist = consultRepository.findByEmail(consultDto.getEmail());

            if(consultExist != null){
                
                throw new ResourceNotFoundException("comentId", "id", consultId);
            }

            consulting.setEmail(consultDto.getEmail());
        }

        if(consultDto.getName() != null){

            consulting.setName(consultDto.getName());
        }

        if(consultDto.getPhone() != null){

            consulting.setPhone(consultDto.getPhone());
        }
        
        if(consultDto.getType() != null){

            consulting.setType(consultDto.getType());
        }
        
        Consulting updateConsult = consultRepository.save(consulting);

        return mapToDto(updateConsult);
    }

    @Override
    public void deleteConsultById(Long consultId) {
        
        Consulting consult = consultRepository.findById(consultId).orElseThrow(()-> new ResourceNotFoundException("cosultID", "consultId", consultId));

        consultRepository.deleteById(consultId);
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
