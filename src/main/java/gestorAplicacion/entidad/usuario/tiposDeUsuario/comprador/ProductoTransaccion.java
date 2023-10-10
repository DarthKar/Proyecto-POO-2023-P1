package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;

import java.io.Serializable;

public class ProductoTransaccion implements Serializable {
    protected Publicacion publicacion;
    protected int cantidad;

    public ProductoTransaccion(Publicacion publicacion, int cantidad) {
        this.publicacion = publicacion;
        this.cantidad = cantidad;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public int getCantidad() {
        return cantidad;
    }
}
