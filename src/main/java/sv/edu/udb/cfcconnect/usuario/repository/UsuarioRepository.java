package sv.edu.udb.cfcconnect.usuario.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.cfcconnect.usuario.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @EntityGraph(attributePaths = "rol")
    Optional<Usuario> findByEmailIgnoreCase(String email);
}
