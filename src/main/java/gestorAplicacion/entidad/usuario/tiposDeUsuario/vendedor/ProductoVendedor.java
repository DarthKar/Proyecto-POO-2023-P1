package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor;

import gestorAplicacion.entidad.producto.Producto;

public class ProductoVendedor {
    private Vendedor vendedor;
    private Producto producto;
    private int inventario;
    private float precio;

    public ProductoVendedor(Vendedor vendedor, Producto producto, int inventario, float precio) {
        this.vendedor = vendedor;
        this.producto = producto;
        this.inventario = inventario;
        this.precio = precio;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
