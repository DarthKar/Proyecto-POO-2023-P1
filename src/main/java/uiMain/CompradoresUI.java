package uiMain;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Devolucion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import uiMain.utilidades.Validaciones;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CompradoresUI extends Validaciones {

    static Comprador compradorActual;
    private static Scanner scanner;

    protected static void IU(Scanner scanner) {
        CompradoresUI.scanner = scanner;
        compradorActual = null;

        System.out.println("Bienvenido al módulo de comprador 😎");
        do {
            compradorActual = buscarComprador();
        } while (compradorActual == null);

        System.out.println("Bienvenido %s %s".formatted(compradorActual.getNombre(), compradorActual.getApellido()));


        menuBuscarComprador:
        do {
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            try {
                switch (opcion) {
                    case "1":
                        comprar();
                        continue;
                    case "2":
                        realizarDevolucion();
                        continue;
                    case "3":
                        listarOrdenes(compradorActual.getOrdenes());
                        continue;
                    case "4":
                        break menuBuscarComprador;
                    default:
                        System.out.println("Has elegido una opción invalida. Regresando al menú");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }

    private static void listarOrdenes(List<Orden> ordenes) {

        if (ordenes.isEmpty()) {
            throw new IllegalArgumentException("No tiene ninguna compra realizada");
        }
        AtomicInteger i = new AtomicInteger(0);
        ordenes.forEach(orden -> {
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow("Número Orden", "Número de productos");
            at.addRow(i.incrementAndGet(), orden.getProductosTransaccion().size());
            at.addRule();
            at.setTextAlignment(TextAlignment.CENTER);
            System.out.println(at.render());
        });
    }

    private static void realizarDevolucion() {
        Devolucion devolucion = new Devolucion(compradorActual);
        System.out.println(getOpcionesDevolucion());
        elegirProducto(devolucion);
    }

    private static void elegirProducto(Devolucion devolucion) {
        menuRealizarDevolucionOrden:
        do {
            Orden orden = listarOrdenesEleccion(compradorActual.getOrdenes());
            do {
                ProductoTransaccion productoTransaccionOrden = listarProductosEleccion(orden.obtenerProductosPorCantidad(1));
                int cantidadADevolver = menuCantidadADevolver(productoTransaccionOrden);
                devolucion.agregarProducto(new ProductoTransaccion(productoTransaccionOrden.getPublicacion(), cantidadADevolver));
                System.out.printf("Para continuar agregando más productos de la orden %s escribe 'sí'.%n", orden.getId());
                System.out.println("Si regresa, no podrá devolver más productos de esta orden.");
                String input = scanner.nextLine();
                if (!Objects.equals(input, "sí") && !Objects.equals(input, "si"))
                    break;
            } while (true);
            System.out.printf("Para elegir una nueva orden escribe sí.%n", orden.getId());
            String input = scanner.nextLine();
            if (!Objects.equals(input, "sí") && !Objects.equals(input, "si"))
                break;
        } while (true);
    }

    private static String getOpcionesDevolucion() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Elegir productos a devolver\n"
                + "2. Deshacer productos a devolver\n"
                + "4. Listar productos a devolver"
                + "3. Finalizar";
    }

    private static int menuCantidadADevolver(ProductoTransaccion productoTransaccion) {
        int cantidad = 1;
        if (productoTransaccion.getCantidad() > 1) {
            do {
                System.out.printf("Escriba la cantidad que desea devolver del producto. Disponible %s%n", productoTransaccion.getCantidad());
                cantidad = Integer.parseInt(scanner.nextLine());
                if (productoTransaccion.getCantidad() - cantidad < 0) {
                    System.out.printf("No se puede devolver la cantidad de %s para el producto %s%n",
                            cantidad, productoTransaccion.getPublicacion().getProducto().getNombre());
                    continue;
                }
                return cantidad;
            } while (true);
        }
        return cantidad;
    }

    private static Orden listarOrdenesEleccion(List<Orden> ordenes) {
        listarOrdenes(ordenes);
        System.out.println("Selecciona el número de orden");
        //TODO: Agregar validación número
        int orden = Integer.parseInt(scanner.nextLine()) - 1;
        return compradorActual.getOrdenes().get(orden);
    }

    private static void listarProductos(List<ProductoTransaccion> productosTransaccion) {
        AsciiTable atProducto = new AsciiTable();
        atProducto.addRule();
        atProducto.addRow("Identificador", "Vendedor", "Nombre producto", "Cantidad comprada", "Costo por unidad");

        AtomicInteger i = new AtomicInteger();
        productosTransaccion.forEach(productoTransaccion -> {
            atProducto.addRule();
            Vendedor vendedor = productoTransaccion.getPublicacion().getVendedor();
            Producto producto = productoTransaccion.getPublicacion().getProducto();
            atProducto.addRow(i.getAndIncrement(), vendedor.getNombreCompleto(), producto.getNombre(), productoTransaccion.getCantidad(),
                    productoTransaccion.getPublicacion().getPrecio());
        });
        atProducto.addRule();
        System.out.println(atProducto.render());
    }

    private static ProductoTransaccion listarProductosEleccion(List<ProductoTransaccion> productosTransaccion) {
        listarProductos(productosTransaccion);
        System.out.println("Selecciona el número de producto");
        //TODO: Agregar validación número
        int orden = Integer.parseInt(scanner.nextLine()) - 1;
        return productosTransaccion.get(orden);
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Comprar\n"
                + "2. Realizar devolución\n"
                + "3. Listar Ordenes\n"
                + "4. Regresar\n";
    }


    private static void comprar() {
    }

    private static Comprador buscarComprador() {
        System.out.println("Ingresa id de comprador");
        String id = scanner.nextLine();
        try {
            validarId(id);
            return Comprador.obtenerCompradorPorId(Long.parseLong(id));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
