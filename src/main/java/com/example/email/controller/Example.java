package com.example.email.controller;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class Example {



    public static void main(String[] args) throws IOException {

        Email from = new Email("bbq598@hotmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("bbq598@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);
        mail.getPersonalization().get(0).addBcc(new Email("bbq598@bu.edu"));
        mail.getPersonalization().get(0).addCc(new Email("710397430@qq.com"));
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
