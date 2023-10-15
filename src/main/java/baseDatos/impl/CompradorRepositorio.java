package baseDatos.impl;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CompradorRepositorio{

    public static void guardar(Comprador comprador) {
        if (Objects.isNull(comprador))
            return;
        Repositorio.guardar(comprador);
    }

    public static Optional<Comprador> obtenerPorId(long id) {
        return Repositorio.obtenerCompradorPorId(id);
    }

    public static List<Comprador> obtener() {
        return Repositorio.obtenerCompradores();
    }

    public static void eliminar(long id) {
        Repositorio.eliminarComprador(id);
    }

}
