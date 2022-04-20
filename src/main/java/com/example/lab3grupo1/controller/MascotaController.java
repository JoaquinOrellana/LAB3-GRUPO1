package com.example.lab3grupo1.controller;

import com.example.lab3grupo1.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mascota")

public class MascotaController {


    @Autowired
    MascotaRepository mascotaRepository;

    @GetMapping(value = {"","/lista"})
    public String listaMascota(Model model){
        model.addAttribute("listaMascota", mascotaRepository.findAll());

        return "mascota/lista";
    }

}
