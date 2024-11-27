package com.example.tarefa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tarefa.entity.Tarefa;
import com.example.tarefa.service.EquipeService;
import com.example.tarefa.service.TarefaService;
@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public ModelAndView listarTarefas() {
        var listaTarefas = tarefaService.findAll();
        return new ModelAndView("listaTarefas", "listaTarefas", listaTarefas);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var tarefa = new Tarefa();
        var listaEquipe = equipeService.findAll();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tarefa", tarefa);
        map.put("listaEquipe", listaEquipe);
        return new ModelAndView("cadastroTarefa", map);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar (Tarefa tarefa) {
        tarefaService.save(tarefa);
        return new ModelAndView("redirect:/tarefa");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable ("id")long id){
        var listaEquipe = equipeService.findAll();
        var tarefa = tarefaService.alterar(id);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tarefa", tarefa);
        map.put("listaEquipe", listaEquipe);
        return new ModelAndView("cadastroTarefa", map);
    } 

     @PostMapping("/{id}/finalizar")
    public ModelAndView finalizarTarefa(@PathVariable Long id) {
        tarefaService.finalizarTarefa(id);
        return new ModelAndView("redirect:/tarefa"); // Redireciona para a lista de tarefas
    }
}
