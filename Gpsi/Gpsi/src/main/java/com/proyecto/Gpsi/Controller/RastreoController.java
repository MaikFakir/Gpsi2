package com.proyecto.Gpsi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.Gpsi.Entity.GestionEnvios;
import com.proyecto.Gpsi.Service.GestionEnviosService;

@Controller
@RequestMapping("/views/rastreo")
public class RastreoController {

    @Autowired
	private GestionEnviosService service;
    
    @GetMapping("/rastreo")
	public String rastrear(Model model) {
        model.addAttribute("title", "Rastree su pedido");
        model.addAttribute("alerta", "El numero de id debio ser asignado previamente.");
		return  "/views/rastreo/listar";
	}

    @GetMapping(value = "/busqueda")
    public String bucarporid(Model model, @RequestParam(value ="id",required = false)String id){
        try {
            Optional<GestionEnvios> gestionenvios = this.service.findById(id);
            model.addAttribute("pedido", gestionenvios);;
            model.addAttribute("title", "Mi pedido.");
            model.addAttribute("message","Se ha cargado correctamente su pedido.");
            return "/views/rastreo/resultado";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    
}
