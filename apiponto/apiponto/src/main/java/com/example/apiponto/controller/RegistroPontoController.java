package com.example.apiponto.controller;

import com.example.apiponto.models.RegistroPonto;
import com.example.apiponto.models.RegistroPontoRequest;
import com.example.apiponto.models.Usuario;
import com.example.apiponto.repository.RegistroPontoRepository;
import com.example.apiponto.repository.UsuarioRepository;
import com.example.apiponto.service.RegistroPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pontos")
public class RegistroPontoController {

    @Autowired
    private RegistroPontoService registroPontoService;

    @PostMapping("/bater-ponto")
    public ResponseEntity<?> baterPonto(@RequestBody RegistroPontoRequest request) {
        try {
            RegistroPonto novoRegistroPonto = registroPontoService.baterPonto(request.getUsuarioId(), request.getTipo());
            return ResponseEntity.ok(novoRegistroPonto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{usuarioId}/historico")
    public ResponseEntity<List<RegistroPonto>> historicoPontos(@PathVariable Long usuarioId) {
        List<RegistroPonto> historico = registroPontoService.historicoPontos(usuarioId);
        return ResponseEntity.ok(historico);
    }
    @GetMapping
    public ResponseEntity<List<RegistroPonto>> listarTodos() {
        List<RegistroPonto> registros = registroPontoService.listarTodos();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroPonto> obterRegistro(@PathVariable Long id) {
        try {
            RegistroPonto registroPonto = registroPontoService.obterPorId(id);
            return ResponseEntity.ok(registroPonto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarRegistro(@PathVariable Long id, @RequestBody RegistroPontoRequest request) {
        try {
            RegistroPonto registroAtualizado = registroPontoService.atualizarRegistro(id, request);
            return ResponseEntity.ok(registroAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable Long id) {
        try {
            registroPontoService.deletarRegistro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
