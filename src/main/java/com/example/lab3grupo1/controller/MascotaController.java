package com.example.lab3grupo1.controller;

import com.example.lab3grupo1.entity.Mascota;
import com.example.lab3grupo1.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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


    @PostMapping("/search")
    public String buscar (Model model, @RequestParam("parametro") String parametro, @RequestParam("buscador") String buscador, RedirectAttributes attr){

        try {
            if (parametro.equals("")) { // verifica que no esté vacío
                attr.addFlashAttribute("msg", "La búsqueda no debe estar vacía.");
                return "redirect:/mascota";
            } else {
                model.addAttribute("parametro", parametro);
                model.addAttribute("buscador", buscador);
                parametro = parametro.toLowerCase();

                switch (buscador){
                    case "sexo":
                        List<Mascota> listaEmpleados1 = mascotaRepository.buscarPorSexo(parametro);
                        model.addAttribute("listaMascota", listaEmpleados1);
                        break;
                    case "raza":
                        List<Mascota> listaEmpleados2 = mascotaRepository.buscarPorRaza(parametro);
                        model.addAttribute("listaMascota", listaEmpleados2);
                        break;
                    case "contacto":
                        List<Mascota> listaEmpleados3 = mascotaRepository.buscarPorContacto(parametro);
                        model.addAttribute("listaMascota", listaEmpleados3);
                        break;
                    default:
                        List<Mascota> listaEmpleados4= mascotaRepository.findAll();
                        model.addAttribute("listaMascota", listaEmpleados4);
                        break;
                }

                return "mascota/lista";
            }
        } catch (Exception e) {
            attr.addFlashAttribute("msg", "La búsqueda no debe contener caracteres extraños.");
            return "redirect:/mascota";
        }




    }



}
