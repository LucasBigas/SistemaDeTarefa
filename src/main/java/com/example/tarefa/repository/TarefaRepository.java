package com.example.tarefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tarefa.entity.Tarefa;
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
}
