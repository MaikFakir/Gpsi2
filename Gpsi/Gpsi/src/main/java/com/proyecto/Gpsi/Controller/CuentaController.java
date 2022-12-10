package com.proyecto.Gpsi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.proyecto.Gpsi.Security.CustomUserDetails;
import com.proyecto.Gpsi.Service.MarcaService;
import com.proyecto.Gpsi.Service.UsuarioServices;
import com.proyecto.Gpsi.Util.UsuarioNotFoundException2;

@Controller
@RequestMapping("/views/dashboard")
public class CuentaController {
    
    @Autowired
    private UsuarioServices service;

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private MarcaService service2;
    
    

    

    @GetMapping("/cuenta")
    public String verCuenta(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model){
        String email = loggedUser.getUsername();
        Usuario usuario = repo.findByEmail(email);

            model.addAttribute("user2",usuario);
            model.addAttribute("title","Mi perfil");
            model.addAttribute("alerta","Cuidado una vez guarde sus cambios debera cerrar sesion y volver a ingresar para aplicar los cambios!");
            return "/views/dashboard/perfil";

    }


    @GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws UsuarioNotFoundException2 {
		List<Rol> listRoles = service.listRoles();
		List<Marca> listMarca = service2.listMarcas();
        
		Usuario usuarios = service.get(id);
		model.addAttribute("usu", usuarios);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("listMarcas", listMarca);
        model.addAttribute("title", "Editar Usuario (ID: " + id + ")");
        model.addAttribute("alerta","Si desea cambiar su contraseña, en la pesataña anterior puede hacerlo!");
		return "/views/dashboard/frmCuenta";
		
		
        
	}


    @PostMapping("/save")
	public String saveUser(Usuario usuario,@AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
        loggedUser.setNombres(usuario.getNombres());
        loggedUser.setApellidos(usuario.getApellidos());
		service.save(usuario);
		model.addAttribute("message","Su perfil se ha guardado correctamente!");
		return "/views/dashboard/inicio";
	}	



 
}
