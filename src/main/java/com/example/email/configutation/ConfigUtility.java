package com.example.email.configutation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfigUtility {

    @Value("${api_key}")
    private String API_KEY;

    @Value("${enable_emails_filter}")
    private Boolean ENABLE_EMAIL_FILTER;

    @Value(("#{'${emails_filter_list}'.split(',')}"))
    private List<String> EMAIL_FILTER_LIST;


    public String getAPI_KEY(){
        return API_KEY;
    }

    public boolean isEnableEmailFilter(){
        return ENABLE_EMAIL_FILTER != null ? ENABLE_EMAIL_FILTER : false;
    }

    public List<String> getEMAIL_FILTER_LIST(){
        return EMAIL_FILTER_LIST != null ? EMAIL_FILTER_LIST : new ArrayList<String>();
    }




}
