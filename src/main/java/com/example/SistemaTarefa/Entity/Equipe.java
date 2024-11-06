package com.example.SistemaTarefa.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da equipe é obrigatório")
    private String nomeEquipe;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private List<Usuario> pessoas;

    // Getters e setters
}
