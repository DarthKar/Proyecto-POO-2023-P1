package uiMain;

import gestorAplicacion.casoDeUso.*;

import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final ComprarCDU comprarCDU = new ComprarCDU();
    private static final ContabilidadCDU contabilidadCDU = new ContabilidadCDU();
    private static final DevolucionCDU devolucionCDU = new DevolucionCDU();
    private static final OpinionCDU opinionCDU = new OpinionCDU();
    private static final RecomendacionCDU recomendacionCDU = new RecomendacionCDU();


    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println("Bienvenido a e-commerce XYZ");
        menuLoop : do {
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1", "2":
                    continue;
                case "10":
                    break menuLoop;
            }
        } while (true);
        System.out.println("Hasta luego :)");
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Realizar compra\n"
                + "2....\n"
                + "3....\n"
                + "10. Para cerrar la aplicación";
    }
}
