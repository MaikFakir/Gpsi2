package com.proyecto.Gpsi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.Enrutador;
import com.proyecto.Gpsi.Repository.EnrutadorRepository;
import com.proyecto.Gpsi.Util.EnrutadorNotFoundException;

@Service
public class EnrutadorService {

    @Autowired
    private EnrutadorRepository repo;
    
    public List<Enrutador> listEnrutador(){
        return (List<Enrutador>) repo.findAll();
    }

    public void save(Enrutador enrutador) {
        repo.save(enrutador);
    }

    public Enrutador get(Integer id) throws EnrutadorNotFoundException{
		Optional<Enrutador> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EnrutadorNotFoundException("No se pudo encontrar una ruta con ID" + id);
	}

    public void delete(Integer id) throws EnrutadorNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new EnrutadorNotFoundException("No se pudo encontrar alguna ruta con el ID " + id);
        }
        repo.deleteById(id);
    }

}
