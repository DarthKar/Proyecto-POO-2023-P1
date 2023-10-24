package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;

public class Orden extends Transaccion {
    boolean tieneDevoluciones;
    public boolean pagar;

    public Orden(Long id,Comprador comprador) {
        super(id, comprador);
        pagar = false;
    }

    public void pagado(){
        this.pagar = true;
    }

    public void mostrarProductoorden(Orden orden){
        int contador2 = 1;
        for (ProductoTransaccion productosTransaccion : orden.getProductosTransaccion()) {
            System.out.println(contador2 + ". " + productosTransaccion.mostrarEspProducto());
            contador2++;
         }
    }

    public boolean isTieneDevoluciones() {
        return tieneDevoluciones;
    }

    public void setTieneDevoluciones(boolean tieneDevoluciones) {
        this.tieneDevoluciones = tieneDevoluciones;
    }

    public String mostrarOrden(){
        return"Comprado por: "+ this.comprador.getNombreCompleto()+" \nid de la compra : "+this.id+"\nEl total a pagar es: "+this.calcularTotal()+"\n-------------------------------";    //metodo para mostrar una orden individualmente
    }

    @Override
    public void agregarProducto(ProductoTransaccion productoTransaccion) {
        this.productosTransaccion.add(productoTransaccion);                 //metodo para agregar un producto a la lista de productos
    }

    @Override
    public void removerProducto(ProductoTransaccion productoTransaccion) {      //implentado para remover productos a la lista en transaccion
        if (productosTransaccion.contains(productoTransaccion)) {
            this.productosTransaccion.remove(productoTransaccion);
        }
    }

    @Override
    public void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad) {
        productoTransaccion.setCantidad(cantidad);                                                //implementado para modifcar la cantidad de un producto en especifico
    }
}
