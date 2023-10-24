package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.opinion.OpinionVendedor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import baseDatos.impl.CompradorRepositorio;



public class Vendedor extends Usuario {

	private List<Publicacion> publicaciones;
	private List<OpinionVendedor> opinionesVendedor;
	private List<Comprador> compradores;
	private List<Comprador> resenadores;

	public Vendedor(long id, String nombre, String apellido, String correo) {
		super(id, nombre, apellido, correo);
		this.publicaciones = new ArrayList<>();
		this.opinionesVendedor = new ArrayList<>();
		this.compradores = new ArrayList<>(); // Nuevo array para verificar si el comprador que va a crear una opinion
		this.resenadores = new ArrayList<>();										// si ha comprado en esa tienda

	}

	public Vendedor(long id,String nombre, String apellido){
        this(id,nombre,apellido,nombre+"@example.com");
    }

	public List<Publicacion> getProductoVendedor() {
		return publicaciones;
	}

	protected void agregarPublicacion(Publicacion publicacion) {
		if (Objects.isNull(publicacion))
			return;
		publicaciones.add(publicacion);
	}

	public List<OpinionVendedor> getOpinion() {
		return opinionesVendedor;
	}

	public void addOpinionVendedor(OpinionVendedor opinionVendedor) {
		if (Objects.isNull(opinionVendedor))
			return;
		opinionesVendedor.add(opinionVendedor);
	}

	public boolean existeResena(Comprador comprador) {
		return resenadores.contains(comprador); // Creacion del metodo ExisteResena que comprueba si ya hay una rese�a

	}

	public List<Comprador> getCompradores() {
		return compradores; // Getter y setter para esas listas
	}

	public List<Publicacion> getPublicaciones() { // Getter para la lista de publicaciones del respectivo vendedor
		return this.publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public void setOpinionesVendedor(List<OpinionVendedor> opinionesVendedor) {
		this.opinionesVendedor = opinionesVendedor;
	}

	public void setCompradores(List<Comprador> compradores) {
		this.compradores = compradores;
	}

	public void setResenadores(List<Comprador> resenadores) {
		this.resenadores = resenadores;
	}

	public List<Comprador> getResenadores() {
		return resenadores;
	}

	public void agregarComprador(Comprador comprador) {
		this.compradores.add(comprador);
	}
	
	public void agregarResenador(Comprador comprador){
		this.resenadores.add(comprador);
	}

	public void crearPublicacion(Producto producto, int inventario, float precio) {
		Optional<Publicacion> publicacionOptional = publicaciones.stream()
				.filter(publicacion -> publicacion.getProducto().equals(producto)).findAny();
		if (publicacionOptional.isPresent()) {
			throw new IllegalArgumentException(
					"Ya existe una publicación creada con el producto %s".formatted(producto.getNombre()));
		}
		new Publicacion(this, producto, inventario, precio);
	}

	public static String mejorVendedor() {
	    Map<Vendedor, Integer> cantidadProductosPorVendedor = new HashMap<>();
	    
	    for (Comprador comprador : CompradorRepositorio.obtener()) {
	        for (Transaccion transaccion : comprador.getOrdenes()) {
	            for (ProductoTransaccion productoTransaccion : transaccion.getProductosTransaccion()) {
	                Vendedor vendedor = productoTransaccion.getPublicacion().getVendedor();
	                cantidadProductosPorVendedor.merge(vendedor, 1, Integer::sum);
	            }
	        }
	    }
	    
	    Vendedor mejorVendedor = null;
	    int maxCantidadProductos = 0;
	    
	    for (Map.Entry<Vendedor, Integer> entry : cantidadProductosPorVendedor.entrySet()) {
	        if (entry.getValue() > maxCantidadProductos) {
	            maxCantidadProductos = entry.getValue();
	            mejorVendedor = entry.getKey();
	        }
	    }
	    
	    return (mejorVendedor.getNombre()+" "+ mejorVendedor.getApellido()+" con un total de "+maxCantidadProductos+" productos vendidos");
	}
     
	

public static String mejorVendedorPorRecaudacion() {
    Map<Vendedor, Float> recaudacionPorVendedor = new HashMap<>();
    
    for (Comprador comprador : CompradorRepositorio.obtener()) {
        for (Transaccion transaccion : comprador.getOrdenes()) {
            for (ProductoTransaccion productoTransaccion : transaccion.getProductosTransaccion()) {
                Vendedor vendedor = productoTransaccion.getPublicacion().getVendedor();
                float precioProducto = productoTransaccion.getPublicacion().getPrecio();
                recaudacionPorVendedor.merge(vendedor, precioProducto, Float::sum);
            }
        }
    }
    
    Vendedor mejorVendedor = null;
    float maxRecaudacion = 0;
    
    for (Map.Entry<Vendedor, Float> entry : recaudacionPorVendedor.entrySet()) {
        if (entry.getValue() > maxRecaudacion) {
            maxRecaudacion = entry.getValue();
            mejorVendedor = entry.getKey();
        }
    }
    
    if (mejorVendedor != null) {
        return mejorVendedor.getNombre() + " " + mejorVendedor.getApellido() + " ha recaudado un total de " + maxRecaudacion + " en ventas.";
    } else {
        return "No se encontró ningún mejor vendedor por recaudación.";
    }
}
}




