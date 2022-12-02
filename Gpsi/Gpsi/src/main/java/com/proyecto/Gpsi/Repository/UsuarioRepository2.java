package com.proyecto.Gpsi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Usuario;

@Repository
public interface UsuarioRepository2 extends CrudRepository<Usuario,Integer>{
    
}
