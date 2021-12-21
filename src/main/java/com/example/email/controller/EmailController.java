package com.example.email.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.email.service.EmailService;

import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/sendEmail")
    public Map<String, String> sentEmail(@RequestBody Map<String, String> data){
//        System.out.println(configUtility.getAPI_KEY());
//        System.out.println(configUtility.isEnableEmailFilter());
//        System.out.println(configUtility.getEMAIL_FILTER_LIST().get(0));
//        System.out.println(configUtility.getEMAIL_FILTER_LIST().get(1));
        return data;
    }




}
