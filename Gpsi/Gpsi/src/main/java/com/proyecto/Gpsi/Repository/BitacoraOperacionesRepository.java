package com.proyecto.Gpsi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.BitacoraOperaciones;

@Repository
public interface BitacoraOperacionesRepository extends CrudRepository<BitacoraOperaciones,Integer> {
    public Long countById(Integer id);
}
