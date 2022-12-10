package com.proyecto.Gpsi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.Ruta;
import com.proyecto.Gpsi.Repository.RutaRepository;
import com.proyecto.Gpsi.Repository.RutaRepository2;
import com.proyecto.Gpsi.Util.RutaNotFoundException;

@Service
public class RutaService {
    
    @Autowired
    private RutaRepository2 repo;

    @Autowired
    private RutaRepository repo2;
    

    public List<Ruta> listRutas() {
        return (List<Ruta>) repo.findAll();
    }

    public void save(Ruta ruta) {
        repo.save(ruta);
    }

    public Ruta get(Integer id) throws RutaNotFoundException{
		Optional<Ruta> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RutaNotFoundException("No se pudo encontrar una ruta con ID" + id);
	}
    
    public void delete(Integer id) throws RutaNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new RutaNotFoundException("No se pudo encontrar alguna ruta con el ID " + id);
        }

    }

    public List<Ruta> findByRutasMensajero(int i) throws Exception {

        try {
            List<Ruta> entities = this.repo2.findByRutasMensajero(i);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
