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

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public String mostrarEspProducto(){
        return "Nombre: "+this.getPublicacion().getProducto().getNombre()+"\nCantidad: "+this.getCantidad()+"\nPrecio: "+this.getPublicacion().getPrecio()
                +"\nSubtotal: "+(this.getCantidad()*this.getPublicacion().getPrecio());
    }
}
