package gestorAplicacion.entidad.producto;

import gestorAplicacion.entidad.Opinion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;

public class OpinionProducto extends Opinion {
    private Producto producto;
    private Comprador comprador;

    public OpinionProducto(String opinion, int valoracion, Producto producto, Comprador comprador) { //Ajuste al contructor para que pueda asignar valores a comprador y vendedor
        super(opinion, valoracion);
        this.producto=producto;
        this.comprador=comprador;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
     @Override
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
    @Override
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Producto getProducto() {
        return producto;
    }

    public Comprador getComprador() {
        return comprador;
    }
}
