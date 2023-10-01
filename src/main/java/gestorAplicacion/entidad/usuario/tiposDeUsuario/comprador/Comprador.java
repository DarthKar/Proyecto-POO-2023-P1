package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.carrito.Carrito;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.devolucion.Devolucion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.orden.Orden;

import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario {
    private Membresia membresia;
    private List<Orden> ordenes;
    private List<Devolucion> devoluciones;
    private Carrito carrito;

    public Comprador(long id, String nombre, String apellido, String correo, Membresia membresia) {
        super(id, nombre, apellido, correo);
        this.membresia = membresia;
        ordenes = new ArrayList<>();
        devoluciones = new ArrayList<>();
        carrito = new Carrito(this);
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

    public void setDevoluciones(List<Devolucion> devoluciones) {
        this.devoluciones = devoluciones;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}
