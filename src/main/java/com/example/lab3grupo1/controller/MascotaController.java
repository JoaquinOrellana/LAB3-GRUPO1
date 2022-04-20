package com.example.lab3grupo1.controller;

import com.example.lab3grupo1.entity.Mascota;
import com.example.lab3grupo1.repository.MascotaRepository;
import com.example.lab3grupo1.repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mascota")

public class MascotaController {


    @Autowired
    MascotaRepository mascotaRepository;


    @Autowired
    RazaRepository razaRepository;

    @GetMapping(value = {"", "/lista"})
    public String listaMascota(Model model) {
        model.addAttribute("listaMascota", mascotaRepository.findAll());

        return "mascota/lista";
    }


    @PostMapping("/search")
    public String buscar(Model model, @RequestParam("parametro") String parametro, @RequestParam("buscador") String buscador, RedirectAttributes attr) {

        try {
            if (parametro.equals("")) { // verifica que no esté vacío
                attr.addFlashAttribute("msg", "La búsqueda no debe estar vacía.");
                return "redirect:/mascota";
            } else {
                model.addAttribute("parametro", parametro);
                model.addAttribute("buscador", buscador);
                parametro = parametro.toLowerCase();

                switch (buscador) {
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
                        List<Mascota> listaEmpleados4 = mascotaRepository.findAll();
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

    @GetMapping("/new")
    public String nuevoMascotaForm(@ModelAttribute("mascota") Mascota mascota, Model model) {
        model.addAttribute("listaMascota", mascotaRepository.findAll());
        model.addAttribute("listaRaza", razaRepository.findAll());

        return "mascota/form";
    }

    @GetMapping("/edit")
    public String editarMascota(@ModelAttribute("mascota") Mascota mascota,
                                @RequestParam("idmascota") int id,
                                Model model) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        if (mascotaOptional.isPresent()) {
            mascota = mascotaOptional.get();
            model.addAttribute("listaMascota", mascotaRepository.findAll());
            model.addAttribute("listaRaza", razaRepository.findAll());
            ;
            return "/mascota/form";
        } else {
            return "redirect:/mascota/lista";
        }
    }

    @PostMapping("/save")
    public String guardarMascota(@ModelAttribute("mascota") @Valid Mascota mascota, BindingResult bindingResult,
                                 RedirectAttributes attr, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaMascota", mascotaRepository.findAll());
            model.addAttribute("listaRaza", razaRepository.findAll());
            return "mascota/form";
        } else if (mascota.getIdmascota() == 0) {
                attr.addFlashAttribute("msg", "Mascota creado exitosamente");
                mascotaRepository.save(mascota);
                return "redirect:/mascota/lista";
            }
        return "redirect:/mascota/lista";
        }
    }




