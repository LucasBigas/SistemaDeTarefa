package com.example.tarefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tarefa.entity.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    
}
