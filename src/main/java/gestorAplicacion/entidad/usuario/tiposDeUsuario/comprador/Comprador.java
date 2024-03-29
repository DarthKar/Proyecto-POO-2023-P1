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
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Membresia;
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
    private List<ProductoTransaccion> productosComprados;//Para guardar los productos que ha comprado este comprador
    private List<Opinion> resenasDeProductos; //Se guardaran aqui las rese�as a productos
    private float saldo; //atributo para poder justificar el metodo de pago en la funcionalidad 1

    public Comprador(long id, String nombre, String apellido, String correo, Membresia membresia,float saldo) {
        super(id, nombre, apellido, correo);
        this.membresia = membresia;
        this.saldo = saldo;
        ordenes = new ArrayList<>();
        devoluciones = new ArrayList<>();
        carrito = new Carrito(id, this);
        resenasDeProductos = new ArrayList<>(); // Se crean Ambas listas Para Rese�as hechas a tiendas y productos
        productosComprados = new ArrayList<>();
    }

    public Comprador(long id,String nombre,String apellido,String correo){
        this(id,nombre, apellido, correo,Membresia.NINGUNA,100);
    }

    public void agregarProductoComprado(ProductoTransaccion productocomprado){    //agregar productos comprados
        productosComprados.add(productocomprado);
    }

    public List<Opinion>  getResenasDeProductos() {
        return resenasDeProductos;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void agregarDevolucion(Devolucion devolucion) {
        if (!devolucion.getProductosTransaccion().isEmpty()) {
            saldo += AplicarDescuento(devolucion.calcularTotal());
            devoluciones.add(devolucion);
            CompradorRepositorio.guardar(this);
        }
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public float getSaldo(){                  //metodo para obtener el saldo de un comprador
        return this.saldo;
    }
    
    public void quitarSaldo(float quitar){
        this.saldo  = this.saldo-quitar;
    }
    
    public void agregarSaldo(float dinero){              //metodo para agregar saldo a un comprador
        this.saldo = this.saldo+dinero;
    }
    
    public float AplicarDescuento(float totalpuro){
        float a = 0;
        if (this.membresia ==  Membresia.NINGUNA){
            a  = totalpuro;
        }
        if(this.membresia ==  Membresia.BASICA){
            a = totalpuro*Membresia.PRECIO_BASICA;                                                       //metodo para aplicar los descuentos al total de una orden al ejecutar el pago
        }
        if(this.membresia ==  Membresia.BRONCE){
            a = totalpuro*Membresia.PRECIO_BRONCE;
        }
        if(this.membresia ==  Membresia.PLATA){
            a = totalpuro*Membresia.PRECIO_PLATA;
        }
        if(this.membresia ==  Membresia.ORO){
            a = totalpuro*Membresia.PRECIO_ORO;
        }
        return a;
    }
   
    public String mostrarInformacion(){
        return "Nombre: "+this.getNombre()+" "+this.apellido+" \nCorreo: "+this.correo+" \nTipo de Membresia: "+this.membresia+" \nSaldo: "+this.saldo;
    }

    public static Comprador obtenerCompradorPorId(long id) {
        return CompradorRepositorio.obtenerPorId(id).orElseThrow(() ->
                new IllegalArgumentException("Comprador con id %s no ha sido encontrado.".formatted(id)));
    }

    public void agregarOrden(Orden orden) {
        ordenes.add(orden);
    }

    public List<Orden> getOrdenesValidasParaDevolucion() {
        return ordenes.stream().filter(orden ->
                        !orden.isTieneDevoluciones() && orden.isPagar()
                                && orden.getProductosTransaccion()
                                .stream()
                                .anyMatch(productoTransaccion -> !productoTransaccion.getPublicacion().getProducto().getCategoria().isPerecedero()))
                .toList();
    }

    public static String masComprador() {
        int tamanoOrdenes = 0;
        Comprador mascomprador = null;
        for (Comprador comprador : CompradorRepositorio.obtener()) {
            if (comprador.getOrdenes().size() > tamanoOrdenes) {
                tamanoOrdenes = comprador.getOrdenes().size();
                mascomprador = comprador;
            }

        }
        return (mascomprador.getNombre()+" "+ mascomprador.getApellido()+" con el ID "+mascomprador.getId()+" y con el correo electronico "+mascomprador.getCorreo()+" con un total de "+tamanoOrdenes+" productos comprados");
    }
    
    public static String masCompradorValor() {
        float comprasValorMaximo = 0;
        Comprador masComprador = null;

        for (Comprador comprador : CompradorRepositorio.obtener()) {
            float comprasValor = 0;

            for (Orden orden : comprador.getOrdenes()) {
                for (ProductoTransaccion productoTransaccion : orden.getProductosTransaccion()) {
                    comprasValor += productoTransaccion.getPublicacion().getPrecio();
                }
            }

            if (comprasValor > comprasValorMaximo) {
                comprasValorMaximo = comprasValor;
                masComprador = comprador;
            }
        }

        if (masComprador != null) {
            return masComprador.getNombre() +" "+ masComprador.getApellido()+ " que gastó un total de " + comprasValorMaximo;
        } else {
            return "No se encontró ningún comprador.";
        }
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
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3).map(Map.Entry::getKey).toList();

        final List<Publicacion> publicacionesMasCompradas = cantidadDeComprasPublicacion.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3).map(Map.Entry::getKey).toList();

        final List<Vendedor> vendedoresMasComprados = cantidadDeComprasVendedor.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3).map(Map.Entry::getKey).toList();

        final List<Categoria> categoriasMasCompradas = cantidadDeComprasCategoria.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
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

                if(ultimaOrden.productosTransaccion.stream()
                        .anyMatch(productoTransaccion -> productoTransaccion.getPublicacion().equals(publicacion))){
                    puntuacionPublicacion+=4;
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
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(1)
                    .findFirst();

            if (publicacionRecomendada.isPresent()) {
                puntuacionPublicaciones.put(publicacionRecomendada.get().getKey(), puntuacionProducto + publicacionRecomendada.get().getValue());
            }
        });

        return puntuacionPublicaciones.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numeroPublicaciones)
                .map(Map.Entry::getKey)
                .toList();
    }
    
    public static Membresia MembresiaMasComprada() {
        int Oro = 0;
        int Plata = 0;
        int Bronce = 0;
        int Basica = 0;

        for (Comprador comprador : CompradorRepositorio.obtener()) {
            Membresia comparar = comprador.getMembresia();
            switch (comparar) {
                case BASICA:
                    Basica++;
                    break;
                case BRONCE:
                    Bronce++;
                    break;
                case PLATA:
                    Plata++;
                    break;
                case ORO:
                    Oro++;
                    break;
            }
        }

        // Encuentra la membresía más común
        Membresia membresiaMasComun = null;
        int maxCompras = 0;

        if (Basica > maxCompras) {
            membresiaMasComun = Membresia.BASICA;
            maxCompras = Basica;
        }
        if (Bronce > maxCompras) {
            membresiaMasComun = Membresia.BRONCE;
            maxCompras = Bronce;
        }
        if (Plata > maxCompras) {
            membresiaMasComun = Membresia.PLATA;
            maxCompras = Plata;
        }
        if (Oro > maxCompras) {
            membresiaMasComun = Membresia.ORO;
        }

        return membresiaMasComun;
    }
}

