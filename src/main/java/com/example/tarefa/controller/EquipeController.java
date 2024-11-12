package com.example.tarefa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tarefa.entity.Equipe;
import com.example.tarefa.service.EquipeService;
import com.example.tarefa.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/equipe")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView lista(){
       var equipes = equipeService.findAll();
       return new ModelAndView("listaEquipes", "equipes", equipes);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var equipe = new Equipe();
        var listaUsuarios = usuarioService.findAll();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("equipe", equipe);
        map.put("usuarios", listaUsuarios);
        return new ModelAndView("equipe", map);
        
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid Equipe equipe, BindingResult result){
        if(result.hasErrors()){
        var usuarios = usuarioService.findAll();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("equipe", equipe);
        map.put("usuarios", usuarios);
        return new ModelAndView("equipe", map); 
        }
        equipeService.save(equipe);
        return new ModelAndView("redirect:/equipe");
    }

    //alterar
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable ("id")long id){
        var equipe = equipeService.alterar(id);
        var listaUsuarios = usuarioService.findAll();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("equipe", equipe);
        map.put("usuarios", listaUsuarios);
        return new ModelAndView("equipe", map);
    }

    //deletar
    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable ("id")long id){
        equipeService.delete(id);
        return new ModelAndView("redirect:/equipe");
    }
}

