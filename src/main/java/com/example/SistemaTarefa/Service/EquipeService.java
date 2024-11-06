package com.example.SistemaTarefa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SistemaTarefa.Entity.Equipe;
import com.example.SistemaTarefa.Repository.EquipeRepository;

@Service
public class EquipeService {
    
    @Autowired
    private EquipeRepository equipeRepository;

    public List<Equipe> buscarTodos() {
        return equipeRepository.findAll();
    }

    public Equipe salvar(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe alterar(Long id) {
        var resul = equipeRepository.findById(id);
        if (resul.isPresent()) {
            return resul.get();
        }
        return new Equipe();
    }

    public void deletar(Long id) {
        equipeRepository.deleteById(id);
    }       

    public Equipe buscarPorId(Long id) {
        return equipeRepository.findById(id).orElse(null);
    }
   

}
