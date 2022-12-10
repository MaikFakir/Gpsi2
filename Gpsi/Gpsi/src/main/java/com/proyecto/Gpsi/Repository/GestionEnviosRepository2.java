package com.proyecto.Gpsi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.Gpsi.Entity.GestionEnvios;

@Repository
public interface GestionEnviosRepository2 extends JpaRepository<GestionEnvios,Integer> {

    @Query("SELECT ge FROM GestionEnvios ge WHERE ge.numGuia = ?1")
	public GestionEnvios findByName(String name);

    @Query(value = "SELECT * FROM gestion_de_paquetes_envios g WHERE num_guia = :id ",nativeQuery = true)
    Optional<GestionEnvios> findById(@Param("id")String id);

    @Query(value = "SELECT * FROM gestion_de_paquetes_envios g WHERE usuarios_id = :id  ",nativeQuery = true)
    List<GestionEnvios> findByGestionEnvios(Integer id);
}


