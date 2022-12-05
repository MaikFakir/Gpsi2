package com.proyecto.Gpsi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/dashboard")
public class DashboardController {
    
    @GetMapping({"/inicio"})
	public String index() {
		return "/views/dashboard/inicio";
	}


}
