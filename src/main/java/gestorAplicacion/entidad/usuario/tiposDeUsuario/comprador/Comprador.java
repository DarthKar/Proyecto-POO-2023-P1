package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import baseDatos.impl.CompradorRepositorio;
import baseDatos.impl.ProductoRepositorio;
import baseDatos.impl.UsuarioRepositorio;
import gestorAplicacion.entidad.Opinion; // Import para usar Ambas listas
import gestorAplicacion.entidad.producto.Categoria;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Carrito;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Devolucion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Comprador extends Usuario {
    private Membresia membresia;
    private List<Orden> ordenes;
    private List<Devolucion> devoluciones;
    private Carrito carrito;
    private List<Opinion> resenasDeProductos; //Se guardaran aqui las rese�as a productos
    private List<Opinion> resenasDeTiendas; //Se guardaran Rese�as De Productos

    public Comprador(long id, String nombre, String apellido, String correo, Membresia membresia) {
        super(id, nombre, apellido, correo);
        this.membresia = membresia;
        ordenes = new ArrayList<>();
        devoluciones = new ArrayList<>();
        carrito = new Carrito(this);
        resenasDeProductos = new ArrayList<>(); // Se crean Ambas listas Para Rese�as hechas a tiendas y productos
        resenasDeTiendas = new ArrayList<>();

    }

    public List<Opinion> getResenasDeProductos() {
        return resenasDeProductos;
    }

    public void SetResenasDeProductos(List<Opinion> resenaP) {
        this.resenasDeProductos = resenaP;
    }

    public List<Opinion> getResenasDeTiendas() {                   //Metodos get y set para esas rese�as
        return resenasDeTiendas;

    }

    public void SetResenasDeTiendas(List<Opinion> resenaT) {
        this.resenasDeProductos = resenaT;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    public void agregarDevolucion(Devolucion devolucion) {
        if (!devolucion.getProductosTransaccion().isEmpty()) {
            devoluciones.add(devolucion);
            CompradorRepositorio.guardar(this);
        }
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public static List<Comprador> obtenerCompradores() {
        return CompradorRepositorio.obtener();
    }

    public static Comprador obtenerCompradorPorId(long id) {
        return CompradorRepositorio.obtenerPorId(id).orElseThrow(() ->
                new IllegalArgumentException("Comprador con id %s no ha sido encontrado.".formatted(id)));
    }

    public static void eliminarComprador(long id) {

    }

    // TODO: Método temporal para agregar información por defecto a base de datos.
    public void agregarOrden(Orden orden) {
        ordenes.add(orden);
    }

    public List<Orden> getOrdenesValidasParaDevolucion() {
        return ordenes.stream().filter(orden ->
                        !orden.isTieneDevoluciones()
                                && orden.getProductosTransaccion()
                                .stream()
                                .anyMatch(productoTransaccion -> !productoTransaccion.getPublicacion().getProducto().getCategoria().isPerecedero()))
                .toList();
    }

    public static Comprador masComprador() {
        int tamanoOrdenes = 0;
        Comprador mascomprador = null;
        for (Comprador comprador : CompradorRepositorio.obtener()) {
            if (comprador.getOrdenes().size() > tamanoOrdenes) {
                tamanoOrdenes = comprador.getOrdenes().size();
                mascomprador = comprador;
            }

        }
        return mascomprador;
    }

    public List<Publicacion> getPublicacionesRecomendadas(int numeroPublicaciones) {

        if (ordenes.isEmpty()) {
            return Collections.emptyList();
        }

        final Map<Producto, Long> cantidadDeComprasProductos = new ConcurrentHashMap<>();
        final Map<Publicacion, Long> cantidadDeComprasPublicacion = new ConcurrentHashMap<>();
        final Map<Categoria, Long> cantidadDeComprasCategoria = new ConcurrentHashMap<>();
        final Map<Vendedor, Long> cantidadDeComprasVendedor = new ConcurrentHashMap<>();

        List<Publicacion> publicacionesAdquiridas = ordenes.parallelStream()
                .flatMap(orden -> orden.productosTransaccion
                        .stream()
                        .map(ProductoTransaccion::getPublicacion)
                        .toList().stream())
                .toList();

        publicacionesAdquiridas.parallelStream().forEach(publicacion -> {
            Producto producto = publicacion.getProducto();
            Categoria categoria = producto.getCategoria();
            Vendedor vendedor = publicacion.getVendedor();

            if (!cantidadDeComprasVendedor.containsKey(vendedor)) {
                cantidadDeComprasVendedor.put(vendedor, 0L);
            }
            if (!cantidadDeComprasCategoria.containsKey(categoria)) {
                cantidadDeComprasCategoria.put(categoria, 0L);
            }
            if (!cantidadDeComprasPublicacion.containsKey(publicacion)) {
                cantidadDeComprasPublicacion.put(publicacion, 0L);
            }
            if (!cantidadDeComprasProductos.containsKey(producto)) {
                cantidadDeComprasProductos.put(producto, 0L);
            }

            cantidadDeComprasVendedor.merge(vendedor, 1L, Long::sum);
            cantidadDeComprasCategoria.merge(categoria, 1L, Long::sum);
            cantidadDeComprasPublicacion.merge(publicacion, 1L, Long::sum);
            cantidadDeComprasProductos.merge(producto, 1L, Long::sum);
        });

        final Orden ultimaOrden = ordenes.get(ordenes.size() - 1);

        final List<Producto> productosMasComprados = cantidadDeComprasProductos.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(3).map(Map.Entry::getKey).toList();

        final List<Publicacion> publicacionesMasCompradas = cantidadDeComprasPublicacion.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(3).map(Map.Entry::getKey).toList();

        final List<Vendedor> vendedoresMasComprados = cantidadDeComprasVendedor.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(3).map(Map.Entry::getKey).toList();

        final List<Categoria> categoriasMasCompradas = cantidadDeComprasCategoria.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(3).map(Map.Entry::getKey).toList();


        Map<Publicacion, Integer> puntuacionPublicaciones = new ConcurrentHashMap<>();

        ProductoRepositorio.getProductos().parallelStream().forEach(producto -> {
            int puntuacionProducto = 0;
            Map<Publicacion, Integer> tempPublicaciones = new ConcurrentHashMap<>();
            producto.getPublicaciones().forEach(publicacion -> {
                int puntuacionPublicacion = 0;
                if (vendedoresMasComprados.contains(publicacion.getVendedor())) {
                    puntuacionPublicacion += 5;
                }
                if (publicacionesMasCompradas.contains(publicacion)) {
                    puntuacionPublicacion += 10;
                }
                tempPublicaciones.put(publicacion, puntuacionPublicacion);
            });

            if (categoriasMasCompradas.contains(producto.getCategoria())) {
                puntuacionProducto += 5;
            }

            if (productosMasComprados.contains(producto)) {
                puntuacionProducto += 10;
            }

            Optional<Map.Entry<Publicacion, Integer>> publicacionRecomendada = tempPublicaciones.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .limit(1)
                    .findFirst();

            if (publicacionRecomendada.isPresent()) {
                puntuacionPublicaciones.put(publicacionRecomendada.get().getKey(), puntuacionProducto + publicacionRecomendada.get().getValue());
            }
        });

        return puntuacionPublicaciones.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(numeroPublicaciones)
                .map(Map.Entry::getKey)
                .toList();
    }

}
