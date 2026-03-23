package com.upiiz.materias.services;

import com.upiiz.materias.models.Materia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaService {
    private List<Materia> materias = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Materia> obtenerTodas() {
        return materias;
    }

    public Materia obtenerPorId(Long id) {
        return materias.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void guardar(Materia materia) {
        materia.setId(idCounter++);
        materias.add(materia);
    }

    public void actualizar(Materia materia) {
        for (int i = 0; i < materias.size(); i++) {
            if (materias.get(i).getId().equals(materia.getId())) {
                materias.set(i, materia);
                return;
            }
        }
    }

    public void eliminar(Long id) {
        materias.removeIf(m -> m.getId().equals(id));
    }
}