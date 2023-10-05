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

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    @Override
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
    @Override
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }
}
