package com.proyecto.Gpsi.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.Gpsi.Entity.GestionEnvios;
import com.proyecto.Gpsi.Entity.TipoProd;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Repository.GestionEnviosRepository2;
import com.proyecto.Gpsi.Security.CustomUserDetails;
import com.proyecto.Gpsi.Service.GestionEnviosService;
import com.proyecto.Gpsi.Service.TipoProdService;
import com.proyecto.Gpsi.Service.UsuarioServices;
import com.proyecto.Gpsi.Util.GestionEnviosNotFoundException;

@Controller
@RequestMapping("/views/gestion_envios")
public class GestionEnviosController {

	@Autowired
	private GestionEnviosService service;
	@Autowired
	private GestionEnviosRepository2 repo;

	@Autowired
	private TipoProdService service2;

	@Autowired
	private UsuarioServices service3;

	@GetMapping("/listar")
	public String listGestionEnvios(Model model) {

		List<GestionEnvios> listGestionEnvios = service.listGestionEnvios();
		model.addAttribute("listGestionEnvios", listGestionEnvios);
		model.addAttribute("title", "Listar Gestion Envios");

		return "/views/gestion_envios/listar";
	
	}

	@GetMapping(value = "/Mis_Pedidos")
    public String bucarporid(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model,Integer id){
        try {

			id =loggedUser.getIdentificacion();

            List<GestionEnvios> gestionenvios = this.service.findByIdEnvios(id);
            model.addAttribute("pedido", gestionenvios);
            model.addAttribute("title", "Mi pedido.");
            model.addAttribute("message","Se ha cargado correctamente su pedido.");
            return "/views/dashboard/mispedidos";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

	@GetMapping("/nueva")
	public String nuevaGestionEnvios(Model model) {

		List<TipoProd> listTpProd = service2.listTpProductos();
		List<Usuario> listUsuario = service3.listAll();

		model.addAttribute("gestionenvios", new GestionEnvios());
		model.addAttribute("tpProd", listTpProd);
		model.addAttribute("usuarios", listUsuario);
		model.addAttribute("title", "Agregar nuevo envio");

		return "/views/gestion_envios/frmGestionEnvios";
	}

	@PostMapping("/save")
	public String guardarGestion(GestionEnvios gestionEnvios, RedirectAttributes ra) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTimeString = now.format(formatter);

		gestionEnvios.setFechacreacion(dateTimeString);
		gestionEnvios.setFechaRecibido("0000-00-00 00:00");
		service.save(gestionEnvios);
		ra.addFlashAttribute("message", "La gestion ha sido guardada correctamente!");
		return "redirect:/views/gestion_envios/listar";
	}

	@PostMapping("/save2")
	public String guardarGestion2(GestionEnvios gestionEnvios, RedirectAttributes ra) {

		service.save(gestionEnvios);
		ra.addFlashAttribute("message", "La gestion ha sido guardada correctamente!");
		return "redirect:/views/gestion_envios/listar";
	}

	@PostMapping("/save3")
	public String entregarGestion3(GestionEnvios gestionEnvios, RedirectAttributes ra) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTimeString = now.format(formatter);
		gestionEnvios.setFechaRecibido(dateTimeString);

		service.save(gestionEnvios);
		ra.addFlashAttribute("message", "La gestion ha sido entregada correctamente!");
		return "redirect:/views/gestion_envios/listar";
	}

	@PostMapping("/save4")
	public String entregarGestion4(GestionEnvios gestionEnvios, RedirectAttributes ra) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTimeString = now.format(formatter);

		gestionEnvios.setFechacreacion(dateTimeString);
		gestionEnvios.setFechaRecibido("0000-00-00 00:00");
		service.save(gestionEnvios);
		ra.addFlashAttribute("message", "La gestion ha sido guardada correctamente!");
		return "redirect:/views/dashboard/Mis_Pedidos";
	}

	@GetMapping("/entregar/{id}")
	public String entregar(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		List<TipoProd> listTpProd = service2.listTpProductos();
		List<Usuario> listUsuario = service3.listAll();
		try {
			GestionEnvios gestionEnvios = service.get(id);
			model.addAttribute("gestionenvios", gestionEnvios);
			model.addAttribute("tpProd", listTpProd);
			model.addAttribute("usuarios", listUsuario);
			model.addAttribute("title", "Editar Gestion Envios (ID: " + id + ")");
			return entregarGestion3(gestionEnvios, ra);
		} catch (GestionEnviosNotFoundException e) {
			ra.addFlashAttribute("message", "La gestion que busca no se encuentra!");
			return "redirect:/views/gestion_envios/listar";
		}
	}

	@GetMapping("/edit2/{id}")
	public String editarForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		List<TipoProd> listTpProd = service2.listTpProductos();
		List<Usuario> listUsuario = service3.listAll();
		try {
			GestionEnvios gestionEnvios = service.get(id);
			model.addAttribute("gestionenvios", gestionEnvios);
			model.addAttribute("tpProd", listTpProd);
			model.addAttribute("usuarios", listUsuario);
			model.addAttribute("title", "Editar Gestion Envios (ID: " + id + ")");
			return "/views/gestion_envios/frmGestionEnviosED";
		} catch (GestionEnviosNotFoundException e) {
			ra.addFlashAttribute("message", "La gestion que busca no se encuentra!");
			return "redirect:/views/gestion_envios/listar";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteGestionEnvio(@PathVariable("id") Integer id, RedirectAttributes ra) {
		try {
			service.delete(id);
		} catch (GestionEnviosNotFoundException e) {
			ra.addFlashAttribute("message", "La Gestion que busca no se encuentra!");
		} catch (Exception e) {
			ra.addFlashAttribute("error", "No puede eliminar una Marca que ya fue asignada a otras tablas!");
		}
		return "redirect:/views/gestion_envios/listar";
	}

}
