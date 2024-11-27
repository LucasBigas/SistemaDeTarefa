package com.example.tarefa.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.tarefa.entity.Equipe;
import com.example.tarefa.entity.Usuario;
import com.example.tarefa.repository.EquipeRepository;
import com.example.tarefa.repository.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EquipeRepository equipeRepository;

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

    
   // Endpoint para adicionar um usuário à equipe
    @PostMapping("/adicionar-usuario")
    public String adicionarUsuario(@RequestParam Long equipeId, @RequestParam Long usuarioId) {
        equipeService.adicionarUsuario(equipeId, usuarioId);
        return "redirect:/equipe/" + equipeId; // Redirecionar para a página da equipe
    }

    // Endpoint para remover um usuário da equipe
    @PostMapping("/remover-usuario")
    public String removerUsuario(@RequestParam Long equipeId, @RequestParam Long usuarioId) {
        equipeService.removerUsuario(equipeId, usuarioId);
        return "redirect:/equipe/" + equipeId; // Redirecionar para a página da equipe
    }

    // Endpoint para exibir a página de uma equipe
    @GetMapping("/{id}")
    public String listarUsuariosDaEquipe(@PathVariable Long id, Model model) {
        Equipe equipe = equipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
        List<Usuario> usuariosDisponiveis = usuarioRepository.findAll(); // Todos os usuários

        // Remover os usuários já associados à equipe da lista de disponíveis
        usuariosDisponiveis.removeAll(equipe.getUsuarios());

        model.addAttribute("equipe", equipe);
        model.addAttribute("usuariosDisponiveis", usuariosDisponiveis);

        return "listaUsuarioEquipe"; // Nome do template Thymeleaf
    }

    //deletar
    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable ("id")long id){
        equipeService.delete(id);
        return new ModelAndView("redirect:/equipe");
    }
}

