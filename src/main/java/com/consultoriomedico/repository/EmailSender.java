package com.consultoriomedico.repository;


import com.consultoriomedico.domain.Cita;
import com.consultoriomedico.domain.PropertiesConfig;
import com.consultoriomedico.domain.Persona;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Builder
public class EmailSender implements IConfirmadorCitas {

    private static int port = 25;
    private static boolean debug = true;
    private static final String MAIL_CONFIRMATION_PATH = "src/main/resources/confirmationMail.html";
    private static final Logger log = Logger.getLogger(EmailSender.class);


    private static String senderEmail = "clinicakodigo@gmail.com";

    public void sendMail(Persona usuario, String subject) {
        PropertiesConfig properties = new PropertiesConfig();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getPropertyConfig("username"), properties.getPropertyConfig("password"));
            }
        };
        Session session = Session.getInstance(props, auth);

        try {

            MimeMessage message = new MimeMessage(session);

            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");


            message.setFrom(new InternetAddress(senderEmail));
            message.setReplyTo(InternetAddress.parse(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getEmail()));
            message.setSubject(subject, "UTF-8");

            String htmlContentStr = modifiedHtmlConfirmation(usuario);

            message.setContent(htmlContentStr, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String modifiedHtmlConfirmation(Persona usuario) throws IOException {
        File flHtml = new File(MAIL_CONFIRMATION_PATH);
        Document doc = Jsoup.parse(flHtml, "UTF-8", "");
        Element p = doc.getElementById("idNombre");
        if (p != null) {
            p.append(usuario.getNombre());
        }
        p = doc.getElementById("idIdentificacion");
        if (p != null) {
            p.append(String.valueOf(usuario.getId()));
        }
        p = doc.getElementById("idDireccion");
        if (p != null) {
            p.append(usuario.getDireccion());
        }
        p = doc.getElementById("idTelefono");
        if (p != null) {
            p.append(usuario.getTelefono());
        }
        return doc.html();
    }

    public boolean enviarConfirmacion(Persona usuario, Cita cita) {
        //TODO: IMPLEMENTAR ENVIAR CONFIMACION
        return true;
    }
}
