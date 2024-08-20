package com.example.apiponto.service;

import com.example.apiponto.models.Usuario;
import com.example.apiponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> loginUsuario(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario editar(Long id, Usuario usuarioAtualizado) throws Exception {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());

        return usuarioRepository.save(usuarioExistente);
    }
}
