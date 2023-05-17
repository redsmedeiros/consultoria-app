package com.consultoriaService.service;

import java.util.List;

import com.consultoriaService.payload.ConsultDto;

public interface ConsultService {
    
    ConsultDto createConsult(ConsultDto consultDto);

    List<ConsultDto> getAllConsults(int pageNo, int pageSize);

    ConsultDto getConsulById(Long consultId);

    ConsultDto updateConsultById(Long consultId, ConsultDto consultDto);

    void deleteConsultById(Long consultId);
}
