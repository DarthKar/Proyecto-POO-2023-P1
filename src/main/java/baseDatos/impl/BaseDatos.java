package baseDatos.impl;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos implements Serializable {

    private List<Vendedor> vendedores;
    private List<Comprador> compradores;
    private static List<Producto> productos;

    public BaseDatos() {
        vendedores = new ArrayList<>();
        compradores = new ArrayList<>();
        productos = new ArrayList<>();
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Comprador> getCompradores() {
        return compradores;
    }

    public static List<Producto> getProductos(){
        return productos;
    }

}
