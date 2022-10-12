package com.example.interceptores_http.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @Value("${config.horario.apertura}")
    private Integer apertura;
    @Value("${config.horario.cierre}")
    private Integer cierre;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("titulo", "Bienvenido al horario de atención al cliente");
        
        return "index";
    }

    @GetMapping("/cerrado")
    public String cerrado(Model model){
        model.addAttribute("titulo", "Fuera del horario de atención");

        return "cerrado";
    }
    
}
