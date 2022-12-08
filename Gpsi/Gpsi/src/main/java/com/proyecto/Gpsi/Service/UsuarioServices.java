package com.proyecto.Gpsi.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Entity.Rol;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Repository.MarcaRepository;
import com.proyecto.Gpsi.Repository.RolRepository;
import com.proyecto.Gpsi.Repository.UsuarioRepository;
import com.proyecto.Gpsi.Repository.UsuarioRepository2;
import com.proyecto.Gpsi.Util.UsuarioNotFoundException;
import com.proyecto.Gpsi.Util.UsuarioNotFoundException2;

import net.bytebuddy.utility.RandomString;

@Service
public class UsuarioServices {
    
    @Autowired
    private UsuarioRepository repo;

	@Autowired
    private UsuarioRepository2 repo2;

    @Autowired
    private RolRepository roleRepo;

	@Autowired
    private MarcaRepository marcaRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    

	private void encodePassword(Usuario usuario) {
		String encodedPassword = passwordEncoder.encode(usuario.getContraseña());
		usuario.setContraseña(encodedPassword);		
	}
	
	

    public void register(Usuario usuario, String siteURL) 
			throws UnsupportedEncodingException, MessagingException {

		String encodedPassword = passwordEncoder.encode(usuario.getContraseña());
		usuario.setContraseña(encodedPassword);
		
		String randomCode = RandomString.make(64);
		usuario.setVerificationCode(randomCode);
		usuario.setEstado(false);

        Rol roleUser = roleRepo.findByName("Usuario");
        usuario.addRole(roleUser);

		Marca marcaUser = marcaRepo.findByName("Natural");
		usuario.setMarca(marcaUser);
		
		repo.save(usuario);
		
		sendVerificationEmail(usuario, siteURL);
	}

    private void sendVerificationEmail(Usuario usuario, String siteURL) 
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = usuario.getEmail();
		String fromAddress = "senapruebas18@gmail.com";
		String senderName = "your company name";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>"
				        + "Please click the link below to verify your registration:<br>"
				        + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
				        + "Thank you,<br>"
				        + "Your company name.";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);
		
		content = content.replace("[[name]]", usuario.getFullName());
		String verifyURL = siteURL + "/verify?code=" + usuario.getVerificationCode();
		
		content = content.replace("[[URL]]", verifyURL);
		
		helper.setText(content, true);
		
		mailSender.send(message);
		
		System.out.println("Email has been sent");
	}

    public boolean verify(String verificationCode) {
		Usuario usuario = repo.findByVerificationCode(verificationCode);
		
		if (usuario == null || usuario.getEstado()) {
			return false;
		} else {
			usuario.setVerificationCode(null);
			usuario.setEstado(true);
			repo.save(usuario);
			
			return true;
		}
		
	}

    public void updateResetPasswordToken(String token, String email) throws UsuarioNotFoundException{
         
		Usuario usuario = repo.findByEmail(email);

        if(usuario != null){
            usuario.setResetPasswordToken(token);
            repo.save(usuario);
        }else{
            throw new UsuarioNotFoundException("No se pudo encontrar ningún usuario con este email "+email);
        }

    }

    public Usuario getByResetPasswordToken(String token){
        return repo.findByResetPasswordToken(token);
    }

    public void updatePassword(Usuario usuario, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);

        usuario.setContraseña(encodedPassword);
        usuario.setResetPasswordToken(null);

        repo.save(usuario);
    }

	public List<Usuario> listAll() {
        return (List<Usuario>) repo2.findAll();
    }

	public void save(Usuario usuario) {
		encodePassword(usuario);		
        repo2.save(usuario);
    }

	public void save2(Usuario usuario) {
        repo2.save(usuario);
    }

    public Usuario get(Integer id) throws UsuarioNotFoundException2{
		Optional<Usuario> result = repo2.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UsuarioNotFoundException2("No se pudo encontrar un usuario con ID" + id);
	}
    
    public void delete(Integer id) throws UsuarioNotFoundException2 {
        Long count = repo2.countById(id);
        if (count == null || count == 0) {
            throw new UsuarioNotFoundException2("No se pudo encontrar algun usuario con el ID " + id);
        }
        repo.deleteById(id);
    }

	public List<Rol> listRoles() {
		return roleRepo.findAll();
	}






}
