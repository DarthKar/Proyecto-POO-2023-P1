package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.opinion;

import gestorAplicacion.entidad.Opinion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

public class OpinionVendedor extends Opinion {
    private Comprador comprador;
    private Vendedor vendedor;

    public OpinionVendedor(String opinion, int valoracion, Comprador comprador, Vendedor vendedor) {
        super(opinion, valoracion);
        this.comprador = comprador;
        this.vendedor = vendedor;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
}
