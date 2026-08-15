package sv.edu.udb.cfcconnect.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import sv.edu.udb.cfcconnect.repository.domain.Curso;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CursoRepository {
    private List<Curso> cursos;

    @PostConstruct
    public void init(){

        cursos = List.of(

                Curso.builder()
                        .id(1L)
                        .nombre("Spring Boot")
                        .precio(120.0)
                        .cupo(30)
                        .build(),

                Curso.builder()
                        .id(2L)
                        .nombre("React")
                        .precio(150.0)
                        .cupo(25)
                        .build()
        );

    }

    public List<Curso> findAll(){
        return cursos;
    }

    public Curso findById(Long id){

        return cursos.stream()
                .filter(c->c.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

    }

}
