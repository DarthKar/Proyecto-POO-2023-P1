package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Transaccion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected long id;
    protected Comprador comprador;
    protected  List<ProductoTransaccion> productosTransaccion;

    public Transaccion(long id, Comprador comprador) {
        this.id = id;
        this.comprador = comprador;
        productosTransaccion = new ArrayList<>();
    }

    public Comprador getComprador() {
        return comprador;
    }

    public List<ProductoTransaccion> getProductosTransaccion() {
        return productosTransaccion;
    }
    
    public void setProductosTransaccion(List<ProductoTransaccion> productoTra) {       //metodo que uso para asignarle a las ordenes la misma lista de productos que el carrito de compras
        this.productosTransaccion = productoTra;
    }

    public long getId() {
        return id;
    }
    
    public final float calcularTotal(){
        float Total = 0;
        for(ProductoTransaccion proTran : productosTransaccion){                    //metodo para obtener el total de una transaccion o bien del  carrito
            Total += proTran.getSubTotal();
        }
        return Total;
    }

    public abstract void agregarProducto(ProductoTransaccion productoTransaccion);
    public abstract void removerProducto(ProductoTransaccion productoTransaccion);
    public abstract void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad);

}
