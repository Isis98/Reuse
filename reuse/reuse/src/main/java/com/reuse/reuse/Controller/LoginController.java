package com.reuse.reuse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.reuse.reuse.Entity.Usuario;
import com.reuse.reuse.Service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Vista de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
    
    @PostMapping("/login")
    public String procesarLogin(
        @RequestParam  String username,
        @RequestParam String password,
        Model model) {

        username = username.trim();
        password = password.trim();

        Usuario usuario = usuarioService.buscarPorUsername(username);

        if (usuario != null) {
            System.out.println("PASSWORD BD: [" + usuario.getPassword() + "]");
        } else {
            System.out.println("USUARIO NO ENCONTRADO");
        }


        if (usuario != null && usuario.getPassword().equals(password)) {
            return "redirect:/inicio";
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }
    
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

}
