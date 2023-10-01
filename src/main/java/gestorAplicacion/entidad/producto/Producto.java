package gestorAplicacion.entidad.producto;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    private Long id;
    private String nombre;
    private Categoria categoria;
    private List<OpinionProducto> opinion;

    public Producto(Long id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.opinion = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<OpinionProducto> getOpiniones() {
        return opinion;
    }
}
