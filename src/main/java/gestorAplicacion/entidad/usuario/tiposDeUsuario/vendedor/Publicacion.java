package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor;

import gestorAplicacion.entidad.producto.Producto;

import java.io.Serializable;

public class Publicacion implements Serializable {

    private Vendedor vendedor;
    private Producto producto;
    private int inventario;
    private float precio;
    private boolean oculto;
    private float coste;

    public Publicacion(Vendedor vendedor, Producto producto, int inventario, float precio,float coste) {
        this.vendedor = vendedor;
        this.producto = producto;
        this.inventario = inventario;
        this.precio = precio;
        this.coste=coste;
        this.oculto = false;
        vendedor.agregarPublicacion(this);
        producto.agregarPublicacion(this);
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
    public float getCoste() {
    	return coste;
    }
    
    public void setCoste(float coste) {
    	this.coste=coste;
    }

    protected void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public void aumentarInventario(int cantidad){
        if(cantidad <= 0){
            throw new IllegalArgumentException("La cantidad ingresada no es valida");
        }
        inventario += cantidad;
    }

    public void reducirInventario(int cantidad){
        if(cantidad <= 0){
            throw new IllegalArgumentException("La cantidad ingresada no es valida");
        }
        inventario -= cantidad;
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

public float utilidadProducto() {
	return (this.precio - this.coste);
	

}



}
