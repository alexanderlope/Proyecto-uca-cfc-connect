package sv.edu.udb.cfcconnect.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sv.edu.udb.cfcconnect.rol.domain.Rol;
import sv.edu.udb.cfcconnect.rol.repository.RolRepository;
import sv.edu.udb.cfcconnect.usuario.domain.Usuario;
import sv.edu.udb.cfcconnect.usuario.repository.UsuarioRepository;

@Component
public class AdminBootstrap implements ApplicationRunner {

    private final Environment environment;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminBootstrap(Environment environment, RolRepository rolRepository,
                          UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.environment = environment;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        String email = environment.getProperty("CFC_ADMIN_EMAIL");
        String password = environment.getProperty("CFC_ADMIN_PASSWORD");
        if (!StringUtils.hasText(email) || !StringUtils.hasText(password)) return;
        if (password.length() < 12) {
            throw new IllegalStateException("CFC_ADMIN_PASSWORD debe tener al menos 12 caracteres");
        }
        if (usuarioRepository.findByEmailIgnoreCase(email).isPresent()) return;

        Rol adminRole = rolRepository.findByNombreIgnoreCase("ADMIN").orElseGet(() -> {
            Rol role = new Rol();
            role.setNombre("ADMIN");
            return rolRepository.save(role);
        });
        Usuario admin = new Usuario();
        admin.setNombre("Administrador");
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRol(adminRole);
        admin.setEstado(true);
        usuarioRepository.save(admin);
    }
}
