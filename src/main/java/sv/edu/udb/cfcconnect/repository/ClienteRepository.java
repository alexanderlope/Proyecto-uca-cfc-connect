package sv.edu.udb.cfcconnect.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import sv.edu.udb.cfcconnect.repository.domain.Cliente;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ClienteRepository {
    private List<Cliente> clientes;

    @PostConstruct
    public void init(){

        clientes = List.of(

                Cliente.builder()
                        .id(1L)
                        .nombre("Juan Pérez")
                        .correo("juan@gmail.com")
                        .telefono("7777-1111")
                        .build(),

                Cliente.builder()
                        .id(2L)
                        .nombre("Ana López")
                        .correo("ana@gmail.com")
                        .telefono("7777-2222")
                        .build()
        );

    }

    public List<Cliente> findAll(){
        return clientes;
    }

    public Cliente findById(Long id){

        return clientes.stream()
                .filter(c->c.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

    }
}
