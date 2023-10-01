package gestorAplicacion.entidad.producto;

import gestorAplicacion.entidad.Opinion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;

public class OpinionProducto extends Opinion {
    private Producto producto;
    private Comprador comprador;

    public OpinionProducto(String opinion, int valoracion) {
        super(opinion, valoracion);
    }

    public Producto getProducto() {
        return producto;
    }

    public Comprador getComprador() {
        return comprador;
    }
}
