package uiMain;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import baseDatos.impl.UsuarioRepositorio;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Carrito;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import uiMain.utilidades.Validaciones;

import java.util.*;

public class productosIU extends Validaciones {
    static Comprador compradorActual;
    private static Scanner scanner;
    static ArrayList<Publicacion> puaux = new ArrayList<>();

    protected static void IU(Scanner scanner) {
        productosIU.scanner = scanner;
        compradorActual = null;

        do {
            compradorActual = buscarComprador();
        } while (Objects.isNull(compradorActual));

        Carrito carrito = compradorActual.getCarrito();
        menuProductoLoop:
        do {
            System.out.println(pro());
            int opcion;
            try {
                opcion = validarOpcionMenu(scanner.nextLine(), 1, 10);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (opcion) {

                case 1:
                    for (int i = 0; i < Producto.getProductos().size(); i++) {
                        System.out.println((i + 1) + ". " + Producto.getProductos().get(i).getNombre()+"\n");
                    }
                    continue;
                case 2:
                    int contadorPR = 1;
                    List<Publicacion> publicacionesPR = compradorActual.getPublicacionesRecomendadas(5);
                    for (Publicacion pu : publicacionesPR) {
                        System.out.println(contadorPR + ". " + pu.mostrarPublicacion());
                        contadorPR++;
                    }
                    System.out.println("Elija la publicacion que le llame la atencion");
                    int selectPR = Integer.parseInt(scanner.nextLine().trim());
                    if (selectPR < 0 || selectPR > publicacionesPR.size()) {
                        System.out.println("La publicación elegida no es valida.");
                        continue;
                    }
                    System.out.println("Cuantas unidades desea comprar");
                    int cantidadPR = Integer.parseInt(scanner.nextLine().trim());

                    Publicacion publicacionPR = publicacionesPR.get(selectPR - 1);
                    if (cantidadPR > publicacionPR.getInventario()) {
                        System.out.println("La publicación no tiene mas unidades de este producto");
                        continue;
                    }

                    ProductoTransaccion compraPR = new ProductoTransaccion(publicacionPR, cantidadPR);
                    carrito.agregarProducto(compraPR);
                    System.out.println("\n");
                    continue;

                case 3:
                    for (int i = 0; i < Producto.getProductos().size(); i++) {
                        System.out.println((i + 1) + ". " + Producto.getProductos().get(i).getNombre()+"\n");
                    }
                    System.out.println("Elija el producto que desea comprar");
                    int select = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Cuantas unidades desea comprar");
                    int cantidadDeseada = Integer.parseInt(scanner.nextLine().trim());
                    int contador = 1;
                    for (Vendedor ven : UsuarioRepositorio.obtener()) {
                        for (Publicacion pu : ven.getPublicaciones()) {
                            if (pu.getProducto() == Producto.getProductos().get(select) && pu.getInventario() > cantidadDeseada) {
                                System.out.println(contador + ". " + pu.mostrarPublicacion());
                                puaux.add(pu);
                                contador++;
                            } else {
                                break;
                            }
                        }
                    }
                    System.out.println("Elija la publicacion que le llame la atencion");

                    int select1 = Integer.parseInt(scanner.nextLine().trim());
                    if (select1 <= 0 || select1 > puaux.size()) {
                        System.out.println("La publicación elegida no es valida.");
                        continue;
                    }
                    ProductoTransaccion compra = new ProductoTransaccion(puaux.get(select1 - 1), cantidadDeseada);
                    carrito.agregarProducto(compra);
                    System.out.println("Producto agregado correctamente al carrito\n");
                    continue;

                case 4:
                    if(carrito.getProductosTransaccion().isEmpty()){
                        System.out.println("No ha agregado ningún producto al carrito");
                        continue;
                    }
                    carrito.mostrarCarrito(carrito);
                    System.out.println("\n");
                    System.out.println("Elija el producto que desea eliminar del carrito");
                    int select2 = Integer.parseInt(scanner.nextLine().trim());
                    if (select2 <= 0 || select2 > carrito.getProductosTransaccion().size()){
                        System.out.println("El producto elegido no es valido.");
                        continue;
                    }
                    System.out.println("Cuantas unidades desea eliminar");
                    int cantidadEl = Integer.parseInt(scanner.nextLine().trim());
                    ProductoTransaccion productoTransaccion = carrito.getProductosTransaccion().get(select2-1);
                    if (cantidadEl == productoTransaccion.getCantidad()) {
                        carrito.removerProducto(productoTransaccion);
                    } else {
                        productoTransaccion.setCantidad(productoTransaccion.getCantidad() - cantidadEl);
                    }
                    System.out.println("Producto eliminado correctamente del carrito\n");
                    continue;

                case 5:
                    carrito.mostrarCarrito(carrito);
                    continue;

                case 6:
                    System.out.println(opciones_5());
                    String opcion2 = scanner.nextLine().trim();
                    switch (opcion2) {
                        case "1":
                            carrito.mostrarCarrito(carrito);
                            System.out.println("Seleccione que producto desea modificar");
                            int select3 = Integer.parseInt(scanner.nextLine().trim());
                            int Ncantidad = Integer.parseInt(scanner.nextLine().trim());
                            carrito.modificarProducto(carrito.getProductosTransaccion().get(select3), Ncantidad);
                            continue;
                        case "2":
                            carrito.getProductosTransaccion().clear();
                            System.out.println("Carrito correctamente vaciado");
                            continue;
                        case "3":
                            break menuProductoLoop;
                        default:
                            System.out.println("Opción inválida. Regresando al menú de carrito");
                            break; // Regresa automáticamente al menú principal
                    }

                    continue;

                case 7:
                    System.out.println("Detalles de la orden de compra");
                    Random random = new Random();
                    long numeroAleatorio = 100 + random.nextInt(900);
                    Orden ordencompra = new Orden(numeroAleatorio, compradorActual);
                    ordencompra.setProductosTransaccion(carrito.getProductosTransaccion());
                    int contador2 = 1;
                    for (ProductoTransaccion productosTransaccion : ordencompra.getProductosTransaccion()) {
                        System.out.println(contador2 + ". " + productosTransaccion.mostrarEspProducto());
                        contador2++;
                    }
                    System.out.println("El total de la transaccion es: " + ordencompra.calcularTotal());
                    System.out.println(opciones_6());
                    String opcion3 = scanner.nextLine().trim();
                    switch (opcion3) {

                        case "1":
                            compradorActual.agregarOrden(ordencompra);
                            System.out.println("Orden creada con exito, regresando al menu de compra");
                            System.out.println("El id es: " + ordencompra.getId() + " guardelo para el pago");
                            carrito.getProductosTransaccion().clear();
                            break;

                        case "2":
                            System.out.println("No se ha creado la orden, regresando al menú de compra");
                            break;
                    }
                    continue;

                case 8:
                    int contador3 = 1;
                    for (Orden or : compradorActual.getOrdenes()) {
                        System.out.println(contador3 + ". " + or.mostrarOrden());
                        contador3++;
                    }
                    System.out.println("Elija la orden de compra que desea pagar");
                    int select3 = Integer.parseInt(scanner.nextLine().trim());
                    Orden ordenpagar = compradorActual.getOrdenes().get(select3);
                    float totalpagar = compradorActual.AplicarDescuento(ordenpagar.calcularTotal());
                    System.out.println(opcionesPago());
                    String opcion4 = scanner.nextLine().trim();
                    switch (opcion4) {
                        case "1":
                            System.out.println("Su saldo actual es de:  " + compradorActual.getSaldo() + "¿Cuanto dinero desea agregar?");
                            float saldoagregado = Float.parseFloat(scanner.nextLine().trim());
                            compradorActual.agregarSaldo(saldoagregado);
                            break;
                        case "2":
                            break;
                    }
                    System.out.println("Esta es su informacion de pago");
                    System.out.println(compradorActual.mostrarInformacion());
                    System.out.println(ordenpagar.mostrarOrden());
                    System.out.println(opcionesPago1());
                    String opcion5 = scanner.nextLine().trim();
                    switch (opcion5) {
                        
                        case "1":
                            if (compradorActual.getSaldo() < totalpagar) {
                                System.out.println("El saldo es insuficiente");
                                break;
                            }
                            compradorActual.quitarSaldo(totalpagar);
                            for (ProductoTransaccion prod : ordenpagar.getProductosTransaccion()) {
                                prod.getPublicacion().getProducto().getCompradores().add(compradorActual);
                            }
                            for (ProductoTransaccion comprado : ordenpagar.getProductosTransaccion()) {
                                compradorActual.agregarProductoComprado(comprado);
                            }
                            System.out.println("Pago realizado con exito");
                            System.out.println("Total pagado: " + totalpagar);
                            break;
                        
                        case "2":
                            System.out.println("Cancelando....");
                            break;
                    }
                    return;
                
                case 9:
                    return;
                default:
                    System.out.println("Has elegido una opción invalida. Regresando al menú");
            }
        } while (true);
    }


    private static String pro() {
        return "Seleccione una opcion\n"
                + "1. Mostrar lista de productos\n"
                + "2. Agregar productos recomendados al carrito\n"
                + "3. Agregar productos al carrito\n"
                + "4. Eliminar productos del carrito\n"
                + "5. Mostrar carrito\n"
                + "6. Modificar carrito\n"
                + "7. Crear orden de compra\n"
                + "8. Realizar pago\n"
                + "9. Volver al menu principal\n";
    }


    private static String opciones_5() {
        return "Que desea modificar?\n"
                + "1. Modificar cantidad de un producto\n"
                + "2. Vaciar carrito completamente\n"
                + "3. Volver al menu principal\n";
    }


    private static String opciones_6() {
        return "Desea crear esta orden(Esto vaciara el carrito)"
                + "1. Si\n"
                + "2. No\n";
    }


    private static String opcionesPago() {
        return "¿Desea agregar fondos antes de proseguir con el pago?\n"
                + "1. Si\n"
                + "2. No\n";
    }


    private static String opcionesPago1() {
        return "Desea proceder con el pago\n"
                + "1. Si\n"
                + "2. No\n";
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
