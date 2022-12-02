package com.proyecto.Gpsi.Repository;



import org.springframework.data.repository.CrudRepository;

import com.proyecto.Gpsi.Entity.TipoProd;


public interface TipoProdRepository2 extends CrudRepository<TipoProd,Integer>{

    public Long countById(Integer id);
    
}
