package baseDatos.impl;

import gestorAplicacion.entidad.producto.Categoria;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Membresia;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import org.apache.commons.lang3.RandomUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BaseDatos implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Vendedor> vendedores;
    private List<Comprador> compradores;
    private List<Producto> productos;

    public BaseDatos() {
        vendedores = new ArrayList<>();
        compradores = new ArrayList<>();
        productos = new ArrayList<>();
        valoresPorDefecto();
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Comprador> getCompradores() {
        return compradores;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    private void valoresPorDefecto() {

            productos.add(new Producto(1L, "Arroz", Categoria.ALIMENTOS));
            productos.add(new Producto(2L, "Televisor", Categoria.ELECTRONICA));
            productos.add(new Producto(3L, "Camiseta", Categoria.ROPA));
            productos.add(new Producto(4L, "Sofá", Categoria.HOGAR));
            productos.add(new Producto(5L, "Muñeca", Categoria.JUGUETES));
            productos.add(new Producto(6L, "Crema facial", Categoria.COSMETICOS));
            productos.add(new Producto(7L, "Libro", Categoria.OTROS));
            productos.add(new Producto(8L, "Laptop", Categoria.ELECTRONICA));
            productos.add(new Producto(9L, "Cepillo de dientes", Categoria.HOGAR));
            productos.add(new Producto(10L, "Manzanas", Categoria.ALIMENTOS));
            productos.add(new Producto(11L, "Lámpara", Categoria.HOGAR));
            productos.add(new Producto(12L, "Pantalones", Categoria.ROPA));
            productos.add(new Producto(13L, "Chocolate", Categoria.ALIMENTOS));
            productos.add(new Producto(14L, "Teléfono móvil", Categoria.ELECTRONICA));
            productos.add(new Producto(15L, "Pelota de fútbol", Categoria.JUGUETES));
            productos.add(new Producto(16L, "Champú", Categoria.COSMETICOS));
            productos.add(new Producto(17L, "Taza", Categoria.HOGAR));
            productos.add(new Producto(18L, "Reloj", Categoria.ELECTRONICA));
            productos.add(new Producto(19L, "Camiseta polo", Categoria.ROPA));
            productos.add(new Producto(20L, "Leche", Categoria.ALIMENTOS));
            productos.add(new Producto(21L, "Consola de videojuegos", Categoria.ELECTRONICA));
            productos.add(new Producto(22L, "Toallas", Categoria.HOGAR));
            productos.add(new Producto(23L, "Maquillaje", Categoria.COSMETICOS));
            productos.add(new Producto(24L, "Peluche", Categoria.JUGUETES));
            productos.add(new Producto(25L, "Libreta", Categoria.OTROS));
            productos.add(new Producto(26L, "Silla", Categoria.HOGAR));
            productos.add(new Producto(27L, "Gafas de sol", Categoria.OTROS));
            productos.add(new Producto(28L, "Pasta de dientes", Categoria.HOGAR));
            productos.add(new Producto(29L, "Café", Categoria.ALIMENTOS));
            productos.add(new Producto(30L, "Perfume", Categoria.COSMETICOS));
            productos.add(new Producto(31L, "Arroz", Categoria.ALIMENTOS));
            productos.add(new Producto(32L, "Camiseta", Categoria.ROPA));
            productos.add(new Producto(33L, "Teléfono", Categoria.ELECTRONICA));
            productos.add(new Producto(34L, "Leche", Categoria.ALIMENTOS));
            productos.add(new Producto(35L, "Portátil", Categoria.ELECTRONICA));

            compradores.add(new Comprador(1L, "Pedro", "Moreno", "pedro@example.com", Membresia.ORO,234));
            compradores.add(new Comprador(2L, "Ana", "López", "ana@example.com", Membresia.PLATA,456));
            compradores.add(new Comprador(3L, "Juan", "Gómez", "juan@example.com", Membresia.BASICA,123));
            compradores.add(new Comprador(4L, "María", "Rodríguez", "maria@example.com", Membresia.BRONCE,67));
            compradores.add(new Comprador(5L, "Sofía", "Martínez", "sofia@example.com", Membresia.NINGUNA,87));
            compradores.add(new Comprador(6L, "Carlos", "Pérez", "carlos@example.com", Membresia.ORO,89));
            compradores.add(new Comprador(7L, "Laura", "Fernández", "laura@example.com", Membresia.PLATA,122));
            compradores.add(new Comprador(8L, "Diego", "Hernández", "diego@example.com", Membresia.BASICA,111));
            compradores.add(new Comprador(9L, "Isabel", "Díaz", "isabel@example.com", Membresia.BRONCE,102));
            compradores.add(new Comprador(10L, "Luis", "García", "luis@example.com", Membresia.NINGUNA,334));
            compradores.add(new Comprador(11L, "Alejandro", "González", "alejandro@example.com", Membresia.ORO,34));
            compradores.add(new Comprador(12L, "Elena", "Torres", "elena@example.com", Membresia.PLATA,12));
            compradores.add(new Comprador(13L, "Miguel", "Ruiz", "miguel@example.com", Membresia.BASICA,34));
            compradores.add(new Comprador(14L, "Carmen", "Sánchez", "carmen@example.com", Membresia.BRONCE,345));
            compradores.add(new Comprador(15L, "Andrea", "Lara", "andrea@example.com", Membresia.NINGUNA,234));
            compradores.add(new Comprador(16L, "Ricardo", "Vargas", "ricardo@example.com", Membresia.ORO,123));
            compradores.add(new Comprador(17L, "Natalia", "Pérez", "natalia@example.com", Membresia.PLATA,103));
            compradores.add(new Comprador(18L, "Daniel", "Gómez", "daniel@example.com", Membresia.BASICA,167));
            compradores.add(new Comprador(19L, "Lucía", "Hernández", "lucia@example.com", Membresia.BRONCE,178));
            compradores.add(new Comprador(20L, "Javier", "Soto", "javier@example.com", Membresia.NINGUNA,78));
            compradores.add(new Comprador(21L, "Natalia", "López", "natalia@example.com", Membresia.PLATA,789));
            compradores.add(new Comprador(22L, "Pablo", "Martínez", "pablo@example.com", Membresia.BASICA,56));
            compradores.add(new Comprador(23L, "Valeria", "Gutiérrez", "valeria@example.com", Membresia.BRONCE,90));
            compradores.add(new Comprador(24L, "Sara", "Jiménez", "sara@example.com", Membresia.NINGUNA,89));
            compradores.add(new Comprador(25L, "Juan", "Torres", "juan@example.com", Membresia.ORO,123));
            compradores.add(new Comprador(26L, "María", "Gómez", "maria@example.com", Membresia.PLATA,78));
            compradores.add(new Comprador(27L, "Lucas", "Díaz", "lucas@example.com", Membresia.BASICA,86));
            compradores.add(new Comprador(28L, "Valentina", "Fernández", "valentina@example.com", Membresia.BRONCE,126));
            compradores.add(new Comprador(29L, "Gabriel", "Pérez", "gabriel@example.com", Membresia.NINGUNA,67));
            compradores.add(new Comprador(30L, "Sofía", "Moreno", "sofia@example.com", Membresia.ORO,456));
            compradores.add(new Comprador(31L, "Pedro", "Moreno", "pedro@example.com", Membresia.ORO,678));
            compradores.add(new Comprador(32L, "Ana", "González", "ana@example.com", Membresia.PLATA,90));
            compradores.add(new Comprador(33L, "Juan", "López", "juan@example.com", Membresia.BRONCE,145));
            compradores.add(new Comprador(34L, "María", "Sánchez", "maria@example.com", Membresia.ORO,167));
            compradores.add(new Comprador(35L, "Luis", "Martínez", "luis@example.com", Membresia.PLATA,189));
            compradores.add(new Comprador(36L, "Aristobulo", "Cachimbo", "aristi@example.com",Membresia.ORO,78));

            vendedores.add(new Vendedor(1L, "Juan", "Pérez", "juan@example.com"));
            vendedores.add(new Vendedor(2L, "María", "López", "maria@example.com"));
            vendedores.add(new Vendedor(3L, "Carlos", "Gómez", "carlos@example.com"));
            vendedores.add(new Vendedor(4L, "Ana", "Martínez", "ana@example.com"));
            vendedores.add(new Vendedor(5L, "Laura", "Sánchez", "laura@example.com"));
            vendedores.add(new Vendedor(6L, "Pedro", "Fernández", "pedro@example.com"));
            vendedores.add(new Vendedor(7L, "Sofía", "Hernández", "sofia@example.com"));
            vendedores.add(new Vendedor(8L, "Diego", "Díaz", "diego@example.com"));
            vendedores.add(new Vendedor(9L, "Isabel", "Gutiérrez", "isabel@example.com"));
            vendedores.add(new Vendedor(10L, "Luis", "Torres", "luis@example.com"));
            vendedores.add(new Vendedor(11L, "Elena", "Ruiz", "elena@example.com"));
            vendedores.add(new Vendedor(12L, "Miguel", "Soto", "miguel@example.com"));
            vendedores.add(new Vendedor(13L, "Carmen", "Lara", "carmen@example.com"));
            vendedores.add(new Vendedor(14L, "Andrea", "Jiménez", "andrea@example.com"));
            vendedores.add(new Vendedor(15L, "Ricardo", "Vargas", "ricardo@example.com"));
            vendedores.add(new Vendedor(16L, "Natalia", "Gómez", "natalia@example.com"));
            vendedores.add(new Vendedor(17L, "Daniel", "Pérez", "daniel@example.com"));
            vendedores.add(new Vendedor(18L, "Lucía", "Moreno", "lucia@example.com"));
            vendedores.add(new Vendedor(19L, "Gabriel", "Martínez", "gabriel@example.com"));
            vendedores.add(new Vendedor(20L, "Valentina", "Sánchez", "valentina@example.com"));
            vendedores.add(new Vendedor(21L, "Alejandro", "Gómez", "alejandro@example.com"));
            vendedores.add(new Vendedor(22L, "Pablo", "López", "pablo@example.com"));
            vendedores.add(new Vendedor(23L, "Valeria", "Torres", "valeria@example.com"));
            vendedores.add(new Vendedor(24L, "Sara", "Hernández", "sara@example.com"));
            vendedores.add(new Vendedor(25L, "Juan", "Díaz", "juan@example.com"));
            vendedores.add(new Vendedor(26L, "María", "Gutiérrez", "maria@example.com"));
            vendedores.add(new Vendedor(27L, "Lucas", "Fernández", "lucas@example.com"));
            vendedores.add(new Vendedor(28L, "Natalia", "Pérez", "natalia@example.com"));
            vendedores.add(new Vendedor(29L, "Sofía", "Moreno", "sofia@example.com"));
            vendedores.add(new Vendedor(30L, "Javier", "Soto", "javier@example.com"));
            vendedores.add(new Vendedor(31L, "Juan", "Pérez", "juan@example.com"));
            vendedores.add(new Vendedor(32L, "María", "García", "maria@example.com"));
            vendedores.add(new Vendedor(33L, "Carlos", "López", "carlos@example.com"));
            vendedores.add(new Vendedor(34L, "Ana", "Martínez", "ana@example.com"));
            vendedores.add(new Vendedor(35L, "Pedro", "Sánchez", "pedro@example.com"));


        vendedores.forEach(vendedor -> {
            int cantidadPublicaciones = RandomUtils.nextInt(5, 11);
            IntStream.range(1, cantidadPublicaciones).forEach(i -> {
                do {
                    try {
                        int productoAleatorio = RandomUtils.nextInt(0, productos.size());
                        int inventarioAleatorio = RandomUtils.nextInt(1, 99);
                        float precioAleatorio = RandomUtils.nextFloat(10, 100);
                        vendedor.crearPublicacion(productos.get(productoAleatorio), inventarioAleatorio, precioAleatorio);
                        break;
                    } catch (IllegalArgumentException ignored) {

                    }
                } while (true);
            });
        });

        compradores.forEach(comprador -> {
            int cantidadOrdenes = RandomUtils.nextInt(1,9);
            IntStream.range(0,cantidadOrdenes).forEach(i -> {
                Orden orden = new Orden((long) i,comprador);
                int cantidadArticulos = RandomUtils.nextInt(1, 4);
                IntStream.range(0, cantidadArticulos).forEach(j -> {

                    int productoAleatorio = RandomUtils.nextInt(0, productos.size());

                    Producto producto = productos.get(productoAleatorio);

                    int publicacionAleatoria = RandomUtils.nextInt(0, producto.getPublicaciones().size());
                    Publicacion publicacion = producto.getPublicaciones().get(publicacionAleatoria);

                    int cantidadAleatoria = RandomUtils.nextInt(1, 10);

                    ProductoTransaccion productoTransaccion = new ProductoTransaccion(publicacion, cantidadAleatoria);

                    orden.agregarProducto(productoTransaccion);

                });
                comprador.agregarOrden(orden);
            });
        });
    }

}
