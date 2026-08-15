package sv.edu.udb.cfcconnect.repository.domain;

public class Cliente {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;

    public Cliente() {}

    public Cliente(Long id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public static ClienteBuilder builder(){
        return new ClienteBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public static class ClienteBuilder{

        private Long id;
        private String nombre;
        private String correo;
        private String telefono;

        public ClienteBuilder id(Long id){
            this.id=id;
            return this;
        }

        public ClienteBuilder nombre(String nombre){
            this.nombre=nombre;
            return this;
        }

        public ClienteBuilder correo(String correo){
            this.correo=correo;
            return this;
        }

        public ClienteBuilder telefono(String telefono){
            this.telefono=telefono;
            return this;
        }

        public Cliente build(){
            return new Cliente(id,nombre,correo,telefono);
        }
    }

}
