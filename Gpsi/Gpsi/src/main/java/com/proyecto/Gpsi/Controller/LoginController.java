package com.proyecto.Gpsi.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Service.MarcaService;
import com.proyecto.Gpsi.Service.UsuarioServices;

@Controller
public class LoginController {

    @Autowired
    UsuarioServices service;

    @Autowired
    MarcaService marcaService;
    
    
    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login/login";
    }

    @GetMapping("/login_error")
    public String logerr(Model model){
        
            model.addAttribute("title","Error al inciar sesion");
            model.addAttribute("message","intentelo de nuevo.");
            
            return "Mensaje/mensaje";
    }

    @GetMapping("/users")
	public String listUsers(Model model) {
		List<Usuario> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

   

}
