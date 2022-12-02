package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.Gpsi.Entity.Rol;

public interface RolRepository extends JpaRepository<Rol,Integer>{

    @Query("SELECT r FROM Rol r WHERE r.name = ?1")
	public Rol findByName(String name);

}
