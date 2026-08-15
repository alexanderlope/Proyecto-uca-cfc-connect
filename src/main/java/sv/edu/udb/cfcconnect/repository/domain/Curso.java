package sv.edu.udb.cfcconnect.repository.domain;

public class Curso {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer cupo;

    public Curso(){}

    public Curso(Long id,String nombre,Double precio,Integer cupo){
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.cupo=cupo;
    }

    public static CursoBuilder builder(){
        return new CursoBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getCupo() {
        return cupo;
    }

    public static class CursoBuilder{

        private Long id;
        private String nombre;
        private Double precio;
        private Integer cupo;

        public CursoBuilder id(Long id){
            this.id=id;
            return this;
        }

        public CursoBuilder nombre(String nombre){
            this.nombre=nombre;
            return this;
        }

        public CursoBuilder precio(Double precio){
            this.precio=precio;
            return this;
        }

        public CursoBuilder cupo(Integer cupo){
            this.cupo=cupo;
            return this;
        }

        public Curso build(){
            return new Curso(id,nombre,precio,cupo);
        }
    }
}
