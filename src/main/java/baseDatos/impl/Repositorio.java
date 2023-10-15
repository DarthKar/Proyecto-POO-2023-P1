package baseDatos.impl;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Repositorio {

    private static BaseDatos baseDatos;
    public static final String FILE = "basedatos.txt";
    private static final String PATH = System.getProperty("user.dir") + "\\temp\\%s";

    static {
        leer();
    }

    protected static void guardar(Vendedor vendedor) {
        OptionalInt pos = IntStream.range(0, baseDatos.getVendedores().size())
                .filter(i -> vendedor.getId() == baseDatos.getVendedores().get(i).getId())
                .findFirst();

        if (pos.isEmpty()) {
            baseDatos.getVendedores().add(vendedor);
        }
        else{
            baseDatos.getVendedores().set(pos.getAsInt(), vendedor);
        }
        guardarArchivo();
    }

    protected static void guardar(Comprador comprador) {
        OptionalInt pos = IntStream.range(0, baseDatos.getVendedores().size())
                .filter(i -> comprador.getId() == baseDatos.getVendedores().get(i).getId())
                .findFirst();

        if (pos.isEmpty()) {
            baseDatos.getCompradores().add(comprador);
        } else {
            baseDatos.getCompradores().set(pos.getAsInt(), comprador);
        }
        guardarArchivo();
    }

    public static void guardar(Producto producto) {
        OptionalInt pos = IntStream.range(0, baseDatos.getProductos().size())
                .filter(i -> producto.getId() == baseDatos.getProductos().get(i).getId())
                .findFirst();

        if (pos.isEmpty()) {
            baseDatos.getProductos().add(producto);
        } else {
            baseDatos.getProductos().set(pos.getAsInt(), producto);
        }
        guardarArchivo();
    }

    protected static Optional<Vendedor> obtenerVendedorPorId(long id) {
        return baseDatos.getVendedores().stream()
                .filter(v -> v.getId() == id).findFirst();
    }

    protected static List<Vendedor> obtenerVendedores() {
        return baseDatos.getVendedores();
    }

    protected static void eliminarVendedor(long id) {
        baseDatos.getVendedores().remove(obtenerVendedorPorId(id).orElseThrow(() -> new IllegalArgumentException("No existe el vendedor")));
    }

    protected static List<Comprador> obtenerCompradores() {
        return baseDatos.getCompradores();
    }

    protected static Optional<Comprador> obtenerCompradorPorId(long id) {
        return baseDatos.getCompradores().stream().filter(c -> c.getId() == id).findAny();
    }

    public static Optional<Producto> obtenerProducto(long id) {
        return baseDatos.getProductos().stream().filter(c -> c.getId() == id).findAny();
    }

    public static List<Producto> obtenerProductos() {
        return baseDatos.getProductos();
    }

    protected static void eliminarComprador(long id) {
        baseDatos.getCompradores().remove(obtenerCompradorPorId(id).orElseThrow(() -> new IllegalArgumentException("No existe el comprador")));
    }

    private static void guardarArchivo() {
        try {
            FileOutputStream f = new FileOutputStream(PATH.formatted(FILE));
            ObjectOutputStream objectInputStream = new ObjectOutputStream(f);
            objectInputStream.writeObject(baseDatos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void leer() {
        if (crearDirectorio() || crearArchivo()) {
            baseDatos = new BaseDatos();
            guardarArchivo();
            return;
        }

        try {
            FileInputStream f = new FileInputStream(PATH.formatted(FILE));
            ObjectInputStream objectInputStream = new ObjectInputStream(f);
            baseDatos = (BaseDatos) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean crearArchivo() {
        return !new File(PATH.formatted(FILE)).exists();
    }

    private static boolean crearDirectorio() {
        try {
            Path path = Paths.get(PATH.formatted(StringUtils.EMPTY));
            if (Files.exists(path)) return false;
            Files.createDirectory(path);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
