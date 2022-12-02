package com.proyecto.Gpsi.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Service.UsuarioServices;
import com.proyecto.Gpsi.Util.UsuarioNotFoundException;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("pageTitle", "Forgot Password");
        return "login/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request, Model model) {

        String email = request.getParameter("email");
        String token = RandomString.make(45);

        // System.out.println("Email: " + email);
        // System.out.println("Token: " + token);

        try {
            usuarioServices.updateResetPasswordToken(token, email);
            String resetPasswordLink = getSiteURL(request) + "/reset_password?token=" + token;

            System.out.println(resetPasswordLink);
            sendEmail(email, resetPasswordLink);

            model.addAttribute("message","Se ha enviado un link de recuperacion a su correo. Porfavor reviselo");

            // send email

        } catch (UsuarioNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error","Error mientras se enviaba el correo.");
        }

        return "login/forgot_password_form";

    }

    private void sendEmail(String email, String resetPasswordLink)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String toAddress = email;
        String fromAddress = "senapruebas18@gmail.com";
        String senderName = "Recuperacion de contraseña GRIIP";
        String subject = "Aqui esta el link para cambiar su contraseña";
        String content = "Hola,<br>"
                + "Solicitaste un cambio de contraseña<br>"
                + "<h3><a href=\"" + resetPasswordLink + "\">Cambiar mi Contraseña</a></h3>"
                + "Ignore este correo si recuerda su contraseña, o no hizo esta peticion.<br>"
                + "Gracias,<br>"
                + "Griip logistica.";

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);

        System.out.println("Email has been sent");
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token,Model model){
        Usuario usuario = usuarioServices.getByResetPasswordToken(token);
        model.addAttribute("token",token);

        if(usuario == null){
            model.addAttribute("title","Cambia Contraseña");
            model.addAttribute("message","Token Invalido");
            return "Mensaje/mensaje";
        }

        model.addAttribute("title","token");
        model.addAttribute("message","Cambia tu contraseña");

        return "login/reset_password_form";
        
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model){
        
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Usuario usuario = usuarioServices.getByResetPasswordToken(token);

        if(usuario == null){
            model.addAttribute("title","Cambia Contraseña");
            model.addAttribute("message","Token Invalido");
        } else {
            usuarioServices.updatePassword(usuario, password);
            model.addAttribute("message","Has cambiado correctamente tu contraseña.");
        }
        return "Mensaje/mensaje";

    }

}
