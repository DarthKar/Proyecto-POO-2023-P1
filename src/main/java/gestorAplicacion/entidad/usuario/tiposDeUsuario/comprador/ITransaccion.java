package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

public interface ITransaccion {
    void agregarProducto(ProductoTransaccion productoTransaccion);
    void removerProducto(ProductoTransaccion productoTransaccion);
    void modificarProducto(ProductoTransaccion productoTransaccion, int cantidad);
}
