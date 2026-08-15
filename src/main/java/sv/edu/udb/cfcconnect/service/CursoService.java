package sv.edu.udb.cfcconnect.service;

import sv.edu.udb.cfcconnect.repository.domain.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> findAll();

    Curso findById(Long id);
}
