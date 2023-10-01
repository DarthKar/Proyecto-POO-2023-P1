package baseDatos.impl;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos implements Serializable {

    private List<Vendedor> vendedores;
    private List<Comprador> compradores;

    public BaseDatos() {
        vendedores = new ArrayList<>();
        compradores = new ArrayList<>();
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Comprador> getCompradores() {
        return compradores;
    }

}
