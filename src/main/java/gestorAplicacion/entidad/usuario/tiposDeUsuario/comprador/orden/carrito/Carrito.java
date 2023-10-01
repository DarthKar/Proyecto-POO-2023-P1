package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.carrito;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Transaccion;


public class Carrito extends Transaccion {

    public Carrito(Comprador comprador) {
        super(comprador);
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion, int cantidad) {

    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion, int cantidad) {

    }
}
