package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Enrutador;
import com.proyecto.Gpsi.Entity.Ruta;

@Repository
public interface RutaRepository2  extends CrudRepository<Ruta,Integer> {
    public Long countById(Integer id);
}
