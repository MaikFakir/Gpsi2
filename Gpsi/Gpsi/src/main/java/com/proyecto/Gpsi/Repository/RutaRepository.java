package com.proyecto.Gpsi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.Enrutador;
import com.proyecto.Gpsi.Entity.Ruta;

@Repository
public interface RutaRepository  extends JpaRepository<Ruta,Integer> {

    @Query("SELECT ru FROM Ruta ru WHERE ru.id = ?1")
	public Enrutador findByName(String name);

    @Query(value = "SELECT * FROM rutas r INNER JOIN usuarios u ON r.usuarios_id = u.id INNER JOIN users_roles ur ON ur.user_id = r.usuarios_id INNER JOIN roles ro ON ro.id = ur.rol_id WHERE ro.name = 'Mensajero' AND ur.user_id = id  ",nativeQuery = true)
    List<Ruta> findByRutasMensajero(Integer id);
}
