package com.example.apiponto.repository;

import com.example.apiponto.models.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {
    // Método para encontrar todos os registros de ponto de um usuário específico
    List<RegistroPonto> findByUsuarioId(Long usuarioId);

}