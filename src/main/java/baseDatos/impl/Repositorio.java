package baseDatos.impl;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Repositorio {

    private static BaseDatos baseDatos;
    public static final String FILE = "basedatos.txt";
    private static final String PATH = System.getProperty("user.dir") + "\\temp\\%s";

    protected static void guardar() {
        try {
            FileOutputStream f = new FileOutputStream(PATH.formatted(FILE));
            ObjectOutputStream objectInputStream = new ObjectOutputStream(f);
            objectInputStream.writeObject(baseDatos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void leer() {
        if(crearDirectorio() || verificarArchivo()){
            baseDatos = new BaseDatos();
            return;
        }

        try {
            FileInputStream f = new FileInputStream(PATH.formatted(FILE));
            ObjectInputStream objectInputStream = new ObjectInputStream(f);
            baseDatos = (BaseDatos) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verificarArchivo() {
        return new File(PATH.formatted(FILE)).exists();
    }

    public static boolean crearDirectorio() {
        try {
            Path path = Paths.get(PATH.formatted(FILE));
            if (Files.exists(path))
                return false;
            Files.createDirectory(path);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Vendedor> getVendedores() {
        return baseDatos.getVendedores();
    }
}
