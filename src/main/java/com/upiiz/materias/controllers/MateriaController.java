package com.upiiz.materias.controllers;

import com.upiiz.materias.dto.MateriaDTO;
import com.upiiz.materias.models.Materia;
import com.upiiz.materias.services.MateriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public String listarMaterias(Model model) {
        model.addAttribute("materias", materiaService.obtenerTodas());
        return "listado";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("materia", new MateriaDTO());
        return "formulario-crear";
    }

    @PostMapping("/guardar")
    public String guardarMateria(@ModelAttribute MateriaDTO materiaDTO) {
        Materia materia = new Materia(null, materiaDTO.getNombre(), materiaDTO.getCreditos());
        materiaService.guardar(materia);
        return "redirect:/materias";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Materia materia = materiaService.obtenerPorId(id);
        if (materia != null) {
            MateriaDTO dto = new MateriaDTO();
            dto.setId(materia.getId());
            dto.setNombre(materia.getNombre());
            dto.setCreditos(materia.getCreditos());
            model.addAttribute("materia", dto);
            return "formulario-actualizar";
        }
        return "redirect:/materias";
    }

    @PostMapping("/actualizar")
    public String actualizarMateria(@ModelAttribute MateriaDTO materiaDTO) {
        Materia materia = new Materia(materiaDTO.getId(), materiaDTO.getNombre(), materiaDTO.getCreditos());
        materiaService.actualizar(materia);
        return "redirect:/materias";
    }

    @GetMapping("/eliminar/{id}")
    public String mostrarFormularioEliminar(@PathVariable Long id, Model model) {
        Materia materia = materiaService.obtenerPorId(id);
        if (materia != null) {
            model.addAttribute("materia", materia);
            return "formulario-eliminar";
        }
        return "redirect:/materias";
    }

    @PostMapping("/borrar/{id}")
    public String borrarMateria(@PathVariable Long id) {
        materiaService.eliminar(id);
        return "redirect:/materias";
    }
}