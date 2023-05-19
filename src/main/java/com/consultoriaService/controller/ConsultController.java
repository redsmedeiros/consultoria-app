package com.consultoriaService.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consultoriaService.payload.ConsultDto;
import com.consultoriaService.payload.ConsultResponse;
import com.consultoriaService.service.ConsultService;
import com.consultoriaService.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/consult")
public class ConsultController {

    private ConsultService consultService;

    public ConsultController(ConsultService consultService){

        this.consultService = consultService;
    }

    @PostMapping
    public ResponseEntity<ConsultDto> createConsult(@Valid @RequestBody ConsultDto consultDto){

        ConsultDto response = consultService.createConsult(consultDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ConsultResponse> getAllConsults(
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy
    ){

        ConsultResponse response = consultService.getAllConsults(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDto> getConsultById(@PathVariable(name = "id") Long consultId){

        ConsultDto response = consultService.getConsulById(consultId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDto> updateConsult(@PathVariable(name = "id") Long consultId, @Valid @RequestBody ConsultDto consultDto){

        ConsultDto response = consultService.updateConsultById(consultId, consultDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") long consultId){

        consultService.deleteConsultById(consultId);

        return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
    }
    
}
