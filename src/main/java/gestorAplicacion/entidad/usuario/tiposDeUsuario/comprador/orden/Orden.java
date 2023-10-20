package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;

public class Orden extends Transaccion {

    boolean tieneDevoluciones;

    public Orden(Comprador comprador) {
        super(123,comprador);
    }

    public boolean isTieneDevoluciones() {
        return tieneDevoluciones;
    }

    public void setTieneDevoluciones(boolean tieneDevoluciones) {
        this.tieneDevoluciones = tieneDevoluciones;
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion) {
        //TODO: Implementar validaciones de inventario.
        productosTransaccion.add(productoTransaccion);
    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion) {
        //TODO: Realizar implementación
    }

    @Override
    public void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        //TODO: Realizar implementación
    }
}
