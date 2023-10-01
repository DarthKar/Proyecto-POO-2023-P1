package baseDatos.impl;


import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UsuarioRepositorio {
    public void agregarVendedor(Vendedor vendedor){
        if (Objects.isNull(vendedor))
            return;
        Repositorio.getVendedores().add(vendedor);
    }

    public void eliminar(long id){
        List<Vendedor> vendedores = Repositorio.getVendedores();
        Optional<Vendedor> vendedor = vendedores.stream().filter(v -> v.getId() == id).findFirst();
        vendedor.ifPresent(vendedores::remove);
    }

    public Optional<Vendedor> obtenerPorId(long id){
        List<Vendedor> vendedores = Repositorio.getVendedores();
        return vendedores.stream().filter(v -> v.getId() == id).findFirst();
    }

}
