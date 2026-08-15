package sv.edu.udb.cfcconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.udb.cfcconnect.repository.domain.Cliente;
import sv.edu.udb.cfcconnect.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service){
        this.service=service;
    }

    @GetMapping
    public List<Cliente> getClientes(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable Long id){
        return service.findById(id);
    }
}
