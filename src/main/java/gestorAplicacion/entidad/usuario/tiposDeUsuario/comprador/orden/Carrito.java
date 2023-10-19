package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;


public class Carrito extends Transaccion {

    public Carrito(Long id, Comprador comprador) {
        super(id,comprador);
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion) {
        for (int i = 0; i<productoTransaccion.getCantidad();i++){                                               //implentado para agregar productos a la lista en transaccion
            this.productosTransaccion.add(productoTransaccion);
        }
    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion,int cantidad) {                 //implentado para remover productos a la lista en transaccion
        for (int i = 0; i<productoTransaccion.getCantidad();i++){
            if (productosTransaccion.contains(productoTransaccion)){
            this.productosTransaccion.remove(productoTransaccion);
            }else{break;}
        }
    }

    @Override
    public void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        
    }
}
