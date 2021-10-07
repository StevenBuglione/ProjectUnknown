package com.job.logs.configconsumer.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SampleProfileConfiguration {

    @Autowired
    Environment environment;

    public String getSampleProfile(){
        return environment.getProperty("sample.one.env");
    }
}
