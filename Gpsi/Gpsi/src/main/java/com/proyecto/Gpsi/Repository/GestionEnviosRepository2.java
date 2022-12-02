package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.GestionEnvios;

@Repository
public interface GestionEnviosRepository2 extends JpaRepository<GestionEnvios,Integer> {

    @Query("SELECT ge FROM GestionEnvios ge WHERE ge.numGuia = ?1")
	public GestionEnvios findByName(String name);
}


