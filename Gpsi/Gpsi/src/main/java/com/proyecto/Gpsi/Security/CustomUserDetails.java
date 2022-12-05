package com.proyecto.Gpsi.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Entity.Rol;
import com.proyecto.Gpsi.Entity.Usuario;

public class CustomUserDetails implements UserDetails {

    private Usuario usuario;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Rol> roles = usuario.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Rol rol : roles) {
            authorities.add(new SimpleGrantedAuthority(rol.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getContraseña();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario.getEstado();
    }

    public String getFullName() {
        return usuario.getNombres() + " " + usuario.getApellidos();
    }

    public int getIdentificacion() {
        return usuario.getId();
    }

    public String getTipoDocumento() {
        return usuario.getTipoDocumento();
    }

    public String getTelefono() {
        return usuario.getTelefono();
    }

    public Marca getMarca() {
        return usuario.getMarca();
    }

    public void setNombres(String nombres) {
        this.usuario.setNombres(nombres);
    }

    public void setApellidos(String apellidos) {
        this.usuario.setApellidos(apellidos);
    }

    

}



