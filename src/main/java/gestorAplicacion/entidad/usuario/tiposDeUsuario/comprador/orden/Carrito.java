package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;


public class Carrito extends Transaccion {

    public Carrito(long id, Comprador comprador) {
        super(id, comprador);
    }
    
    public void mostrarCarrito(Carrito carrito){
        int contador1 = 1;
        for (ProductoTransaccion productosTransaccion : carrito.getProductosTransaccion()) {
            System.out.println(contador1 + ". " + productosTransaccion.mostrarEspProducto());
            contador1++;
         }
    }


    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion) {
        this.productosTransaccion.add(productoTransaccion);
    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion) {                 //implentado para remover productos a la lista en transaccion
        if (productosTransaccion.contains(productoTransaccion)) {
            this.productosTransaccion.remove(productoTransaccion);
        }
    }

    @Override
    public void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        productoTransaccion.setCantidad(cantidad);                                                                           //implementado para modifcar la cantidad de un producto en especifico
    }
}
