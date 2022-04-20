package com.example.lab3grupo1.controller;

import com.example.lab3grupo1.entity.Mascota;
import com.example.lab3grupo1.entity.Servicio;
import com.example.lab3grupo1.entity.OpcionServicio;
import com.example.lab3grupo1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    ResponsableRepository responsableRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    OpcionServicioRepository opcionServicioRepository;

    @Autowired
    OpcionRepository opcionRepository;


    @GetMapping(value = {"/lista", ""})
    public String listarServicios(Model model) {
        List<Servicio> lista = servicioRepository.findAll();
        model.addAttribute("servicioLista", lista);
        model.addAttribute("listaOpServ", opcionServicioRepository.findAll());
        return "servicio/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoServicio(Model model) {
        model.addAttribute("listaResponsable", responsableRepository.findAll());
        model.addAttribute("listaCuenta", cuentaRepository.findAll());
        model.addAttribute("listaOpcion", opcionRepository.findAll());
        return "servicio/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarServicio(Servicio servicio, RedirectAttributes attr) {
        if (servicio.getId() == null) {
            attr.addFlashAttribute("msg", "Servicio creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Servicio actualizado exitosamente");
        }
        servicioRepository.save(servicio);
        return "redirect:/servicio/lista";
    }

    @GetMapping("/editar")
    public String editarServicio(Model model, @RequestParam("id") int id) {
        model.addAttribute("listaResponsable", responsableRepository.findAll());
        Optional<Servicio> optionalServicio = servicioRepository.findById(id);
        // model.addAttribute("listaPrecios",opcionRepository.obtenerPrecios());
        if (optionalServicio.isPresent()) {
            Servicio servicio = optionalServicio.get();
            model.addAttribute("servicio", servicio);
            return "servicio/editar";
        } else {
            return "redirect:/servicio/lista";
        }
    }

}
