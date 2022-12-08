package com.proyecto.Gpsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyecto.Gpsi.Entity.Rol;
import com.proyecto.Gpsi.Repository.RolRepository2;
import com.proyecto.Gpsi.Service.RolServicio;

@SpringBootApplication
public class GpsiApplication {

	@Autowired
	private RolRepository2 repo;

	@Autowired
	private RolServicio service;

	public static void main(String[] args) {
		SpringApplication.run(GpsiApplication.class, args);
	}

}
