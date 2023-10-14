package uiMain;

import de.vandermeer.asciitable.AsciiTable;
import gestorAplicacion.casoDeUso.UsuarioCDU;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import baseDatos.impl.BaseDatos;
import baseDatos.impl.Repositorio;

import java.util.Scanner;

public class productosIU extends Repositorio {
    protected static void IU(Scanner scanner) {
        menuVendedorLoop:
        do {
            System.out.println(pro());
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    for (int i = 0;i<baseDatos.getProductos().size() ;i++){
                        System.out.println((i+1)+". "+baseDatos.getProductos().get(i).getNombre());
                    }
                    int select = Integer.parseInt(scanner.nextLine());
                    
                    ;
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
    
    private static String pro() {
        return "Seleccione una opcion\n"
                + "1. mostrar lista de productos disponibles"
                + "2. Mostrar productos\n"
                + "3. Mostrar productos\n"
                + "4. Mostrar productos\n"
                + "5. Mostrar productos\n"
                + "6. Mostrar productos\n"
                + "7. Mostrar productos\n"
                + "8. Mostrar productos\n"
                + "9. Mostrar productos\n"
                
                
                
    }
}
