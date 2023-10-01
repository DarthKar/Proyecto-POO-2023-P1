package gestorAplicacion.entidad;

public class Opinion {
    protected String opinion;
    protected int valoracion;

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
}
