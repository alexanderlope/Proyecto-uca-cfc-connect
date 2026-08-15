package sv.edu.udb.cfcconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.udb.cfcconnect.repository.domain.Curso;
import sv.edu.udb.cfcconnect.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service){
        this.service=service;
    }

    @GetMapping
    public List<Curso> getCursos(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Curso getCurso(@PathVariable Long id){
        return service.findById(id);
    }
}
