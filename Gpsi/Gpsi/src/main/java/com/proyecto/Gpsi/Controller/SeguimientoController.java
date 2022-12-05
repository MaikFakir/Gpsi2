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
import com.proyecto.Gpsi.Entity.Seguimiento;
import com.proyecto.Gpsi.Service.EnrutadorService;
import com.proyecto.Gpsi.Service.SeguimientoService;
import com.proyecto.Gpsi.Util.SeguimientoNotFoundException;

@Controller
@RequestMapping("/views/seguimiento")
public class SeguimientoController {

    @Autowired
    private SeguimientoService service;

    @Autowired
    private EnrutadorService service2;
    
    @GetMapping("/listar")
	public String listSeguimiento(Model model) {

		List<Seguimiento> listSeguimiento = service.listSeguimiento();

		model.addAttribute("listSeguimiento", listSeguimiento);
		model.addAttribute("title", "Listar Seguimiento");

		return "/views/seguimiento/listar";
	}

    @GetMapping("/nueva")
	public String nuevoSeguimiento(Model model) {

		List<Enrutador> listRuta = service2.listEnrutador();

		model.addAttribute("seguimiento", new Seguimiento());
		model.addAttribute("enrutador", listRuta);
		model.addAttribute("title", "Agregar seguimiento");

		return "/views/seguimiento/frmSeguimiento";
	}

    @PostMapping("/save")
	public String guardarRuta(Seguimiento seguimiento, RedirectAttributes ra) {
		
		service.save(seguimiento);
		ra.addFlashAttribute("message", "Seguimiento guardado correctamente!");
		return "redirect:/views/seguimiento/listar";
	}

    @GetMapping("/edit/{id}")
	public String editarForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {

		List<Enrutador> listRuta = service2.listEnrutador();
		try {
            Seguimiento seguimiento = service.get(id);
            model.addAttribute("seguimiento",seguimiento);
		    model.addAttribute("enrutador", listRuta);
			model.addAttribute("title", "Editar Seguimiento (ID: " + id + ")");
			return "/views/seguimiento/frmSeguimiento";
		} catch (SeguimientoNotFoundException e) {
			ra.addFlashAttribute("message", "El seguimiento que busca no se encuentra!");
			return "redirect:/views/seguimiento/listar";
		}
	}

}
