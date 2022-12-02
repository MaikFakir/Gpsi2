package com.proyecto.Gpsi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.TipoProd;
import com.proyecto.Gpsi.Repository.TipoProdRepository2;
import com.proyecto.Gpsi.Util.TpProdNotFountException;

@Service
public class TipoProdService {
    
    @Autowired
    private TipoProdRepository2 repo;

    public List<TipoProd> listTpProductos() {
        return (List<TipoProd>) repo.findAll();
    }

    public void save(TipoProd tipoProd) {
        repo.save(tipoProd);
    }

    public TipoProd get(Integer id) throws TpProdNotFountException{
		Optional<TipoProd> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new TpProdNotFountException("No se pudo encontrar una tipo producto con ID" + id);
	}
    
    public void delete(Integer id) throws TpProdNotFountException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new TpProdNotFountException("No se pudo encontrar algun tipo producto con el ID " + id);
        }
        repo.deleteById(id);
    }


}
