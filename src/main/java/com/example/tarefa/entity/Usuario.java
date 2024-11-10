package com.example.tarefa.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome completo é obrigatório")
    private String nomeCompleto;
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Sexo é obrigatório")
    private String sexo;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento não pode ser nula")
    private Date dataNascimento;
    @NotBlank(message = "RG é obrigatório")
    private String rg;
    @NotBlank(message = "Senha é obrigatório")
    private String senha;
    
}
