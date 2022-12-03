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

import com.proyecto.Gpsi.Entity.TipoProd;
import com.proyecto.Gpsi.Service.TipoProdService;
import com.proyecto.Gpsi.Util.TpProdNotFountException;

@Controller
@RequestMapping("/views/tpProd")
public class TpProdController {

    @Autowired
    private TipoProdService service;

    @GetMapping("/listar")
	public String listUsers(Model model) {

        List<TipoProd> listTpProd = service.listTpProductos();
		model.addAttribute("listTpProd", listTpProd);
		model.addAttribute("title","Listar Tipo Productos");
		
		
		return "/views/tpProd/listar";
	}

	@GetMapping("/nuevo")
	public String nuevoTpProd(Model model) {
		model.addAttribute("tpProd",new TipoProd());
		model.addAttribute("title","Agregar nuevo Tipo Producto");
		return "/views/tpProd/frmTpProd";
	}

	@PostMapping("/save")
	public String guardarMarca(TipoProd tipoProd, RedirectAttributes ra) {
		service.save(tipoProd);
		ra.addFlashAttribute("message","El tipo de Producto ha sido guardado correctamente!");
		return "redirect:/views/tpProd/listar";
	}

	@GetMapping("/edit/{id}")
	public String editarForm(@PathVariable("id")Integer id,Model model,RedirectAttributes ra){
		try{
			TipoProd tpProd = service.get(id);
			model.addAttribute("tpProd",tpProd);
			model.addAttribute("title","Editar Marca (ID: "+ id+ ")");
			return "/views/tpProd/frmTpProd";
		} catch (TpProdNotFountException e){
			ra.addFlashAttribute("message","El tipo de Producto que busca no se encuentra!");
			return "redirect:/views/tpProd/listar";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteMarca(@PathVariable("id")Integer id,RedirectAttributes ra){
		try{
			service.delete(id);
		} catch (TpProdNotFountException e){
			ra.addFlashAttribute("message","El tipo de Producto que busca no se encuentra!");
		}
		return "redirect:/views/tpProd/listar";
	}
    
}
