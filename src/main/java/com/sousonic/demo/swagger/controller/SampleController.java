package com.sousonic.demo.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ruiminglu on 14-8-11.
 */
@Controller
public class SampleController {

    @RequestMapping("home")
    public String loadHomePage(Model m) {
        m.addAttribute("name", "sousonic");
        return "home";
    }
}
