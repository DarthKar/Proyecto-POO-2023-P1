package uiMain;

import java.util.Scanner;

public class main {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
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
