package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public abstract void agregarProducto(ProductoTransaccion productoTransaccion, int cantidad);
    public abstract void removerProducto(ProductoTransaccion productoTransaccion, int cantidad);

}
