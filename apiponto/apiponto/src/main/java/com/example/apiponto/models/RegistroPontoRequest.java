package com.example.apiponto.models;

import lombok.Data;

@Data
public class RegistroPontoRequest {
    private Long usuarioId;
    private String tipo;
}
