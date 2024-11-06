package com.example.SistemaTarefa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.SistemaTarefa.Entity.Usuario;
import com.example.SistemaTarefa.Service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    //listar todos usuarios model and view
    @GetMapping()
    public ModelAndView listar(){
        var listaUsuarios = usuarioService.buscarTodos();
        return new ModelAndView("usuario", "listaUsuarios", listaUsuarios);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    
    
    
    
    //criar e salvar o usuário
    @GetMapping("/novo")
    public ModelAndView novo() {
        var usuario = new Usuario();
        return new ModelAndView("cadastro", "usuario", usuario);
    }

    // cadastro
    @PostMapping("cadastro")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("cadastro", "usuario", usuario);
        }
        usuarioService.salvar(usuario);
        return new ModelAndView("redirect:/usuario/login");
    }

    //alterar o usuário
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        var usuario = usuarioService.alterar(id);
        return new ModelAndView("cadastro", "usuario", usuario);
    }

    //deletar o usuário
    @GetMapping("/delete/{id}")
    public ModelAndView deletar(@PathVariable("id") Long id) {
        usuarioService.deletar(id);
        return new ModelAndView("redirect:/usuario");
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Usuario obterUsuario(@PathVariable("id") Long id) {
        return usuarioService.buscarPorId(id); // Retorna um objeto Usuario como JSON
    }
}
