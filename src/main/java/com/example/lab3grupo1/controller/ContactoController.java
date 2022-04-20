package com.example.lab3grupo1.controller;


import com.example.lab3grupo1.entity.Cuenta;
import com.example.lab3grupo1.entity.Mascota;
import com.example.lab3grupo1.repository.CuentaRepository;
import com.example.lab3grupo1.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @GetMapping(value = {"", "/lista"})
    public String listaContacto(Model model){
        model.addAttribute("listaContacto", cuentaRepository.findAll());
        return "contacto/lista";
    }
    @GetMapping("/new")
    public String nuevoContactoForm(Model model) {
        model.addAttribute("listaContacto", cuentaRepository.findAll());
        return "contacto/form";
    }

    @GetMapping("/editar")
    public String editarContacto(@ModelAttribute("contacto") Cuenta contacto,
                                @RequestParam("id") int id,
                                Model model) {
        Optional<Cuenta> optcontacto = cuentaRepository.findById(id);
        System.out.println(id);
        if (optcontacto.isPresent()) {
            contacto = optcontacto.get();
            model.addAttribute("contacto", contacto);
            model.addAttribute("listaCuenta", cuentaRepository.findAll());
            return "/contacto/editar";
        } else {
            return "redirect:/contacto/lista";
        }
    }

    @PostMapping("/save")
    public String guardarContacto(Cuenta contacto, RedirectAttributes attr, Model model) {

        if (contacto.getId() == 0) {
            attr.addFlashAttribute("msg", "Contacto creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Contacto actualizado exitosamente");
        }

        if (contacto.getId() != null) {
            cuentaRepository.save(contacto);
            return "redirect:/mascota/lista";
        } else {
            model.addAttribute("errProd", "Error al guardar contacto");
            model.addAttribute("listaCuenta", cuentaRepository.findAll());
            if (contacto.getId() != 0) {
                model.addAttribute("contacto", contacto);
                return "contacto/editar";
            } else {
                return "contacto/form";
            }
        }

    }
}
