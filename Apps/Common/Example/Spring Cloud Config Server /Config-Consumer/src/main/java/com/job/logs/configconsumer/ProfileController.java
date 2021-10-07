package com.job.logs.configconsumer;

import com.job.logs.configconsumer.model.SampleProfileConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProfileController {

    @Autowired
    SampleProfileConfiguration sampleProfileConfiguration;

    @RequestMapping("/profile")
    public String getConfig(Model model){
        model.addAttribute("Env", sampleProfileConfiguration.getSampleProfile());
        return "mprofile";
    }
}
