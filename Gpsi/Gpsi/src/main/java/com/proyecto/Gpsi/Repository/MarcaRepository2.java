package com.proyecto.Gpsi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Marca;

@Repository
public interface MarcaRepository2 extends CrudRepository<Marca,Integer> {

    public Long countById(Integer id);
}