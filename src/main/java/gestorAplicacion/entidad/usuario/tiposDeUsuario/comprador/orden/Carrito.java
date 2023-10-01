package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;


public class Carrito extends Transaccion {

    public Carrito(Comprador comprador) {
        super(comprador);
    }

    @Override
    protected void agregarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        //TODO: Realizar implementación
    }

    @Override
    protected void removerProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        //TODO: Realizar implementación
    }
}
