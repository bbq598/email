package com.example.email.controller;


import com.example.email.domain.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.email.service.EmailService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/sendEmail")
    public EmailResponse sentEmail(@RequestBody Map<String, String> data) throws IOException {
        EmailResponse emailResponse = emailService.sendEmail(data);
        return emailResponse;
    }




}
