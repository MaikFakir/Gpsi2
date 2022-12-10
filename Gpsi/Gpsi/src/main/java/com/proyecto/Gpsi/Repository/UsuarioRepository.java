package com.proyecto.Gpsi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.Gpsi.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	public Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.verificationCode = ?1")
	public Usuario findByVerificationCode(String code);

	public Usuario findByResetPasswordToken(String token);

	@Query(value = "SELECT * FROM users_roles ur INNER JOIN usuarios u ON ur.user_id = u.id INNER JOIN roles r ON r.id =ur.rol_id WHERE r.name = 'Mensajero'; ",nativeQuery = true)
    List<Usuario> findMensajero();

}
