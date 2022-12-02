package com.proyecto.Gpsi.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Service.UsuarioServices;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioServices service;
    
    @GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return  "login/register";
	}

    @PostMapping("/process_register")
	public String processRegister(Usuario usuario, HttpServletRequest request) 
			throws UnsupportedEncodingException, MessagingException {
		service.register(usuario, getSiteURL(request));		
		return "login/register_success";
	}

    private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}	

    @GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (service.verify(code)) {
			return "login/verify_success";
		} else {
			return "login/verify_fail";
		}
	}

}
