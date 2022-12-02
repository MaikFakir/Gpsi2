package com.proyecto.Gpsi.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Repository.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(usuario);
    }
    
}
