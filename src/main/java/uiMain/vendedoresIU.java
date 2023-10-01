package uiMain;

import de.vandermeer.asciitable.AsciiTable;
import gestorAplicacion.casoDeUso.UsuarioCDU;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

import java.util.Scanner;

public class vendedoresIU {

    private static final UsuarioCDU usuarioCDU = new UsuarioCDU();

    protected static void IU(Scanner scanner) {
        menuVendedorLoop:
        do {
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    listarVendedores();
                    continue;
                case "2":
                    buscarVendedor(scanner);
                    continue;
                case "3":
                    guardarVendedor(scanner);
                    continue;
                case "4":
                    break menuVendedorLoop;
                default:
                    System.out.println("Has elegido una opción invalida. Regresando al menú");
            }
        } while (true);
    }

    private static void buscarVendedor(Scanner scanner) {
        System.out.println("Ingresa id a buscar");
        String id = scanner.nextLine();
        final Vendedor vendedor;
        try {
            vendedor = usuarioCDU.obtenerVendedorPorId(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        menuBuscarVendedor:
        do {
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow("Cédula", "Nombre", "Apellidos", "Email");
            at.addRule();
            at.addRow(vendedor.getId(), vendedor.getNombre(), vendedor.getApellido(), vendedor.getCorreo());
            at.addRule();
            System.out.println(at.render());
            System.out.println(getOpcionesBuscarVendedor());
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    modificarVendedor(scanner, id, vendedor.getNombre(), vendedor.getApellido(), vendedor.getApellido());
                    break menuBuscarVendedor;
                case "2":
                    eliminarVendedor(scanner, vendedor.getId());
                    break menuBuscarVendedor;
                case "3":
                    break menuBuscarVendedor;
                default:
                    System.out.println("Has elegido una opción invalida. Regresando al menú");
            }
        } while (true);
    }

    private static void modificarVendedor(Scanner scanner, String id, String nombre, String apellido, String correo) {

        System.out.printf("El valor actual del nombre es: %s. Escribe a continuación el valor por el cual lo desea modificar o si por el contrario no desea modificarlo pulse enter%n", nombre);
        String nombreMod = scanner.nextLine();

        System.out.printf("El valor actual del apellido es: %s. Escribe a continuación el valor por el cual lo desea modificar o si por el contrario no desea modificarlo pulse enter%n", apellido);
        String apellidoMod = scanner.nextLine();

        System.out.printf("El valor actual del correo es: %s. Escribe a continuación el valor por el cual lo desea modificar o si por el contrario no desea modificarlo pulse enter%n", correo);
        String correoMod = scanner.nextLine();

        try {
            usuarioCDU.modificarVendedor(id, nombreMod, apellidoMod, correoMod);
        } catch (IllegalArgumentException e) {
            System.out.println("No es posible guardar el vendedor debido a: \n" + e.getMessage());
        }
    }

    private static void eliminarVendedor(Scanner scanner, long id) {
        System.out.printf("Escribe si para confirmar la eliminación del usuario %s%n", id);
        String input = scanner.nextLine();
        if (!input.equals("si"))
            return;

        usuarioCDU.eliminarVendedor(id);
    }

    private static void listarVendedores() {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Cédula", "Nombre", "Apellidos", "Email");
        at.addRule();
        usuarioCDU.obtenerVendedores().forEach(vendedor -> {
            at.addRow(vendedor.getId(), vendedor.getNombre(), vendedor.getApellido(), vendedor.getCorreo());
            at.addRule();
        });
        System.out.println(at.render());
    }

    private static void guardarVendedor(final Scanner scanner) {
        System.out.println("Ingresa id");
        String id = scanner.nextLine();

        System.out.println("Ingresa nombre");
        String nombre = scanner.nextLine();

        System.out.println("Ingresa apellido");
        String apellido = scanner.nextLine();

        System.out.println("Ingresa correo");
        String correo = scanner.nextLine();

        try {
            usuarioCDU.guardarVendedor(id, nombre, apellido, correo);
        } catch (IllegalArgumentException e) {
            System.out.println("No es posible guardar el vendedor debido a: \n" + e.getMessage());
        }
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Listar vendedores\n"
                + "2. Buscar vendedor\n"
                + "3. Crear vendedor\n"
                + "4. Para regresar\n";

    }

    private static String getOpcionesBuscarVendedor() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Modificar vendedor\n"
                + "2. Eliminar vendedor\n"
                + "3. Regresar\n";

    }
}
