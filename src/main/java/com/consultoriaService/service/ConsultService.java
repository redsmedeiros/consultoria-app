package com.consultoriaService.service;

import java.util.List;

import com.consultoriaService.payload.ConsultDto;
import com.consultoriaService.payload.ConsultResponse;

public interface ConsultService {
    
    ConsultDto createConsult(ConsultDto consultDto);

    ConsultResponse getAllConsults(int pageNo, int pageSize, String sortBy);

    ConsultDto getConsulById(Long consultId);

    ConsultDto updateConsultById(Long consultId, ConsultDto consultDto);

    void deleteConsultById(Long consultId);
}
