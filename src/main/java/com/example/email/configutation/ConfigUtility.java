package com.example.email.configutation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ConfigUtility {

    @Value("${api_key}")
    private String API_KEY;

    @Value("${enable_emails_filter}")
    private Boolean ENABLE_EMAIL_FILTER;

    @Value(("#{'${emails_filter_list}'.split(',')}"))
    private Set<String> EMAIL_FILTER_LIST;

    @Value("${email_from}")
    private String EMAIL_FROM;

    private final String DEFAULE_EMAIL_FROM = "bbq598@gmail.com";


    public String getAPI_KEY(){
        return API_KEY;
    }

    public boolean isEnableEmailFilter(){
        return ENABLE_EMAIL_FILTER != null ? ENABLE_EMAIL_FILTER : false;
    }

    public Set<String> getEMAIL_FILTER_LIST(){
        return EMAIL_FILTER_LIST != null ? EMAIL_FILTER_LIST : new HashSet<String>();
    }

    public String getEMAIL_FROM(){
        return EMAIL_FROM != null ? EMAIL_FROM : DEFAULE_EMAIL_FROM;
    }


}
