package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.opinion.OpinionVendedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Vendedor extends Usuario {

    private List<Publicacion> publicaciones;
    private List<OpinionVendedor> opinionesVendedor;
    private List<Comprador> compradores;
    private List<Comprador> resenadores;

    public Vendedor(long id, String nombre, String apellido, String correo) {
        super(id, nombre, apellido, correo);
        this.publicaciones = new ArrayList<>();
        this.opinionesVendedor = new ArrayList<>();
        this.compradores = new ArrayList<>(); // Nuevo array para verificar si el comprador que va a crear una opinion si ha comprado en esa tienda
        
    }

    public List<Publicacion> getProductoVendedor() {
        return publicaciones;
    }

    protected void agregarPublicacion(Publicacion publicacion) {
        if(Objects.isNull(publicacion))
            return;
        publicaciones.add(publicacion);
    }

    public List<OpinionVendedor> getOpinionVendedor() {
        return opinionesVendedor;
    }

    public void addOpinionVendedor(OpinionVendedor opinionVendedor) {
        if(Objects.isNull(opinionVendedor))
            return;
        opinionesVendedor.add(opinionVendedor);
    }
    
      public boolean existeResena(Comprador comprador){
        return resenadores.contains(comprador);                  // Creacion del metodo ExisteResena que comprueba si ya hay una rese�a
   
    }
    
    public List<Comprador> getCompradores(){
        return compradores;                                 // Getter y setter para esas listas
    }                 
    
    public List<Publicacion> getPublicaciones(){                    //Getter para la lista de publicaciones del respectivo vendedor
        return this.publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void setOpinionesVendedor(List<OpinionVendedor> opinionesVendedor) {
        this.opinionesVendedor = opinionesVendedor;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }
    
    public void setResenadores(List<Comprador> resenadores) {
        this.resenadores = resenadores;
    }

    public List<Comprador> getResenadores() {
        return resenadores;
    }

    public void crearPublicacion(Producto producto, int inventario, float precio){
       Optional<Publicacion> publicacionOptional = publicaciones.stream().filter(publicacion -> publicacion.getProducto().equals(producto)).findAny();
       if (publicacionOptional.isPresent()){
           throw new IllegalArgumentException("Ya existe una publicación creada con el producto %s".formatted(producto.getNombre()));
       }
       new Publicacion(this, producto, inventario, precio);
    }
}
