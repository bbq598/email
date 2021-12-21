package com.example.email.service;

import com.example.email.configutation.ConfigUtility;
import com.example.email.dao.EmailDao;
import com.example.email.domain.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private ConfigUtility configUtility;


    public EmailResponse sendEmail(Map<String, String> data){


        return new EmailResponse("ok");
    }



}
