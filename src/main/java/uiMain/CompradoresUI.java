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

import java.util.*;
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
        } while (Objects.isNull(compradorActual));

        System.out.printf("Bienvenido %s %s%n", compradorActual.getNombre(), compradorActual.getApellido());

        menuBuscarComprador:
        do {
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();

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
                    System.out.println("Ha elegido una opción invalida.");
            }
        } while (true);
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
        try {
            Orden orden = listarOrdenesEleccion(compradorActual.getOrdenesValidasParaDevolucion());
            devolucion.setOrden(orden);
            menuDevolucion:
            do {
                System.out.println(getOpcionesDevolucion());
                switch (validarOpcionMenu(scanner.nextLine(), 0, 4)) {
                    case 1:
                        elegirProductoADevolver(devolucion);
                        break;
                    case 2:
                        modificarProductoADevolver(devolucion);
                        break;
                    case 3:
                        listarProductos(devolucion.getProductosTransaccion());
                        break;
                    case 4:
                        break menuDevolucion;
                }

            } while (true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void elegirProductoADevolver(Devolucion devolucion) {
        List<ProductoTransaccion> listaProductos = new ArrayList<>(devolucion.getOrden().obtenerProductosPorCantidad(1));
        do {
            Optional<ProductoTransaccion> productoTransaccion = elegirProductoTransaccion(listaProductos);
            if (productoTransaccion.isEmpty()) {
                System.out.println("Ha elegido un producto invalido - Presiona enter para continuar");
                scanner.nextLine();
                continue;
            }
            int cantidadADevolver = menuCantidadADevolver(productoTransaccion.get(), false);
            devolucion.agregarProducto(new ProductoTransaccion(productoTransaccion.get().getPublicacion(), cantidadADevolver));
            listaProductos.remove(productoTransaccion.get());

            if (listaProductos.isEmpty()) {
                System.out.println("Ya no es posible agregar más productos");
                break;
            }

            System.out.printf("Para continuar agregando más productos de la orden: %s escribe 'sí'.%n", devolucion.getOrden().getId());
            System.out.println("Si regresa, no podrá devolver más productos de esta orden.");
            String input = scanner.nextLine();
            if (!Objects.equals(input, "sí") && !Objects.equals(input, "si"))
                break;
        } while (true);
    }

    private static void modificarProductoADevolver(Devolucion devolucion) {
        do {
            Optional<ProductoTransaccion> productoTransaccion = elegirProductoTransaccion(devolucion.getProductosTransaccion());

            if (productoTransaccion.isEmpty()) {
                System.out.println("Ha elegido un producto invalido - Presiona enter para continuar");
                scanner.nextLine();
                continue;
            }

            int cantidadADevolver = menuCantidadADevolver(productoTransaccion.get(), true);

            if (cantidadADevolver == 0) {
                devolucion.removerProducto(productoTransaccion.get());
            } else {
                devolucion.modificarProducto(productoTransaccion.get(), cantidadADevolver);
            }

            if (devolucion.getProductosTransaccion().isEmpty()) {
                System.out.println("Ya no es posible agregar más productos");
                break;
            }

            System.out.printf("Para continuar modificando más productos de la de devolución: %s escribe 'sí.", devolucion.getOrden().getId());
            String input = scanner.nextLine();
            if (!Objects.equals(input, "sí") && !Objects.equals(input, "si"))
                break;

        } while (true);

    }

    private static int menuCantidadADevolver(ProductoTransaccion productoTransaccion, boolean esModificacion) {
        int min = esModificacion ? 0 : 1;
        System.out.printf("Escriba la cantidad que desea devolver del producto %s. Hay disponible %s%n",
                productoTransaccion.getPublicacion().getProducto().getNombre(),
                productoTransaccion.getCantidad());

        return validarCantidad(scanner.nextLine(), min, productoTransaccion.getCantidad());
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
        atProducto.addRow("Identificador", "Vendedor", "Nombre producto", "Cantidad", "Costo por unidad");

        AtomicInteger i = new AtomicInteger(1);
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

    private static Optional<ProductoTransaccion> elegirProductoTransaccion(List<ProductoTransaccion> productosTransaccion) {
        listarProductos(productosTransaccion);
        System.out.println("Selecciona el número de producto");
        int numeroOrden = Integer.parseInt(scanner.nextLine()) - 1;
        if (numeroOrden >= 0 && numeroOrden <= productosTransaccion.size()) {
            return Optional.of(productosTransaccion.get(numeroOrden));
        }
        return Optional.empty();
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Comprar\n"
                + "2. Realizar devolución\n"
                + "3. Listar Ordenes\n"
                + "4. Regresar\n";
    }

    private static String getOpcionesDevolucion() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Elegir productos a devolver\n"
                + "2. Deshacer productos a devolver\n"
                + "4. Listar productos a devolver"
                + "3. Finalizar";
    }

    private static void comprar() {
    }


}
