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
    private RutaRepository2 repo2;

    @Autowired
    private RutaRepository repo;
    

    public List<Ruta> listRutas() {
        return (List<Ruta>) repo2.findAll();
    }

    public void save(Ruta ruta) {
        repo2.save(ruta);
    }

    public Ruta get(Integer id) throws RutaNotFoundException{
		Optional<Ruta> result = repo2.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RutaNotFoundException("No se pudo encontrar una ruta con ID" + id);
	}

    public void delete(Integer id) throws RutaNotFoundException {
        Long count = repo2.countById(id);
        if (count == null || count == 0) {
            throw new RutaNotFoundException("No se pudo encontrar algun usuario con el ID " + id);
        }
        repo.deleteById(id);
    }

    public List<Ruta> findByRutasMensajero(String cd) throws Exception {

        try {
            List<Ruta> entities = this.repo.findByRutasMensajero(cd);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
