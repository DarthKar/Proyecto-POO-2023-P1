package gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador;

import baseDatos.impl.CompradorRepositorio;
import baseDatos.impl.UsuarioRepositorio;
import gestorAplicacion.entidad.Opinion; // Import para usar Ambas listas
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Carrito;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Devolucion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Membresia;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Comprador extends Usuario {
    private Membresia membresia;
    private List<Orden> ordenes;
    private List<Devolucion> devoluciones;
    private Carrito carrito;
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
        if(!devolucion.getProductosTransaccion().isEmpty()){
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
    public static Comprador masComprador() {
    	int tamanoOrdenes=0;
    	Comprador mascomprador=null;
    	for(Comprador comprador: CompradorRepositorio.obtener()) {
    		if (comprador.getOrdenes().size()>tamanoOrdenes) {
    			tamanoOrdenes=comprador.getOrdenes().size();
    			mascomprador=comprador;
    		}
    		
    	}
    return mascomprador;
    }
    
}
