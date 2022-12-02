package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Integer> {

    @Query("SELECT m FROM Marca m WHERE m.name = ?1")
	public Marca findByName(String name);
    
}
