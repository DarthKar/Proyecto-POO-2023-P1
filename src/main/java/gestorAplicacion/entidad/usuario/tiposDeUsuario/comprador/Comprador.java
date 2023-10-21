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
    private List<Opinion> resenasDeTiendas; //Se guardaran Rese�as De Productos
    private float saldo; //atributo para poder justificar el metodo de pago en la funcionalidad 1

    public Comprador(long id, String nombre, String apellido, String correo, Membresia membresia,float saldo) {
        super(id, nombre, apellido, correo);
        this.membresia = membresia;
        this.saldo = saldo;
        ordenes = new ArrayList<>();
        devoluciones = new ArrayList<>();
        carrito = new Carrito(id, this);
        resenasDeProductos = new ArrayList<>(); // Se crean Ambas listas Para Rese�as hechas a tiendas y productos
        resenasDeTiendas = new ArrayList<>();
        productosComprados = new ArrayList<>();

    }
    
    public List<ProductoTransaccion> getProductosComprados() {
        return productosComprados;                                                          //obtener la lista de productos adquiridos
    }
    
    public void agregarProductoComprado(ProductoTransaccion productocomprado){    //agregar productos comprados
        productosComprados.add(productocomprado);
    }
    
    public void setProductosComprados(List<ProductoTransaccion> listacomprados){      //settear una lista de productos comprados predeterminada en caso de ser necesario
        this.productosComprados = listacomprados;
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
            a = totalpuro*0.9f;                                                       //metodo para aplicar los descuentos al total de una orden al ejecutar el pago
        }
        if(this.membresia ==  Membresia.BRONCE){
            a = totalpuro*0.8f;
        }
        if(this.membresia ==  Membresia.PLATA){
            a = totalpuro*0.7f;
        }
        if(this.membresia ==  Membresia.ORO){
            a = totalpuro*0.5f;
        }
        return a;
    }
    
    public String mostrarSaldo(){
        return "Su saldo actual es: "+this.saldo;                   //metodo toString() para mostrar el saldo del comprador
    }
   
    public String mostrarInformacion(){
        return "Nombre: "+this.getNombre()+" "+this.apellido+" \nCorreo: "+this.correo+" \nTipo de Membresia: "+this.membresia+" \nSaldo: "+this.saldo;
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

    public static String masComprador() {
        int tamanoOrdenes = 0;
        Comprador mascomprador = null;
        for (Comprador comprador : CompradorRepositorio.obtener()) {
            if (comprador.getOrdenes().size() > tamanoOrdenes) {
                tamanoOrdenes = comprador.getOrdenes().size();
                mascomprador = comprador;
            }

        }
        return (mascomprador.getNombre()+" "+ mascomprador.getApellido()+" con el ID "+mascomprador.getId()+" y con el correo electronico "+mascomprador.getCorreo());
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
        int Ninguna = 0;
        int Oro = 0;
        int Plata = 0;
        int Bronce = 0;
        int Basica = 0;

        for (Comprador comprador : CompradorRepositorio.obtener()) {
            Membresia comparar = comprador.getMembresia();
            switch (comparar) {
                case NINGUNA:
                    Ninguna++;
                    break;
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
        Membresia membresiaMasComun = Membresia.NINGUNA;
        int maxCompras = Ninguna;

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

