package com.example.lab3grupo1.controller;


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
    ContactoRepository contactoRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @GetMapping(value = {"", "/lista"})
    public String listaContacto(Model model){
        model.addAttribute("listaContacto", contactoRepository.findAll());
        return "contacto/lista";
    }
    @GetMapping("/new")
    public String nuevoContactoForm(Model model) {
        model.addAttribute("listaContacto", contactoRepository.findAll());
        return "contacto/form";
    }

    @GetMapping("/editar")
    public String editarContacto(@ModelAttribute("contacto") Contacto contacto,
                                @RequestParam("id") int id,
                                Model model) {
        Optional<Contacto> optionalContacto = contactoRepository.findById(id);
        System.out.println(id);
        if (optionalContacto.isPresent()) {
            contacto = optionalContacto.get();
            model.addAttribute("contacto", contacto);
            model.addAttribute("listaContacto", contactoRepository.findAll());
            model.addAttribute("listaCuenta", cuentaRepository.findAll());
            return "/contacto/editar";
        } else {
            return "redirect:/contacto/lista";
        }
    }

    @PostMapping("/save")
    public String guardarContacto(Contacto contacto, RedirectAttributes attr, Model model) {

        if (contacto.getIdcontacto() == 0) {
            attr.addFlashAttribute("msg", "Contacto creada exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Contacto actualizada exitosamente");
        }

        if (contacto.getCuenta() != null) {
            contactoRepository.save(contacto);
            return "redirect:/mascota/lista";
        } else {
            model.addAttribute("errProd", "Error al guardar contacto");
            model.addAttribute("listaContacto", contactoRepository.findAll());
            model.addAttribute("listaCuenta", cuentaRepository.findAll());
            if (contacto.getIdcontacto() != 0) {
                model.addAttribute("contacto", contacto);
                return "contacto/editar";
            } else {
                return "contacto/form";
            }
        }

    }
}
