package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;

public class Devolucion extends Transaccion {


    public Devolucion(Comprador comprador) {
        super(comprador);
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion) {
        //TODO: Realizar implementación
    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion) {
        //TODO: Realizar implementación
    }
}
