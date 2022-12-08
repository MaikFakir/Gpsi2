package com.proyecto.Gpsi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.Gpsi.Entity.Enrutador;
import com.proyecto.Gpsi.Entity.GestionEnvios;
import com.proyecto.Gpsi.Service.EnrutadorService;
import com.proyecto.Gpsi.Service.GestionEnviosService;
import com.proyecto.Gpsi.Util.EnrutadorNotFoundException;

@Controller
@RequestMapping("/views/enrutador")
public class EnrutadorController {

    @Autowired
    private EnrutadorService service;

    @Autowired
    private GestionEnviosService service2;


    @GetMapping("/listar")
	public String listEnrutador(Model model) {

		List<Enrutador> listRutas = service.listEnrutador();


		model.addAttribute("listRutas", listRutas);
		model.addAttribute("title", "Listar Rutas");

		return "/views/enrutador/listar";
	}

    @GetMapping("/nueva")
	public String nuevaRuta(Model model) {

		List<GestionEnvios> listGestionEnvios = service2.listGestionEnvios();

		model.addAttribute("ruta", new Enrutador());
		model.addAttribute("gestionenvios", listGestionEnvios);
		model.addAttribute("title", "Agregar nueva ruta");

		return "/views/enrutador/frmEnrutador";
	}
    @PostMapping("/save")
	public String guardarRuta(Enrutador enrutador, RedirectAttributes ra) {
		
		service.save(enrutador);
		ra.addFlashAttribute("message", "La ruta ha sido guardada correctamente!");
		return "redirect:/views/enrutador/listar";
	}

    @GetMapping("/edit/{id}")
	public String editarForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {

		List<GestionEnvios> listGestionEnvios = service2.listGestionEnvios();
		try {
			Enrutador enrutador = service.get(id);
			model.addAttribute("ruta", enrutador);
			model.addAttribute("gestionenvios", listGestionEnvios);
			model.addAttribute("title", "Editar Gestion Envios (ID: " + id + ")");
			return "/views/enrutador/frmEnrutador";
		} catch (EnrutadorNotFoundException e) {
			ra.addFlashAttribute("message", "La ruta que busca no se encuentra!");
			return "redirect:/views/enrutador/listar";
		}
	}

    @GetMapping("/delete/{id}")
	public String deleteMarca(@PathVariable("id")Integer id,RedirectAttributes ra){
		try{
			service.delete(id);
		} catch (EnrutadorNotFoundException e){
			ra.addFlashAttribute("message","la ruta no se encuentra!");
		}
		return "redirect:/views/enrutador/listar";
	}
    
}
