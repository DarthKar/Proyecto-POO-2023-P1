package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;

public class Orden extends Transaccion {
    boolean tieneDevoluciones;

    public Orden(Long id,Comprador comprador) {
        super(id, comprador);
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
        this.productosTransaccion.add(productoTransaccion);
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
