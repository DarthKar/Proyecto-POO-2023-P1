package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Transaccion implements Serializable {

    protected Comprador comprador;
    protected List<ProductoTransaccion> productosTransaccion;

    public Transaccion(Comprador comprador) {
        this.comprador = comprador;
        productosTransaccion = new ArrayList<>();
    }

    public Comprador getComprador() {
        return comprador;
    }

    public List<ProductoTransaccion> getProductosTransaccion() {
        return productosTransaccion;
    }

    protected abstract void agregarProducto(ProductoTransaccion productoTransaccion, int cantidad);
    protected abstract void removerProducto(ProductoTransaccion productoTransaccion, int cantidad);

}
