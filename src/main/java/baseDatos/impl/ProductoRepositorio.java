package baseDatos.impl;

import gestorAplicacion.entidad.producto.Producto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductoRepositorio {
    public static void guardar(Producto producto) {
        if (Objects.isNull(producto))
            return;
        Repositorio.guardar(producto);
    }

    public static Optional<Producto> getProductoPorId(long id) {
        return Repositorio.obtenerProducto(id);
    }

    public static List<Producto> getProductos() {
        return Repositorio.obtenerProductos();
    }

}
