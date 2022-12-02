package com.proyecto.Gpsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.Gpsi.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	public Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.verificationCode = ?1")
	public Usuario findByVerificationCode(String code);

	public Usuario findByResetPasswordToken(String token);

}
