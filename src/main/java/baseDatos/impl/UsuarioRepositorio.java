package baseDatos.impl;


import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UsuarioRepositorio {

    public static void guardar(Vendedor vendedor) {
        if (Objects.isNull(vendedor))
            return;
        Repositorio.guardar(vendedor);
    }

    public static Optional<Vendedor> obtenerPorId(long id) {
        return Repositorio.obtenerVendedorPorId(id);
    }

    public static List<Vendedor> obtener() {
        return Repositorio.obtenerVendedores();
    }

    public static void eliminar(long id) {
        Repositorio.eliminarVendedor(id);
    }
}
