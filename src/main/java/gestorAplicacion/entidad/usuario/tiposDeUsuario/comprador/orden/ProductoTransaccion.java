package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.ProductoVendedor;

public class ProductoTransaccion {
    protected ProductoVendedor productoVendedor;
    protected int cantidad;

    public ProductoTransaccion(ProductoVendedor productoVendedor, int cantidad) {
        this.productoVendedor = productoVendedor;
        this.cantidad = cantidad;
    }

    public ProductoVendedor getProductoVendedor() {
        return productoVendedor;
    }

    public int getCantidad() {
        return cantidad;
    }
}
