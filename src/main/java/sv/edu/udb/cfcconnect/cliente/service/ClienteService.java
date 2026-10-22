package sv.edu.udb.cfcconnect.cliente.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.udb.cfcconnect.cliente.dto.ClienteRequest;
import sv.edu.udb.cfcconnect.cliente.dto.ClienteResponse;

import java.util.List;

public interface ClienteService {
    List<ClienteResponse> findAll();
    ClienteResponse findById(Long id);
    ClienteResponse create(ClienteRequest request);
    ClienteResponse update(Long id, ClienteRequest request);
    void delete(Long id);

    /** Busqueda paginada y ordenada por nombre y/o correo. */
    Page<ClienteResponse> buscar(String nombre, String correo, Pageable pageable);
}
