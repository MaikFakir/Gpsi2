package com.proyecto.Gpsi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.GestionEnvios;
import com.proyecto.Gpsi.Repository.GestionEnviosRepository;
import com.proyecto.Gpsi.Repository.GestionEnviosRepository2;
import com.proyecto.Gpsi.Util.GestionEnviosNotFoundException;

@Service
public class GestionEnviosService {
    
    @Autowired
    private GestionEnviosRepository repo;

    @Autowired
    private GestionEnviosRepository2 repo2;

    public List<GestionEnvios> listGestionEnvios() {
        return (List<GestionEnvios>) repo.findAll();
    }

    public void save(GestionEnvios gestionEnvios) {
        repo.save(gestionEnvios);
    }

    public GestionEnvios get(Integer id) throws GestionEnviosNotFoundException{
		Optional<GestionEnvios> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new GestionEnviosNotFoundException("No se pudo encontrar un pedido con ID" + id);
	}
    
    public void delete(Integer id) throws GestionEnviosNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new GestionEnviosNotFoundException("No se pudo encontrar algun pedido con el ID " + id);
        }
        repo.deleteById(id);
    }

/*     public void validarNumeroGuia (String numguia) throws GestionEnviosNotFoundException{
         
		GestionEnvios gEnvios = repo2.findByName(numguia);

        if(gEnvios != null){
            gEnvios.setNumGuia(numguia);
            repo.save(gEnvios);
        }else{
            throw new GestionEnviosNotFoundException("No se pudo encontrar ningúna guia con este id "+ numguia);
        }

    } */

    public Optional<GestionEnvios> findById(Integer id) throws Exception {

        try {
            Optional<GestionEnvios> entities = this.repo.findById(id);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
        
    
}
