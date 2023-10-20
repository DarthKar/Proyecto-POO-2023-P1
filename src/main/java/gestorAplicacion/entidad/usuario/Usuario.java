package gestorAplicacion.entidad.usuario;

import java.io.Serial;
import java.io.Serializable;

public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected long id;
    protected String nombre;
    protected String apellido;
    protected String correo;

    public Usuario(long id, String nombre, String apellido, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public long getId() {
        return id;
    }
    
    public void setId(Long id){  
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto(){
        return (nombre + " " + apellido).trim();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
