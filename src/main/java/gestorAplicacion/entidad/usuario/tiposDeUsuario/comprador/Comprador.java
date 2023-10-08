package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import gestorAplicacion.entidad.Opinion; // Import para usar Ambas listas
import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Carrito;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Devolucion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;

import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario {
    private Membresia membresia;
    private List<Orden> ordenes;
    private List<Devolucion> devoluciones;
    private Carrito carrito;
    private List<Opinion> resenasDeProductos; //Se guardaran aqui las reseñas a productos
    private List<Opinion> resenasDeTiendas; //Se guardaran Reseñas De Productos
             
    public Comprador(long id, String nombre, String apellido, String correo, Membresia membresia) {
        super(id, nombre, apellido, correo);
        this.membresia = membresia;
        ordenes = new ArrayList<>();
        devoluciones = new ArrayList<>();
        carrito = new Carrito(this);
        resenasDeProductos = new ArrayList<>(); // Se crean Ambas listas Para Reseñas hechas a tiendas y productos
        resenasDeTiendas = new ArrayList<>();
        
    }

    public List<Opinion> getResenasDeProductos() {  
        return resenasDeProductos;
    }
    
    public void SetResenasDeProductos(List<Opinion> resenaP){ 
        this.resenasDeProductos=resenaP;
    }

    public List<Opinion> getResenasDeTiendas() {                   //Metodos get y set para esas reseñas
        return resenasDeTiendas;
        
    }
     public void SetResenasDeTiendas(List<Opinion> resenaT){ 
        this.resenasDeProductos=resenaT;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    public void addDevoluciones(List<Devolucion> devoluciones) {
        this.devoluciones = devoluciones;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    
}
