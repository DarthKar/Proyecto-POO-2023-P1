package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.devolucion;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Transaccion;

import java.util.List;

public class Devolucion extends Transaccion {


    public Devolucion(Comprador comprador) {
        super(comprador);
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion, int cantidad) {

    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion, int cantidad) {

    }
}
