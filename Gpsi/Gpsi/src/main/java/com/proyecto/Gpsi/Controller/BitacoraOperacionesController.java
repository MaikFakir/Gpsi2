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

import com.proyecto.Gpsi.Entity.BitacoraOperaciones;
import com.proyecto.Gpsi.Entity.GestionEnvios;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Service.BitacoraOperacionesService;
import com.proyecto.Gpsi.Service.GestionEnviosService;
import com.proyecto.Gpsi.Service.UsuarioServices;
import com.proyecto.Gpsi.Util.BitacoraOperacionesNotFoundException;

@Controller
@RequestMapping("/views/bitacora_operaciones")
public class BitacoraOperacionesController {

    @Autowired
    private BitacoraOperacionesService service;

    @Autowired
    private GestionEnviosService service2;

    @GetMapping("/listar")
	public String listBitacoraOperaciones(Model model) {

        List<BitacoraOperaciones> listBitacoraOperaciones = service.listBitacoraOperaciones();

		model.addAttribute("listbitacoraOperaciones", listBitacoraOperaciones);
		model.addAttribute("title","Listar Bitacora Operaciones");
		
		
		return "/views/bitacora_operaciones/listar";
	}

    @GetMapping("/nueva")
	public String nuevaBitOperaciones(Model model) {
        
        List<BitacoraOperaciones> listBitOperaciones = service.listBitacoraOperaciones();
        List<GestionEnvios> listGestionEnvios = service2.listGestionEnvios();

		model.addAttribute("bitacoraOperaciones",new BitacoraOperaciones());
        model.addAttribute("gestionEnvios",listGestionEnvios);
		model.addAttribute("title","Agregar nueva Bitacora");

		return "/views/bitacora_operaciones/frmBitacoraOperaciones";
	}

    @PostMapping("/save")
	public String guardarBitacoraOperaciones(BitacoraOperaciones bitacoraOperaciones, RedirectAttributes ra) {
		
		service.save(bitacoraOperaciones);
		ra.addFlashAttribute("message", "La bitacora ha sido guardada correctamente!");
		return "redirect:/views/bitacora_operaciones/listar";
	}

    @GetMapping("/edit/{id}")
	public String editarForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		List<BitacoraOperaciones> listBitOperaciones = service.listBitacoraOperaciones();
        List<GestionEnvios> listGestionEnvios = service2.listGestionEnvios();
		try {
			BitacoraOperaciones bitacoraOperaciones = service.get(id);
			model.addAttribute("bitacoraOperaciones", bitacoraOperaciones);
			model.addAttribute("gestionEnvios",listGestionEnvios);
			model.addAttribute("title", "Editar Bitacora Envios (ID: " + id + ")");
			return "/views/bitacora_operaciones/frmBitacoraOperaciones";
		} catch (BitacoraOperacionesNotFoundException e) {
			ra.addFlashAttribute("message", "La gestion que busca no se encuentra!");
			return "/views/bitacora_operaciones/listar";
		}
	}

    @GetMapping("/delete/{id}")
	public String deleteMarca(@PathVariable("id")Integer id,RedirectAttributes ra){
		try{
			service.delete(id);
		} catch (BitacoraOperacionesNotFoundException e){
			ra.addFlashAttribute("message","El tipo de Producto que busca no se encuentra!");
		}
		return "redirect:/views/bitacora_operaciones/listar";
	}

}   
