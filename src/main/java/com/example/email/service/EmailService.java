package com.example.email.service;

import com.example.email.configutation.ConfigUtility;
import com.example.email.dao.EmailDao;
import com.example.email.domain.EmailResponse;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class EmailService {

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private ConfigUtility configUtility;

    private final String SUBJECT = "SUBJECT";
    private final String TO = "TO";
    private final String BCC = "BCC";
    private final String CC = "CC";
    private final String BODY = "BODY";
    private static final Logger logger = LogManager.getLogger("Email Service");


    public EmailResponse sendEmail(Map<String, String> data) throws IOException {

        EmailResponse emailResponse = new EmailResponse();
        if(!data.containsKey(TO)){
            emailResponse = new EmailResponse("Email To is required");
            return emailResponse;
        }

        List<String> oldemailTo = Arrays.asList(data.get(TO).split(","));

        List<String> emailTo = new ArrayList<>(oldemailTo);

        // if filter is enable and the filter list is not empty
        if (configUtility.isEnableEmailFilter() && configUtility.getEMAIL_FILTER_LIST().size() != 0) {
            Set<String> filterList = configUtility.getEMAIL_FILTER_LIST();
            emailFilterHelper(emailTo,filterList);
        }

        Personalization personalization = new Personalization();

        for(int i = 0; i < emailTo.size(); i ++){
            personalization.addTo(new Email(emailTo.get(i)));
        }

        List<String> ccList = new ArrayList<>();
        if (data.containsKey(CC)) {
            ccList = Arrays.asList(data.get(CC).split(","));
        }

        for (int i = 0; i < ccList.size(); i++) {
            personalization.addCc(new Email(ccList.get(i)));
        }
        List<String> bccList = new ArrayList<>();
        if (data.containsKey(BCC)) {
            bccList = Arrays.asList(data.get(BCC).split(","));
        }

        for (int i = 0; i < bccList.size(); i++) {
            personalization.addBcc(new Email(bccList.get(i)));
        }

        Email from = new Email(configUtility.getEMAIL_FROM());
        String subject = data.getOrDefault(SUBJECT, "");
        Content content = new Content("text/plain",data.getOrDefault(BODY, ""));
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.addContent(content);
        mail.addPersonalization(personalization);
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            emailResponse = generateResponse(response, mail);
            emailDao.insertEmailbyId();
        } catch (IOException ex) {
            logger.warn(ex);
            throw ex;
        }
        return emailResponse;
    }

    private EmailResponse generateResponse(Response response, Mail mail){
        StringBuilder str = new StringBuilder();
        str.append("Your email is sent with a reply code " + response.getStatusCode()  + " " + response.getBody().toString());
        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setContent(str.toString());
        logger.info(emailResponse);
        return emailResponse;
    }

    public void emailFilterHelper( List<String> emailTo, Set<String> filterList){
        for(int i = emailTo.size() - 1; i >= 0 ; i --){
            String emailDomain = emailTo.get(i).split("@")[1];
            if (!filterList.contains(emailDomain)) {
                emailDao.insertEmailbyAddress();
                logger.warn("Email address " + emailTo.get(i) + " is not a valid email address");
                emailTo.remove(i);
            }
        }
    }


}
