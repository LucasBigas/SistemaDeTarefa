package com.example.tarefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tarefa.service.TarefaService;
@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ModelAndView listarTarefas() {
        var listaTarefas = tarefaService.findAll();
        return new ModelAndView("listaTarefas", "listaTarefas", listaTarefas);
    }
}
