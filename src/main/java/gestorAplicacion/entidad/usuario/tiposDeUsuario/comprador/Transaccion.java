package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Transaccion implements Serializable {

    protected long id;

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

    public long getId() {
        return id;
    }

    public abstract void agregarProducto(ProductoTransaccion productoTransaccion);
    public abstract void removerProducto(ProductoTransaccion productoTransaccion);


    public List<ProductoTransaccion> obtenerProductosPorCantidad(int min){
        return obtenerProductosPorCantidad(min, Integer.MAX_VALUE);
    }

    public List<ProductoTransaccion> obtenerProductosPorCantidad(int min, int max){
        return productosTransaccion.stream()
                .filter(productoTransaccion ->  productoTransaccion.getCantidad() >= min && productoTransaccion.getCantidad() < max)
                .toList();
    }


}
