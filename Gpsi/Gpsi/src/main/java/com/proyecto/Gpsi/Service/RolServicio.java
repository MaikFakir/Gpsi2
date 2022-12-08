package com.proyecto.Gpsi.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.Gpsi.Entity.Rol;
import com.proyecto.Gpsi.Repository.RolRepository;
import com.proyecto.Gpsi.Repository.RolRepository2;
import com.proyecto.Gpsi.Util.RolNotFoundException;

@Service
public class RolServicio {
    
    @Autowired
    private RolRepository2 repo;

    @Autowired
    private ExcelUploadService service;

    public List<Rol> listRoles() {
        return (List<Rol>) repo.findAll();
    }

    public void save(Rol rol) {
        repo.save(rol);
    }

    public Rol get(Integer id) throws RolNotFoundException{
		Optional<Rol> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RolNotFoundException("No se pudo encontrar un rol con ID" + id);
	}
    
    public void delete(Integer id) throws RolNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new RolNotFoundException("No se pudo encontrar algun usuario con el ID " + id);
        }
        repo.deleteById(id);
    }

}
