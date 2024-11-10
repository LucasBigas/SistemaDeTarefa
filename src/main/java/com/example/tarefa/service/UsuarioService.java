package com.example.tarefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.tarefa.entity.Usuario;
import com.example.tarefa.repository.UsuarioRepository;

@Service
public class UsuarioService {
 
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }
    //metodo salvar
    public Usuario save(Usuario usuario){
        return repository.save(usuario);
    }
    //metodo alterar
    public Usuario alterar(Long id){
        var resul = repository.findById(id);
        if(resul.isPresent()){
            return resul.get();
        }
        return new Usuario();

    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
