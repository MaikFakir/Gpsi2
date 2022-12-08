
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

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Entity.Rol;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Repository.UsuarioRepository;
import com.proyecto.Gpsi.Service.MarcaService;
import com.proyecto.Gpsi.Service.UsuarioServices;
import com.proyecto.Gpsi.Util.UsuarioNotFoundException;
import com.proyecto.Gpsi.Util.UsuarioNotFoundException2;
 
 @Controller
 @RequestMapping("/views/usuarios")
 public class UsuarioController {
 
     @Autowired
     private UsuarioServices service;

    @Autowired
    private MarcaService service2;
    
 
     @GetMapping("/listar")
     public String listUsuario(Model model) {
 
         List<Usuario> listUsuario = service.listAll();
         model.addAttribute("listUsuarios", listUsuario);
         model.addAttribute("title","Listar Usuarios");
         return "/views/usuarios/listar";
     }
 
     @GetMapping("/nuevo")
     public String nuevoUsuario(Model model) {
        List<Rol> listRoles = service.listRoles();
        List<Marca> listMarca = service2.listMarcas();
         model.addAttribute("usu",new Usuario());
         model.addAttribute("title","Agregar nuevo Usuario");
         model.addAttribute("listRoles", listRoles);
		 model.addAttribute("listMarcas", listMarca);
         
         return "/views/usuarios/frmUsuario";
     }
 
     @PostMapping("/save")
     public String guardarUsuario(Usuario usuario, Model model) {
         service.save(usuario);
         return "redirect:/views/usuarios/listar";
     }

     
 
     @GetMapping("/edit/{id}")
     public String editarForm(@PathVariable("id")Integer id,Model model,RedirectAttributes ra) throws UsuarioNotFoundException2{

            List<Rol> listRoles = service.listRoles();
		    List<Marca> listMarca = service2.listMarcas();
             Usuario usuario = service.get(id);

             model.addAttribute("usu", usuario);
             model.addAttribute("listRoles", listRoles);
		     model.addAttribute("listMarcas", listMarca);
             model.addAttribute("title","Editar Usuario (ID: "+ id+ ")");
             model.addAttribute("alerta","Si no desea cambiar la contraseña, deje el cambio en blanco!");
             return "/views/usuarios/frmUsuario";
     }
 
     @GetMapping("/delete/{id}")
     public String deleteMarca(@PathVariable("id")Integer id,RedirectAttributes ra){
         try{
             service.delete(id);
         } catch (UsuarioNotFoundException2 e){
             ra.addFlashAttribute("message","El Usuario que busca no se encuentra!");
         }
         return "redirect:/views/usuarios/listar";
     }
     
 }
 