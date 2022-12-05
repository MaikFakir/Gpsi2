package com.proyecto.Gpsi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.Seguimiento;
import com.proyecto.Gpsi.Repository.SeguimientRepository2;
import com.proyecto.Gpsi.Util.SeguimientoNotFoundException;

@Service
public class SeguimientoService {

    @Autowired
    private SeguimientRepository2 repo;

    public List<Seguimiento> listSeguimiento(){
        return (List<Seguimiento>) repo.findAll();
    }

    public void save(Seguimiento seguimiento) {
        repo.save(seguimiento);
    }

    public Seguimiento get(Integer id) throws SeguimientoNotFoundException{
		Optional<Seguimiento> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new SeguimientoNotFoundException("No se pudo encontrar un seguimiento con ID" + id);
	}

    public void delete(Integer id) throws SeguimientoNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new SeguimientoNotFoundException("No se pudo encontrar algun pedido con el ID " + id);
        }
        repo.deleteById(id);
    }
    
}
