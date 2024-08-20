package com.example.apiponto.service;

import com.example.apiponto.dto.RegistroPontoDTO;
import com.example.apiponto.models.RegistroPonto;
import com.example.apiponto.models.RegistroPontoRequest;
import com.example.apiponto.repository.RegistroPontoRepository;
import com.example.apiponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroPontoService {


    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public RegistroPonto baterPonto(Long usuarioId, String tipo) throws Exception {
        List<RegistroPonto> historico = registroPontoRepository.findByUsuarioId(usuarioId);

        // Verificação se o usuário está tentando registrar uma saída antes de uma entrada
        if ("saida".equals(tipo) && (historico.isEmpty() || "saida".equals(historico.get(historico.size() - 1).getTipo()))) {
            throw new Exception("Não é possível registrar uma saída sem antes registrar uma entrada.");
        }

        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setUsuario(usuarioRepository.findById(usuarioId).orElseThrow(() -> new Exception("Usuário não encontrado.")));
        registroPonto.setTipo(tipo);
        registroPonto.setHorario(LocalDateTime.now());

        return registroPontoRepository.save(registroPonto);
    }

    public List<RegistroPonto> historicoPontos(Long usuarioId) {
        return registroPontoRepository.findByUsuarioId(usuarioId);
    }
    public List<RegistroPonto> listarTodos() {
        return registroPontoRepository.findAll();
    }

    public RegistroPonto obterPorId(Long id) throws Exception {
        return registroPontoRepository.findById(id).orElseThrow(() -> new Exception("Registro de ponto não encontrado."));
    }

    public RegistroPonto atualizarRegistro(Long id, RegistroPontoRequest request) throws Exception {
        RegistroPonto registroExistente = registroPontoRepository.findById(id)
                .orElseThrow(() -> new Exception("Registro de ponto não encontrado."));

        registroExistente.setTipo(request.getTipo());
        registroExistente.setHorario(LocalDateTime.now());

        return registroPontoRepository.save(registroExistente);
    }

    public void deletarRegistro(Long id) throws Exception {
        RegistroPonto registroExistente = registroPontoRepository.findById(id)
                .orElseThrow(() -> new Exception("Registro de ponto não encontrado."));

        registroPontoRepository.delete(registroExistente);
    }

}
