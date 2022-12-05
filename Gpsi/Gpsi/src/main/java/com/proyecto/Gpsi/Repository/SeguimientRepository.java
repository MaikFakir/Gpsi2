package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Seguimiento;

@Repository
public interface SeguimientRepository extends JpaRepository<Seguimiento,Integer> {
    
    @Query("SELECT s FROM Seguimiento s WHERE s.id = ?1")
	public Seguimiento findByName(String name);

}
