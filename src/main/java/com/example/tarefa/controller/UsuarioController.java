package com.example.tarefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tarefa.entity.Usuario;
import com.example.tarefa.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView lista(){
        var usuarios = usuarioService.findAll();
        return new ModelAndView("listaUsuarios", "usuarios", usuarios);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var usuario = new Usuario();
        return new ModelAndView("cadastro", "usuario", usuario);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("cadastro", "usuario", usuario);
        }
        usuarioService.save(usuario);
        return new ModelAndView("redirect:/usuario");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable ("id")long id){
        var usuario = usuarioService.alterar(id);
        return new ModelAndView("cadastro", "usuario", usuario);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable ("id")long id){
        usuarioService.delete(id);
        return new ModelAndView("redirect:/usuario");
    }
}
