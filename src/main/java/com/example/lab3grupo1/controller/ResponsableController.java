package com.example.lab3grupo1.controller;

import com.example.lab3grupo1.entity.Responsable;
import com.example.lab3grupo1.repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/responsable")
public class ResponsableController {

    @Autowired
    ResponsableRepository responsableRepository;

    @GetMapping(value = {"", "/","/list"})
    public String listaResponsables(Model model) {
        model.addAttribute("listaResponsables", responsableRepository.findAll());
        return "responsable/lista";
    }

    @GetMapping("/new")
    public String nuevoResponsableFrm(Model model) {

        return "responsable/newForm";
    }
    @PostMapping("/save")
    public String guardarMarca(Responsable responsable, RedirectAttributes attributes){
        responsableRepository.save(responsable);
        attributes.addFlashAttribute("msg", "Responsable creado exitosamente");
        return "redirect:/responsable/list";
    }

    @GetMapping("/delete")
    public String borrarResponsable(@RequestParam("id") int id, RedirectAttributes attributes){
        Optional<Responsable> optionalResponsable = responsableRepository.findById(id);
        if (optionalResponsable.isPresent()) {
            responsableRepository.deleteById(id);
            attributes.addFlashAttribute("msg", "Responsable borrada exitosamente");
        }
        return "redirect:/responsable/list";
    }


}
