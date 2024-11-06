package com.example.SistemaTarefa.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.SistemaTarefa.Entity.Equipe;
import com.example.SistemaTarefa.Service.EquipeService;
import com.example.SistemaTarefa.Service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/equipe")
public class EquipeController {
    
    @Autowired
    private EquipeService equipeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView  lista() {
        var listaEquipes = equipeService.buscarTodos();
       return new ModelAndView("equipe", "listaEquipes", listaEquipes);
    }
    
    @GetMapping("/novo")
    public ModelAndView novo() {
        var equipe = new Equipe();
        var listaUsuarios = usuarioService.buscarTodos();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("equipe", equipe);
        hashMap.put("listaUsuarios", listaUsuarios);
        return new ModelAndView("equipeCadastro", hashMap);
    }

    @PostMapping("cadastrar")
    public ModelAndView salvar(@Valid Equipe equipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var listaUsuarios = usuarioService.buscarTodos();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("equipe", equipe);
            hashMap.put("listaUsuarios", listaUsuarios);
           return new ModelAndView("equipeCadastro", hashMap);
        }
        
        equipeService.salvar(equipe);
        return new ModelAndView("redirect:/equipe");
    }
}
