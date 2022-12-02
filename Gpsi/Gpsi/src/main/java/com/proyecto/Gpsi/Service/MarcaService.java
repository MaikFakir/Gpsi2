package com.proyecto.Gpsi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Repository.MarcaRepository2;
import com.proyecto.Gpsi.Util.MarcaNotFoundException;


@Service
public class MarcaService {

    @Autowired
    private MarcaRepository2 repo;

    public List<Marca> listMarcas() {
        return (List<Marca>) repo.findAll();
    }

    public void save(Marca marca) {
        repo.save(marca);
    }

    public Marca get(Integer id) throws MarcaNotFoundException{
		Optional<Marca> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new MarcaNotFoundException("No se pudo encontrar una marca con ID" + id);
	}
    
    public void delete(Integer id) throws MarcaNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new MarcaNotFoundException("No se pudo encontrar algun usuario con el ID " + id);
        }
        repo.deleteById(id);
    }
    


}
