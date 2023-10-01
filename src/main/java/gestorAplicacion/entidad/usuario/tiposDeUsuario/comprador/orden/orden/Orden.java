package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Transaccion;

public class Orden extends Transaccion {

    public Orden(Comprador comprador) {
        super(comprador);
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        super.productosTransaccion.add(productoTransaccion);
    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion, int cantidad) {

    }
}
