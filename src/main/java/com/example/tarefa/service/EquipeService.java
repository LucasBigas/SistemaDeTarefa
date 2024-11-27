package com.example.tarefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tarefa.entity.Equipe;
import com.example.tarefa.entity.Usuario;
import com.example.tarefa.repository.EquipeRepository;
import com.example.tarefa.repository.UsuarioRepository;


@Service
public class EquipeService {
    
    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    public Equipe save(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

   // Método para adicionar um usuário à equipe
   public void adicionarUsuario(Long equipeId, Long usuarioId) {
    Equipe equipe = equipeRepository.findById(equipeId)
            .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
    Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    // Adicionar o usuário à lista de usuários da equipe
    equipe.getUsuarios().add(usuario);
    equipeRepository.save(equipe); // Salvar a equipe com a nova lista
}

// Método para remover um usuário da equipe
public void removerUsuario(Long equipeId, Long usuarioId) {
    Equipe equipe = equipeRepository.findById(equipeId)
            .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));

    // Remover o usuário da lista
    equipe.getUsuarios().removeIf(usuario -> usuario.getId().equals(usuarioId));
    equipeRepository.save(equipe); // Salvar a equipe com a lista atualizada
}

    public void delete(Long id) {
        equipeRepository.deleteById(id);
    }
}
