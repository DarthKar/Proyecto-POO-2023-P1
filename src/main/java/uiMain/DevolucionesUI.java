package uiMain;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Devolucion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import uiMain.utilidades.Validaciones;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DevolucionesUI extends Validaciones {

    static Comprador compradorActual;
    private static Scanner scanner;

    protected static void IU(Scanner scanner) {
        DevolucionesUI.scanner = scanner;
        compradorActual = null;

        System.out.println("Bienvenido al módulo de comprador 😎");
        do {
            compradorActual = buscarComprador();
        } while (Objects.isNull(compradorActual));

        System.out.printf("Bienvenido %s %s%n", compradorActual.getNombre(), compradorActual.getApellido());
        realizarDevolucion();
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
            at.addRow("Número Orden", "Id");
            at.addRow(i.incrementAndGet(), orden.getId());
            at.addRule();
            at.setTextAlignment(TextAlignment.CENTER);
            System.out.println(at.render());
        });
    }

    private static void realizarDevolucion() {
        Devolucion devolucion = new Devolucion(0L, compradorActual);
        try {
            Optional<Orden> ordenOptional = listarOrdenesEleccion(compradorActual.getOrdenesValidasParaDevolucion());
            if (ordenOptional.isEmpty()) {
                System.out.println("No ha elegido una opción valida");
                return;
            }
            
            devolucion.setOrden(ordenOptional.get());
            menuDevolucion:
            do {
                System.out.println(getOpcionesDevolucion(devolucion));
                switch (validarOpcionMenu(scanner.nextLine(), 0, 5)) {
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
                    case 5:
                        deshacerDevolucion(devolucion);
                        break menuDevolucion;
                    default:
                        System.out.println("Ha elegido una opción invalida");
                }
            } while (true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        compradorActual.agregarDevolucion(devolucion);
    }

    private static void deshacerDevolucion(Devolucion devolucion) {
        devolucion.getProductosTransaccion().forEach(devolucion::removerProducto);
    }

    private static void elegirProductoADevolver(Devolucion devolucion) {
        List<ProductoTransaccion> listaProductos = new ArrayList<>(devolucion.getProductosDevolucion());

        if (listaProductos.isEmpty()) {
            System.out.println("No ha seleccionado ningun producto para devolver - Presione enter para continuar");
            return;
        }

        do {
            Optional<ProductoTransaccion> productoTransaccion = elegirProductoTransaccion(listaProductos);
            if (productoTransaccion.isEmpty()) {
                System.out.println("Ha elegido un producto invalido - Presiona enter para continuar");
                scanner.nextLine();
                continue;
            }
            int cantidadADevolver = menuCantidadADevolver(productoTransaccion.get().getPublicacion().getProducto().getNombre(), 1, productoTransaccion.get().getCantidad());
            devolucion.agregarProducto(new ProductoTransaccion(productoTransaccion.get().getPublicacion(), cantidadADevolver));
            listaProductos.remove(productoTransaccion.get());

            if (listaProductos.isEmpty()) {
                System.out.println("No hay más productos elegibles que cumplan el criterio de devolución - Presiona enter para continuar");
                scanner.nextLine();
                break;
            }

            System.out.printf("Para continuar agregando más productos de la orden: %s escribe 'sí'.%n", devolucion.getOrden().getId());
            String input = scanner.nextLine();
            if (!Objects.equals(input, "sí") && !Objects.equals(input, "si"))
                break;
        } while (true);
    }

    private static void modificarProductoADevolver(Devolucion devolucion) {
        if (devolucion.getProductosTransaccion().isEmpty()) {
            System.out.println("No ha seleccionado ningun producto para devolver - Presione enter para continuar");
            return;
        }

        do {
            Optional<ProductoTransaccion> productoTransaccion = elegirProductoTransaccion(devolucion.getProductosTransaccion());

            if (productoTransaccion.isEmpty()) {
                System.out.println("Ha elegido un producto invalido - Presiona enter para continuar");
                scanner.nextLine();
                continue;
            }

            ProductoTransaccion productoOrden = devolucion.getOrdenProductoTransacction(productoTransaccion.get().getPublicacion());
            int cantidadADevolver = menuCantidadADevolver(productoOrden.getPublicacion().getProducto().getNombre(), 0, productoOrden.getCantidad());

            devolucion.modificarProducto(productoTransaccion.get(), cantidadADevolver);

            if (devolucion.getProductosTransaccion().isEmpty()) {
                System.out.println("Ya no es posible agregar más productos");
                break;
            }

            System.out.printf("Para continuar modificando más productos de la de devolución: %s escribe 'sí.%n", devolucion.getOrden().getId());
            String input = scanner.nextLine();
            if (!Objects.equals(input, "sí") && !Objects.equals(input, "si"))
                break;

        } while (true);

    }

    private static int menuCantidadADevolver(String nombreProducto, int min, int max) {
        System.out.printf("Escriba la cantidad que desea devolver del producto %s. Puede devolver hasta %s%n",
                nombreProducto,
                max);

        return validarCantidad(scanner.nextLine(), min, max);
    }

    private static Optional<Orden> listarOrdenesEleccion(List<Orden> ordenes) {
        listarOrdenes(ordenes);
        System.out.println("Selecciona el número de orden");

        int eleccion = Integer.parseInt(scanner.nextLine()) - 1;
        if (eleccion >= 0 && eleccion < ordenes.size()) {
            return Optional.of(ordenes.get(eleccion));
        }
        return Optional.empty();
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
                + "2. Productos recomendados\n"
                + "3. Realizar devolución\n"
                + "4. Listar Ordenes\n"
                + "5. Regresar\n";
    }

    private static String getOpcionesDevolucion(Devolucion devolucion) {
        return "Actualmente ha seleccionado %s articulos para devolver\n".formatted(devolucion.getProductosTransaccion().size())
                + "Selecciona una de las siguientes opciones\n"
                + "1. Elegir productos a devolver\n"
                + "2. Deshacer productos a devolver\n"
                + "3. Listar productos a devolver\n"
                + "4. Guardar\n"
                + "5. Deshacer cambios y regresar";

    }
}
