package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.TipoProd;


public interface TipoProdRepository extends JpaRepository<TipoProd,Integer>{

    @Query("SELECT tp FROM TipoProd tp WHERE tp.name = ?1")
	public TipoProd findByName(String name);
    
}
