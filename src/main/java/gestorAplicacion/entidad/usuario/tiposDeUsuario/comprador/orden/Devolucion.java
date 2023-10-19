package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import baseDatos.impl.Repositorio;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class Devolucion extends Transaccion {
    private Orden orden;

    public Devolucion(long id,Comprador comprador) {
        super(id,comprador);
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion) {
        Publicacion publicacion = productoTransaccion.getPublicacion();
        publicacion.reducirInventario(productoTransaccion.getCantidad());
        productosTransaccion.add(productoTransaccion);
        orden.setTieneDevoluciones(true);
    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion) {
        productoTransaccion.getPublicacion().aumentarInventario(productoTransaccion.getCantidad());
        productosTransaccion.remove(productoTransaccion);
        orden.setTieneDevoluciones(!productosTransaccion.isEmpty());
    }

    @Override
    public void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        Publicacion publicacion = productoTransaccion.getPublicacion();

        if (cantidad == 0) {
            removerProducto(productoTransaccion);
            return;
        }

        if (cantidad > productoTransaccion.getCantidad()) {
            int diferencia = cantidad - productoTransaccion.getCantidad();
            publicacion.reducirInventario(diferencia);
        } else {
            int diferencia = productoTransaccion.getCantidad() - cantidad;
            publicacion.aumentarInventario(diferencia);
        }

        productoTransaccion.setCantidad(cantidad);
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public List<ProductoTransaccion> getProductosDevolucion(){
        return orden.getProductosTransaccion().stream()
                .filter(productoOrden ->
                        !productoOrden.getPublicacion().getProducto().isPerecedero() &&
                        productosTransaccion.parallelStream()
                                .noneMatch(productoDevolucion -> productoOrden.getPublicacion().equals(productoDevolucion.getPublicacion())))
                .toList();
    }

    public ProductoTransaccion getOrdenProductoTransacction(Publicacion publicacion){
        return orden.getProductosTransaccion().stream()
                .filter(productoOrden -> productoOrden.getPublicacion().equals(publicacion))
                .findAny().get();
    }
}
