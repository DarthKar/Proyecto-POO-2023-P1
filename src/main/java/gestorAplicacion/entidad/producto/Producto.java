package gestorAplicacion.entidad.producto;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Producto implements Serializable {

    private Long id;
    private String nombre;
    private Categoria categoria;
    private List<OpinionProducto> opinion;
    private List<Publicacion> publicaciones;
    private List<Comprador> compradores;
    private List<Comprador> resenadores;

 
    public Producto(Long id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.opinion = new ArrayList<>();
        this.compradores= new ArrayList<>();
        this.publicaciones = new ArrayList<>();
    }
    
    public void addOpinionProducto(OpinionProducto resena){
        if(Objects.isNull(resena ))
            return;                                                 // Creacion del metodo addOpinion como en la clase vendedor
        opinion.add(resena);
        
    }
    
    public boolean existeResena(Comprador comprador){
        return resenadores.contains(comprador);                  // Creacion del metodo ExisteResena que comprueba si ya hay una rese�a
   
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

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void agregarPublicacion(Publicacion publicacion){
        publicaciones.add(publicacion);
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void setComprodores(List compradores){
        this.compradores=compradores;
    }
    
    public List<Comprador> getCompradores(){
        return compradores;
    }
    
    public List<Comprador> getResenadores() {
        return resenadores;
    }
       public void setOpinion(List<OpinionProducto> opinion) {
        this.opinion = opinion;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }

    public void setResenadores(List<Comprador> resenadores) {
        this.resenadores = resenadores;
    }

}
