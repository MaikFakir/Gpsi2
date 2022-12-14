package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Enrutador;
import com.proyecto.Gpsi.Entity.GestionEnvios;

@Repository
public interface EnrutadorRepository2 extends JpaRepository<Enrutador,Integer>{

    @Query("SELECT en FROM Enrutador en WHERE en.id = ?1")
	public Enrutador findByName(String name);
    
}
