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

import com.proyecto.Gpsi.Entity.Ruta;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Security.CustomUserDetails;
import com.proyecto.Gpsi.Service.RutaService;
import com.proyecto.Gpsi.Service.UsuarioServices;
import com.proyecto.Gpsi.Util.RutaNotFoundException;

@Controller
@RequestMapping("/views/ruta")
public class RutaController {

    @Autowired
    private RutaService service;

    @Autowired
	private UsuarioServices service3;

    @GetMapping("/listar")
	public String listRuta(Model model) {

        List<Ruta> listRutas = service.listRutas();
		model.addAttribute("listRutas", listRutas);
		model.addAttribute("title","Listar Ruta");
		return "/views/ruta/listar";
	}


	@GetMapping(value = "/Mis_envios")
    public String bucarporid(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model,String cd){
        try {
			
			 int id =loggedUser.getIdentificacion();

			 cd = Integer.toString(id);

            List<Ruta> rutaenvio = this.service.findByRutasMensajero(cd);
            model.addAttribute("envio", rutaenvio);
            model.addAttribute("title", "Mi Entregas.");
            model.addAttribute("message","Se ha cargado correctamente su rutas.");
            return "/views/dashboard/misRutas";
        } catch (Exception e) {
            model.addAttribute("error2", e.getMessage());
            return "error";
        }
    }

	@GetMapping("/nueva")
	public String nuevaRuta(Model model) {

        List<Usuario> listUsuario = service3.ListMensajero();
		model.addAttribute("rutan",new Ruta());
        model.addAttribute("usuarios", listUsuario);
		model.addAttribute("title","Asignar Ruta");
		return "/views/ruta/frmRuta";
	}

	@PostMapping("/save")
	public String guardarRuta(Ruta ruta, RedirectAttributes ra) {
		service.save(ruta);
		ra.addFlashAttribute("message","La marca ha sido guardado correctamente!");
		return "redirect:/views/ruta/listar";
	}

	@GetMapping("/edit/{id}")
	public String editarForm(@PathVariable("id")Integer id,Model model,RedirectAttributes ra){
		try{
			Ruta ruta = service.get(id);
            List<Usuario> listUsuario = service3.ListMensajero();
			model.addAttribute("rutan",ruta);
            model.addAttribute("usuarios", listUsuario);
			model.addAttribute("title","Editar ruta (ID: "+ id+ ")");
			return "/views/ruta/frmRuta";
		} catch (RutaNotFoundException e){
			ra.addFlashAttribute("message","La marca que busca no se encuentra!");
			return "redirect:/views/ruta/listar";
		}
	}

	    @GetMapping("/delete/{id}")
	public String deleteRol(@PathVariable("id")Integer id,RedirectAttributes ra){
		try{
			service.delete(id);
		} catch (RutaNotFoundException e){
			ra.addFlashAttribute("message","El Ma que busca no se encuentra!");
		} catch (Exception e){
			ra.addFlashAttribute("error","No puede eliminar una Rol que ya fue asignado a otras tablas!");
		}
		return "redirect:/views/ruta/listar";
	}
    
}
