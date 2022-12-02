package com.proyecto.Gpsi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.GestionEnvios;

@Repository
public interface GestionEnviosRepository extends CrudRepository<GestionEnvios,Integer> {
    
    public Long countById(Integer id);
}


