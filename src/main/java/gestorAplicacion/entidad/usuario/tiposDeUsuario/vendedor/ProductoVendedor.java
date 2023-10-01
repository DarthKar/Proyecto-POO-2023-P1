package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor;

import gestorAplicacion.entidad.producto.Producto;

public class ProductoVendedor {
    private Vendedor vendedor;
    private Producto producto;
    private int inventario;
    private float precio;
    private boolean oculto;

    public ProductoVendedor(Vendedor vendedor, Producto producto, int inventario, float precio) {
        this.vendedor = vendedor;
        this.producto = producto;
        this.inventario = inventario;
        this.precio = precio;
        this.oculto = false;
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

    protected void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public float getPrecio() {
        return precio;
    }

    protected void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isOculto() {
        return oculto;
    }

    protected void setOculto(boolean oculto) {
        this.oculto = oculto;
    }
}
