package com.example.tarefa.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tarefa.entity.Tarefa;
import com.example.tarefa.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Tarefa save(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa alterar(Long id) {
      var result = tarefaRepository.findById(id);
      if(result.isPresent()){
        return result.get();
        }
        return new Tarefa();
    }
     public void finalizarTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefa.setStatus("Finalizada");
        tarefa.setDataConclusao(new Date()); // Atualiza a data de conclusão
        tarefaRepository.save(tarefa);
    }
}
