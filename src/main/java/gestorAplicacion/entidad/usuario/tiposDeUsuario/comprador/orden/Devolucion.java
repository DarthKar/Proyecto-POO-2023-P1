package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import baseDatos.impl.Repositorio;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class Devolucion extends Transaccion {
    Orden orden;

    public Devolucion(Comprador comprador) {
        super(comprador);
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
        orden.setTieneDevoluciones(productosTransaccion.isEmpty());
    }

    @Override
    public void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        Publicacion publicacion = productoTransaccion.getPublicacion();

        int diferencia = productoTransaccion.getCantidad() - cantidad;

        publicacion.aumentarInventario(diferencia);
        productoTransaccion.setCantidad(cantidad);
    }

    public Orden getOrden(){
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
