package com.example;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import com.example.LectorParaSpam.SpamListener;

public class EnviadorSpam implements SpamListener {

    String usuario;
    String password;

    public EnviadorSpam(String usuario, String password, LectorParaSpam lector) {
        this.usuario = usuario;
        this.password = password;

        lector.addListener(this); // ?
    }

    @Override
    public void emailPreparado(String direccion, String mensaje) {
        // usuario contiene también el dominio (@educa.madrid.org)
        String usuarioSolo = usuario.split("@")[0];

        // Código que envía
        Email email = EmailBuilder.startingBlank()
                .to("Spameado", direccion)
                .from("Spameador", usuario)
                .withSubject("Spam")
                .withPlainText(mensaje)
                .buildEmail();

        Mailer mailer = MailerBuilder

                .withSMTPServer("smtp.educa.madrid.org", 587, usuarioSolo, password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .clearEmailValidator() // turns off email validation
                .buildMailer();

        mailer.sendMail(email);

        System.out.println("Hello world!");
    }
}