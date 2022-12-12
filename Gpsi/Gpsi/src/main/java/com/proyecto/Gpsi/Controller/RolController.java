package com.proyecto.Gpsi.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.http.ResponseEntity;

import com.proyecto.Gpsi.Entity.Rol;
import com.proyecto.Gpsi.Repository.RolRepository;
import com.proyecto.Gpsi.Service.ExcelUploadService;
import com.proyecto.Gpsi.Service.RolServicio;
import com.proyecto.Gpsi.Util.RolNotFoundException;

@Controller
@RequestMapping("/views/roles")
public class RolController {

    @Autowired
    private RolServicio service;

	@Autowired
    private ExcelUploadService service2;

    @GetMapping("/listar")
	public String listRoles(Model model) {

        List<Rol> listRoles = service.listRoles();
		model.addAttribute("listRol", listRoles);
		model.addAttribute("title","Listar Roles");

		return "/views/roles/listar";
	}
    
    @GetMapping("/nueva")
	public String nuevoRol(Model model) {
		model.addAttribute("roles",new Rol());
		model.addAttribute("title","Agregar nueva Rol");
		return "/views/roles/frmRol";
	}

    @PostMapping("/save")
	public String guardarMarca(Rol rol, RedirectAttributes ra) {
		service.save(rol);
		ra.addFlashAttribute("message","El rol ha sido guardado correctamente!");
		return "redirect:/views/roles/listar";
	}

    @GetMapping("/edit/{id}")
	public String editarForm(@PathVariable("id")Integer id,Model model,RedirectAttributes ra){
		try{
			Rol rol = service.get(id);
			model.addAttribute("roles",rol);
			model.addAttribute("title","Editar Rol (ID: "+ id+ ")");
			return "/views/roles/frmRol";
		} catch (RolNotFoundException e){
			ra.addFlashAttribute("message","La Rol que busca no se encuentra!");
			return "redirect:/views/roles/listar";
		}


	}

    @GetMapping("/delete/{id}")
	public String deleteRol(@PathVariable("id")Integer id,RedirectAttributes ra){
		try{
			service.delete(id);
		} catch (RolNotFoundException e){
			ra.addFlashAttribute("message","El Rol que busca no se encuentra!");
		} catch (Exception e){
			ra.addFlashAttribute("error","No puede eliminar una Rol que ya fue asignado a otras tablas!");
		}
		return "redirect:/views/roles/listar";
	}

	@PostMapping("/import")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model,RedirectAttributes ra) {
		try {
			service2.store(file);
			ra.addFlashAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
		}
        return "redirect:/views/roles/listar";
    }


}
