package uiMain;

import java.util.Scanner;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Membresia;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import uiMain.utilidades.Validaciones;

public class EstadisticaUI extends Validaciones {
    private static Scanner scanner;

    public static void interfaz(Scanner scanner) {
        EstadisticaUI.scanner = scanner;

        generales:
        do {
            System.out.println(getOpciones());
            int opcion;
            try {
                opcion = validarOpcionMenu(scanner.nextLine(), 1, 5);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (opcion) {
                case 1:
                    menuEstadisticasGenerales();
                    continue;
                case 2:
                    menuEstadisticasCompradores();
                    continue;
                case 3:
                    menuEstadisticasVendedores();
                    continue;
                case 4:
                    menuEstadisticasProductos();
                    continue;
                case 5:
                    break generales;
                default:
                    System.out.println("Ha elegido una opción invalida.");
            }
        } while (true);

    }

    private static void menuEstadisticasGenerales() {
        estadisticas:
        do {
            System.out.println("Bienvenido a las estadisticas generales del E-commerce, ingrese el numero de la estadistica que desea visualizar");
            System.out.println(opcionesEstadistica());
            int opcion;
            try {
                opcion = validarOpcionMenu(scanner.nextLine(), 1, 3);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (opcion) {
                case 1:
                    totalVentas();
                    continue;
                case 2:
                    productosVendidos();
                    continue;
                case 3:
                    break estadisticas;
                default:
                    System.out.println("Ha elegido una opción invalida.");
            }
        } while (true);

    }


    private static void menuEstadisticasCompradores() {
        estadisticasCompradores:
        do {
            System.out.println("Bienvenido a las estadisticas de los compradores, ingrese el numero de la estadistica que desea visualizar");
            System.out.println(opcionesEstadisticaCompradores());
            int opcion;
            try {
                opcion = validarOpcionMenu(scanner.nextLine(), 1, 4);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (opcion) {
                case 1:
                    totalComprador();
                    continue;
                case 2:
                    totalFactura();
                    continue;
                case 3:
                    totalMembresias();
                    continue;
                case 4:
                    break estadisticasCompradores;
                default:
                    System.out.println("Ha elegido una opción invalida.");
            }
        } while (true);

    }

    private static void menuEstadisticasVendedores() {
        // TODO Auto-generated method stub
        estadisticasVendedores:
        do {
            System.out.println("Bienvenido a las estadisticas generales de los vendedores, ingrese el numero de la estadistica que desea visualizar");
            System.out.println(opcionesEstadisticaVendedores());
            int opcion;
            try {
                opcion = validarOpcionMenu(scanner.nextLine(), 1, 3);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (opcion) {
                case 1:
                    masVendedor();
                    continue;
                case 2:
                    masRecaudador();
                    continue;
                case 3:
                    break estadisticasVendedores;
                default:
                    System.out.println("Ha elegido una opción invalida.");
            }
        } while (true);

    }


    private static void menuEstadisticasProductos() {
        estadisticasProductos:
        do {
            System.out.println("Bienvenido a las estadisticas generales de los productos, ingrese el numero de la estadistica que desea visualizar");
            System.out.println(opcionesEstadisticaProductos());
            int opcion;
            try {
                opcion = validarOpcionMenu(scanner.nextLine(), 1, 4);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (opcion) {
                case 1:
                    productoMasCaro();
                    continue ;
                case 2:
                    productoMasBarato();
                    continue ;
                case 3:
                    productoMasVendido();
                    continue;
                case 4:
                    break estadisticasProductos;
                default:
                    System.out.println("Ha elegido una opción invalida.");
            }
        } while (true);

    }

    private static void productoMasBarato() {
        String barato = Producto.productoMasBarato();
        System.out.println("El producto mas barato de todo el e-commerce es %s".formatted(barato));
    }

    private static void masRecaudador() {
        String masRecaudo = Vendedor.mejorVendedorPorRecaudacion();
        System.out.println("El vendedor con la mayor recaudacion fue %s".formatted(masRecaudo));
    }

    private static void productoMasCaro() {
        String caro = Producto.productoMasCaro();
        System.out.println("El producto mas caro de todo el e-commerce es %s".formatted(caro));

    }

    private static void totalFactura() {
        String Factura = Comprador.masCompradorValor();
        System.out.println("El usuario que mas ha gastado de todo el e-commerce es %s".formatted(Factura));

    }

    private static void totalMembresias() {

        Membresia member = Comprador.MembresiaMasComprada();
        System.out.println("La membresia mas comprada es %s".formatted(member));

    }

    private static void totalVentas() {
        float ventas = Producto.ventasTotales();
        System.out.println("El e-commerce genero %s".formatted(ventas));

    }

    private static void masVendedor() {
        String masVentas = Vendedor.mejorVendedor();
        System.out.println("El vendedor que mas vendio fue %s".formatted(masVentas));
    }

    private static void totalComprador() {
        String compras = Comprador.masComprador();
        System.out.println("El Usuario con el mayor numero de productos comprados es %s".formatted(compras));
    }


    private static void productoMasVendido() {
        Producto producto = Producto.productoMasVendido();
        System.out.println("El producto mas vendido es %s que pertenece a la categoria %s "
                .formatted(producto.getNombre(), producto.getCategoria()));
    }

    private static void productosVendidos() {
        int producto = Producto.cantidadProductosVendidos();
        System.out.println("El total de productos que se vendieron fueron %s "
                .formatted(producto));
    }

    private static String getOpciones() {
        return """
                1. Estadisticas Generales
                2. Estadisticas Compradores
                3. Estadisticas Vendedores
                4. Estadisticas Productos
                5. Regresar a menu principal""";

    }

    private static String opcionesEstadistica() {
        return """
                1. Total ventas e-commerce
                2. Total Productos vendidos
                3. Regresar a menu estadísticas
                """;

    }

    private static String opcionesEstadisticaCompradores() {
        return """
                1. Usuario que mas productos ha comprado
                2. Usuario que mas dinero ha gastado\s
                3. Membresia mas comprada\s
                4. Regresar a menu estadísticas
                """;
    }

    private static String opcionesEstadisticaVendedores() {
        return """
                1. Vendedor que mas productos vendió
                2. Vendedor que mas dinero recaudó
                3. Regresar a menu estadísticas
                """;
    }

    private static String opcionesEstadisticaProductos() {
        return """
                1. Producto mas caro
                2. Producto mas barato
                3. Producto mas vendido
                4. Regresa a menu estadísticas
                """;
    }
}
