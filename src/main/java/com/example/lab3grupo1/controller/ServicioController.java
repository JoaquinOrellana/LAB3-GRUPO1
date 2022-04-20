package com.example.lab3grupo1.controller;

import com.example.lab3grupo1.entity.*;
import com.example.lab3grupo1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    MascotaRepository mascotaRepository;


    @GetMapping(value = {"/lista", ""})
    public String listarServicios(Model model) {
        List<Servicio> lista = servicioRepository.findAll();
        model.addAttribute("servicioLista", lista);
        model.addAttribute("listaOpServ", opcionServicioRepository.findAll());
        return "servicio/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoServicioMascota(Model model) {
        model.addAttribute("listaResponsable", responsableRepository.findAll());
        model.addAttribute("listaCuenta", cuentaRepository.findAll());
        model.addAttribute("listaOpcion", opcionRepository.findAll());
        model.addAttribute("listaMascotas", mascotaRepository.findAll());
        return "servicio/nuevo";
    }

    @GetMapping("/nuevocrear")
    public String crearServicio() {
        return "servicio/crearForm";
    }

    @PostMapping("/guardarnuevoserv")
    public String guardarNuevoServicio(Opcion opcion, RedirectAttributes attr) {

        attr.addFlashAttribute("msg", "Servicio creado exitosamente");

        opcionRepository.save(opcion);

        return "redirect:/servicio/lista";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@RequestParam("precioopc") Double precioopc, @RequestParam("idopcion") Integer idopcion, @RequestParam("idservicio") Integer idservicio, @RequestParam("idresponsable") Integer idresponsable,RedirectAttributes attr) {
        Opcion opc = opcionRepository.findById(idopcion).get();
        opc.setPrecio(precioopc);

        Servicio servicio = servicioRepository.findById(idservicio).get();

        List<Responsable> listaResponsables = responsableRepository.findAll();
        for (Responsable r : listaResponsables) {
            if (r.getId() == idresponsable) {
                servicio.setIdresponsable(r);
            }
        }
        opcionRepository.save(opc);
        servicioRepository.save(servicio);
        attr.addFlashAttribute("msg", "1");
        return "redirect:/servicio/lista";
    }

    @GetMapping("/editar")
    public String editarServicio(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
        Optional<OpcionServicio> optionalOpServ = opcionServicioRepository.findById(id);
        if (optionalOpServ.isPresent()) {
            OpcionServicio opServ = optionalOpServ.get();

            Servicio serv = opServ.getIdservicio();
            Opcion opc = opServ.getIdopcion();

            model.addAttribute("servicio", serv);
            model.addAttribute("opcion", opc);
            model.addAttribute("listaResponsable", responsableRepository.findAll());
            return "servicio/editar";
        } else {
            attr.addFlashAttribute("msg", -1); // "Error al editar"
            return "redirect:/servicio/lista";
        }
    }

    @PostMapping("/guardarserv")
    public String saveNuevaOpServ(@RequestParam("idopcion") Integer opcion,
                                          @RequestParam("idmascota") Integer mascota,
                                          @RequestParam("idcuenta") Integer cuenta,
                                          @RequestParam("horainicio") String horainicio,
                                          @RequestParam("entrega") String entrega,
                                          @RequestParam("duracion") Integer duracion,
                                          @RequestParam("idresponsable") Integer resp,
                                          RedirectAttributes attr) {

        OpcionServicio opServ = new OpcionServicio();
        opServ.setIdopcion(opcionRepository.findById(opcion).get());

        Servicio serv = new Servicio();
        serv.setIdmascota(mascotaRepository.findById(mascota).get());
        serv.setIdcuenta(cuentaRepository.findById(cuenta).get());

        String[] parts = horainicio.split("T");
        String part1 = parts[0];
        String part2 = parts[1] + ":00";
        String b = part1 + " " + part2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
        LocalDateTime datetime = LocalDateTime.parse(b, formatter);
        serv.setHorainicio(datetime);
        serv.setDuracion(duracion);
        serv.setEntrega(entrega);
        serv.setIdresponsable(responsableRepository.findById(resp).get());
        opServ.setIdservicio(serv);

        servicioRepository.save(serv);
        opcionServicioRepository.save(opServ);
        attr.addFlashAttribute("msg", "0");
        return "redirect:/servicio/lista";
    }

}
