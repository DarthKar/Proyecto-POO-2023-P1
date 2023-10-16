package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Transaccion implements Serializable {

    protected long id;

    protected Comprador comprador;
    protected  List<ProductoTransaccion> productosTransaccion;

    public Transaccion(long id, Comprador comprador) {
        this.id = id;
        this.comprador = comprador;
        productosTransaccion = new ArrayList<>();
    }

    public Comprador getComprador() {
        return comprador;
    }

    public List<ProductoTransaccion> getProductosTransaccion() {
        return productosTransaccion;
    }

    public long getId() {
        return id;
    }

    public abstract void agregarProducto(ProductoTransaccion productoTransaccion);
    public abstract void removerProducto(ProductoTransaccion productoTransaccion);
    public abstract void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad);

}
