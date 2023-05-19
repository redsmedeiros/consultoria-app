package com.consultoriaService.payload;

import lombok.Data;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
public class ConsultDto {
    
    private Long id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String phone;
    
    @Email
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String type;

}
