package com.example.SistemaTarefa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaTarefa.Entity.Equipe;
@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    
}
