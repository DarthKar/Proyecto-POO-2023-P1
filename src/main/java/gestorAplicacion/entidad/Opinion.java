package gestorAplicacion.entidad;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;

public class Opinion {
    protected String opinion;
    protected int valoracion;
    protected Comprador creador;        // Creacion de atributo creador para poder diferenciar entre una opinion y otra, filtrar por edicion de opiniones

   
    public Opinion(String opinion, int valoracion) {
        this.opinion = opinion;
        this.valoracion = valoracion;
    }

    public String getOpinion() {
        return opinion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
     public void setCreador(Comprador creador) {
        this.creador = creador;
    }                                                             //setter y getter de creador
     
    public Comprador getCreador() {
        return creador;
    }

}
