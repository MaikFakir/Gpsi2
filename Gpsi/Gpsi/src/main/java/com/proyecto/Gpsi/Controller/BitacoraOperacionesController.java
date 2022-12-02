package com.proyecto.Gpsi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.Gpsi.Entity.BitacoraOperaciones;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Service.BitacoraOperacionesService;
import com.proyecto.Gpsi.Service.UsuarioServices;

@Controller
@RequestMapping("/views/bitacora_operaciones")
public class BitacoraOperacionesController {

    @Autowired
    private BitacoraOperacionesService service;

    @Autowired
    private UsuarioServices service2;

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
        List<Usuario> listUsuario = service2.listAll();

		model.addAttribute("bitacoraOperaciones",new BitacoraOperaciones());
        model.addAttribute("usuarios",listUsuario );
		model.addAttribute("title","Agregar nueva Bitacora");

		return "/views/gestion_envios/frmGestionEnvios";
	}

}   
