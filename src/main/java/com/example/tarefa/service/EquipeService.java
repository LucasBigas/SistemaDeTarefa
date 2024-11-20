package com.example.tarefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tarefa.entity.Equipe;
import com.example.tarefa.entity.Usuario;
import com.example.tarefa.repository.EquipeRepository;


@Service
public class EquipeService {
    
    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    public Equipe save(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

   public Equipe addUsuario(Long equipeId, Long usuarioId) {
        Equipe equipe = equipeRepository.findById(equipeId).orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada"));
        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        equipe.getUsuarios().add(usuario);
        return equipeRepository.save(equipe);
    }

    public Equipe removeUsuario(Long equipeId, Long usuarioId) {
        Equipe equipe = equipeRepository.findById(equipeId).orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada"));
        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        equipe.getUsuarios().remove(usuario);
        return equipeRepository.save(equipe);
    }

    public void delete(Long id) {
        equipeRepository.deleteById(id);
    }
}
