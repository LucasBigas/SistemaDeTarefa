package com.example.SistemaTarefa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SistemaTarefa.Entity.Usuario;
import com.example.SistemaTarefa.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    // buscar todos os usuarios
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    // criar um novo usuario e salvar no banco

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //alterar um usuario
    public Usuario alterar(Long id){
        var resul = usuarioRepository.findById(id);
        if (resul.isPresent()) {
            return resul.get();
        }
        return new Usuario();
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    //LOGIN

    public Usuario login(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
