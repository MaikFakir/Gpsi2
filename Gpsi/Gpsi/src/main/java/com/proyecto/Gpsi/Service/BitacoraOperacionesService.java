package com.proyecto.Gpsi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.BitacoraOperaciones;
import com.proyecto.Gpsi.Entity.GestionEnvios;
import com.proyecto.Gpsi.Repository.BitacoraOperacionesRepository;
import com.proyecto.Gpsi.Util.BitacoraOperacionesNotFoundException;

@Service
public class BitacoraOperacionesService {   
    
    @Autowired
    private BitacoraOperacionesRepository repo;

    public List<BitacoraOperaciones> listBitacoraOperaciones(){
        return (List<BitacoraOperaciones>) repo.findAll();
    }

    public void save(BitacoraOperaciones bitacoraOperaciones) {
        repo.save(bitacoraOperaciones);
    }

    public BitacoraOperaciones get(Integer id) throws BitacoraOperacionesNotFoundException{
		Optional<BitacoraOperaciones> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new BitacoraOperacionesNotFoundException("No se pudo encontrar un pedido con ID" + id);
	}

    public void delete(Integer id) throws BitacoraOperacionesNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new BitacoraOperacionesNotFoundException("No se pudo encontrar algun pedido con el ID " + id);
        }
        repo.deleteById(id);
    }

    





}
