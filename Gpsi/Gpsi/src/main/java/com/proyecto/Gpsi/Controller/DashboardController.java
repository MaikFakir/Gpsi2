package com.proyecto.Gpsi.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.Gpsi.Entity.GestionEnvios;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Repository.GestionEnviosRepository2;
import com.proyecto.Gpsi.Security.CustomUserDetails;
import com.proyecto.Gpsi.Service.GestionEnviosService;
import com.proyecto.Gpsi.Service.UsuarioServices;

@Controller
@RequestMapping("/views/dashboard")
public class DashboardController {

	@Autowired
	private GestionEnviosService service;

	@Autowired
     private UsuarioServices service2;

	@GetMapping({"/inicio"})
	public String index(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model,Integer id) throws Exception {
		id =loggedUser.getIdentificacion();
		String nombre = loggedUser.getFullName();
		List<GestionEnvios> listGestionEnvios = service.findByIdEnvios(id);
		List<GestionEnvios> listGestionEnvios2 = service.listGestionEnvios();
		model.addAttribute("listGestionEnvios", listGestionEnvios);
		model.addAttribute("listGestionEnvios22", listGestionEnvios2);
		model.addAttribute("message","Bienvenid@!"+nombre);

		return "/views/dashboard/inicio";
	}

	@PostMapping("/save2")
     public String guardarUsuario2(Usuario usuario, Model model) {
         service2.save2(usuario);
         return "redirect:/views/dashboard/inicio";
     }

	

}
