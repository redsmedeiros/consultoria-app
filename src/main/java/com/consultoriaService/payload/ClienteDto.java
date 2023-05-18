package com.consultoriaService.payload;

import lombok.Data;

@Data
public class ClienteDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
