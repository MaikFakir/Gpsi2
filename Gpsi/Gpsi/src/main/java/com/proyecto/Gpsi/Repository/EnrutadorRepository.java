package com.proyecto.Gpsi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Enrutador;

@Repository
public interface EnrutadorRepository extends CrudRepository<Enrutador,Integer>{

    public Long countById(Integer id);
    
}
