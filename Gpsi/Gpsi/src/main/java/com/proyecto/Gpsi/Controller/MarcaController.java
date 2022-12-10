package com.proyecto.Gpsi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Service.MarcaService;
import com.proyecto.Gpsi.Service.ExcelUploadService;
import com.proyecto.Gpsi.Util.MarcaNotFoundException;

@Controller
@RequestMapping("/views/marcas")
public class MarcaController {

	@Autowired
    private MarcaService service;

	@Autowired
    private ExcelUploadService service2;

	@GetMapping("/listar")
	public String listMarca(Model model) {

        List<Marca> listMarcas = service.listMarcas();
		model.addAttribute("listMarcas", listMarcas);
		model.addAttribute("title","Listar Marca");
		
		
		return "/views/marcas/listar";
	}

	@GetMapping("/nueva")
	public String nuevaMarca(Model model) {
		model.addAttribute("marca",new Marca());
		model.addAttribute("title","Agregar nueva Marca");
		return "/views/marcas/frmMarca";
	}

	@PostMapping("/save")
	public String guardarMarca(Marca marca, RedirectAttributes ra) {
		service.save(marca);
		ra.addFlashAttribute("message","La marca ha sido guardado correctamente!");
		return "redirect:/views/marcas/listar";
	}

	@GetMapping("/edit/{id}")
	public String editarForm(@PathVariable("id")Integer id,Model model,RedirectAttributes ra){
		try{
			Marca marca = service.get(id);
			model.addAttribute("marca",marca);
			model.addAttribute("title","Editar Marca (ID: "+ id+ ")");
			return "/views/marcas/frmMarca";
		} catch (MarcaNotFoundException e){
			ra.addFlashAttribute("message","La marca que busca no se encuentra!");
			return "redirect:/views/marcas/listar";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteMarca(@PathVariable("id")Integer id,RedirectAttributes ra){
		try{
			service.delete(id);
		} catch (MarcaNotFoundException e){
			ra.addFlashAttribute("message","La marca que busca no se encuentra!");
		} catch (Exception e){
			ra.addFlashAttribute("error","No puede eliminar una Marca que ya fue asignada a otras tablas!");
		}
		return "redirect:/views/marcas/listar";
	}

	@PostMapping("/import")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model,RedirectAttributes ra) {
		try {
			service2.storeMarca(file);
			ra.addFlashAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
		}
        return "redirect:/views/marcas/listar";
    }


}
