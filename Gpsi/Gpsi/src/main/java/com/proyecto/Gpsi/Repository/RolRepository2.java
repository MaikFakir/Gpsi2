package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Rol;

@Repository
public interface RolRepository2 extends CrudRepository<Rol,Integer>{

    public Long countById(Integer id);
    
}
