package com.example.tarefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tarefa.entity.Equipe;
import com.example.tarefa.repository.EquipeRepository;

@Service
public class EquipeService {
    
    @Autowired
    private EquipeRepository equipeRepository;

    public List<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    public Equipe save(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe alterar(Long id) {
        var resul = equipeRepository.findById(id);
        if(resul.isPresent())
            return resul.get();
        return new Equipe();
    }

    public void delete(Long id) {
        equipeRepository.deleteById(id);
    }
}
