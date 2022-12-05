package com.proyecto.Gpsi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Seguimiento;

@Repository
public interface SeguimientRepository2 extends CrudRepository<Seguimiento,Integer> {
    
    public Long countById(Integer id);

}
