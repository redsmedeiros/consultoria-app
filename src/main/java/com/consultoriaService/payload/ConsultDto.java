package com.consultoriaService.payload;

import lombok.Data;

import java.util.Set;

@Data
public class ConsultDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String type;

}
