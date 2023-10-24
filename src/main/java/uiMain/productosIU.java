package uiMain;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Transaccion;
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

    protected static void IU(Scanner scanner) {
        productosIU.scanner = scanner;
        compradorActual = null;

        do {
            compradorActual = buscarComprador();
        } while (Objects.isNull(compradorActual));
        System.out.printf("Bienvenido %s %s%n", compradorActual.getNombre(), compradorActual.getApellido());
        Transaccion carrito = compradorActual.getCarrito();
        menuProductoLoop:
        do {
            System.out.println(pro());
            int opcion;
            try {
                opcion = validarOpcionMenu(scanner.nextLine(), 1, 12);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (opcion) {

                case 1:
                    System.out.println("\n");
                    for (int i = 0; i < Producto.getProductos().size(); i++) {
                        System.out.println((i + 1) + ". " + Producto.getProductos().get(i).getNombre()+"\n");
                    }
                    continue;
                case 2:
                    System.out.println("\n");
                    System.out.println(opcionesPago2());
                    String opcion7 = scanner.nextLine().trim();
                    switch (opcion7) {
                        case "1":
                            System.out.println("\n");
                            int contadorPR = 1;
                            List<Publicacion> publicacionesPR = compradorActual.getPublicacionesRecomendadas(5);
                            for (Publicacion pu : publicacionesPR) {
                            System.out.println(contadorPR + ". " + pu.mostrarPublicacion());
                            contadorPR++;
                            }
                            System.out.println("Elija la publicacion que le llame la atencion");
                            int selectPR = Integer.parseInt(scanner.nextLine().trim());
                            if (selectPR <= 0 || selectPR > publicacionesPR.size()) {
                                System.out.println("La publicación elegida no es valida, regresando al menu de compra");
                                continue;
                            }
                            System.out.println("Cuantas unidades desea comprar");
                            int cantidadPR = Integer.parseInt(scanner.nextLine().trim());
                        
                            Publicacion publicacionPR = publicacionesPR.get(selectPR - 1);
                            if (cantidadPR > publicacionPR.getInventario()) {
                                System.out.println("La publicación no tiene mas unidades de este producto");
                                continue;
                            }
                            System.out.println("Producto agregado al carrito correctamente");
                            ProductoTransaccion compraPR = new ProductoTransaccion(publicacionPR, cantidadPR);
                            carrito.agregarProducto(compraPR);
                        case "2":
                            System.out.println("\n");
                            System.out.println("Saliendo al menu principal");
                            break;
                        default:
                            System.out.println("\n");
                            System.out.println("La opcion elegida no es valida, regresando al menu");
                    }
                    
                    continue;

                case 3:
                    ArrayList<Publicacion> puaux = new ArrayList<>();
                    System.out.println("\n");
                    for (int i = 0; i < Producto.getProductos().size(); i++) {
                        System.out.println((i + 1) + ". " + Producto.getProductos().get(i).getNombre()+"\n");
                    }
                    System.out.println("Elija el producto que desea comprar");
                    int select = Integer.parseInt(scanner.nextLine().trim())-1;
                    if (select<0 || select>=Producto.getProductos().size()){
                        System.out.println("Indice no disponible para esta lista de productos, saliendo al menu");
                        continue menuProductoLoop;
                    }
                    System.out.println("Cuantas unidades desea comprar");
                    int cantidadDeseada = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("\n");
                    int contador = 0;
                    for (Vendedor ven : UsuarioRepositorio.obtener()) {
                        for (Publicacion pu : ven.getPublicaciones()) {
                            if (pu.getProducto().getNombre() == Producto.getProductos().get(select).getNombre() && pu.getInventario() > cantidadDeseada) {
                                System.out.println((contador+1) + ". " + pu.mostrarPublicacion());
                                puaux.add(pu);
                                contador++;
                            } 
                        }
                    }
                    if (puaux.size() <= 0){
                        System.out.println("No existen publicaciones de este producto o que tenga las unidades requeridas, regresando al menu");
                        continue menuProductoLoop;
                    }
                    System.out.println("Elija la publicacion que le llame la atencion");

                    int select1 = Integer.parseInt(scanner.nextLine().trim());
                    if (select1 <= 0 || select1 > puaux.size()) {
                        System.out.println("La publicación elegida no es valida.");
                        continue;
                    }
                    ProductoTransaccion compra = new ProductoTransaccion(puaux.get(select1 - 1), cantidadDeseada);
                    carrito.agregarProducto(compra);
                    System.out.println("Producto agregado correctamente al carrito");
                    continue;

                case 4:
                    System.out.println("\n");
                    if (carrito.getProductosTransaccion().isEmpty()){
                        System.out.println("El carrito esta vacio no hay nada por eliminar,regresando al menu de compra");
                        continue menuProductoLoop;
                    }
                    ((Carrito)carrito).mostrarCarrito((Carrito)carrito);
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
                    System.out.println("Producto eliminado correctamente del carrito");
                    continue;

                case 5:
                    System.out.println("\n");
                    if (carrito.getProductosTransaccion().size() == 0) {
                        System.out.println("Carrito vacio");
                    }
                    else{
                        ((Carrito)carrito).mostrarCarrito((Carrito)carrito);
                        System.out.println("El total parcial es: "+carrito.calcularTotal());
                    }   
                    continue; 
                case 6:
                    System.out.println("\n");
                    System.out.println(opciones_5());
                    String opcion2 = scanner.nextLine().trim();
                    switch (opcion2) {
                        case "1":
                            System.out.println("\n");
                            if (carrito.getProductosTransaccion().size() == 0) {
                                System.out.println("No hay productos por modificar, el carrito esta vacio, volviendo al menu de compra");
                                continue menuProductoLoop;
                            }
                            ((Carrito)carrito).mostrarCarrito((Carrito)carrito);
                            System.out.println("Seleccione que producto desea modificar");
                            int select3 = Integer.parseInt(scanner.nextLine().trim())-1;
                            System.out.println("¿Cuantas unidades desea comprar ahora?");
                            int Ncantidad = Integer.parseInt(scanner.nextLine().trim());
                            if (Ncantidad == 0) {
                                System.out.println("\n");
                                System.out.println("Si quieres eliminar el producto ve a al opcion 'eliminar', regresando al menu principal");
                                continue menuProductoLoop;
                            }
                            if(Ncantidad < 0){
                                System.out.println("No puedes poner cantidades negativas, regresando al menu");
                                continue menuProductoLoop;
                            }
                            carrito.modificarProducto(carrito.getProductosTransaccion().get(select3), Ncantidad);
                            continue;
                        case "2":
                            if (carrito.getProductosTransaccion().size() == 0) {
                                System.out.println("El carrito ya esta vacio, volviendo al menu de compra");
                                continue menuProductoLoop;
                            }
                            System.out.println("\n");
                            carrito.getProductosTransaccion().clear();
                            System.out.println("Carrito correctamente vaciado");
                            continue;
                        case "3":
                            System.out.println("\n");
                            System.out.println("Regresando al menú de compra....");
                            break menuProductoLoop;
                        default:
                            System.out.println("\n");
                            System.out.println("Opción inválida. Regresando al menú de compra");
                            break; // Regresa automáticamente al menú principal
                    }

                    continue;

                case 7:
                    System.out.println(opcionesPago());
                    String opcion4 = scanner.nextLine().trim();
                    switch (opcion4) {
                        case "1":
                            System.out.println("\n");
                            System.out.println("Su saldo actual es de:  " + compradorActual.getSaldo());
                            continue ;
                        case "2":
                            System.out.println("\n");
                            System.out.println("Cuanto saldo desea agregar");
                            float saldoagregado = Float.parseFloat(scanner.nextLine().trim());
                            if(saldoagregado < 0){
                                System.out.println("No puedes poner cantidades negativas, regresando al menu");
                                continue menuProductoLoop;
                            }
                            compradorActual.agregarSaldo(saldoagregado);
                            System.out.println("Saldo agregado con exito");
                            break;
                        default:
                            System.out.println("\n");
                            System.out.println("La opcion elegida no es valida, regresando al menu");
                    }
                    continue;
                case 8:
                    if (carrito.getProductosTransaccion().size() == 0) {
                        System.out.println("No se puede crear una orden de compra con el carrito vacio, regresando al menu de compra");
                        continue menuProductoLoop;
                    }
                    System.out.println("\n");
                    System.out.println("Detalles de la orden de compra: ");
                    Random random = new Random();
                    long numeroAleatorio = 100 + random.nextInt(900);
                    Orden ordencompra = new Orden(numeroAleatorio, compradorActual);
                    List<ProductoTransaccion> nuevaLista = new ArrayList<>(carrito.getProductosTransaccion());
                    ordencompra.setProductosTransaccion(nuevaLista);
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
                            System.out.println("\n");
                            compradorActual.agregarOrden(ordencompra);
                            System.out.println("El id es: " + ordencompra.getId() + " guardelo para el pago");
                            System.out.println("Orden creada con exito, recuerde pagarlo inmediatamente, regresando al menu de compra");
                            carrito.getProductosTransaccion().clear();
                            continue menuProductoLoop;

                        case "2":
                            System.out.println("\n");
                            System.out.println("No se ha creado la orden, regresando al menú de compra");
                            continue menuProductoLoop;
                        default:
                            System.out.println("\n");
                            System.out.println("La opcion elegida no es valida, regresando al menu");
                    }
                    continue;

                case 9:
                    if (compradorActual.getOrdenes().size() == 0) {
                        System.out.println("Usted no tiene ordenes para pagar, regresando al menu de compra");
                        continue menuProductoLoop;
                    }
                    System.out.println("\n");
                    int contador3 = 1;
                    for (Orden or : compradorActual.getOrdenes()) {
                        System.out.println(contador3 + ". " + or.mostrarOrden());
                        contador3++;
                    }
                    System.out.println("Elija la orden de compra que desea pagar");
                    int select3 = Integer.parseInt(scanner.nextLine().trim())-1;
                    Orden ordenpagar = compradorActual.getOrdenes().get(select3);
                    float totalpagar = compradorActual.AplicarDescuento(ordenpagar.calcularTotal());
                    System.out.println("Esta es su informacion de pago");
                    System.out.println(compradorActual.mostrarInformacion());
                    System.out.println(ordenpagar.mostrarOrden());
                    ordenpagar.mostrarProductoorden(ordenpagar);
                    System.out.println(opcionesPago1());
                    String opcion5 = scanner.nextLine().trim();
                    switch (opcion5) {
                        
                        case "1":
                            System.out.println("\n");
                            if (compradorActual.getSaldo() < totalpagar) {
                                System.out.println("El saldo es insuficiente");
                                continue menuProductoLoop;
                            }
                            compradorActual.quitarSaldo(totalpagar);
                            for (ProductoTransaccion prod : ordenpagar.getProductosTransaccion()) {
                                prod.getPublicacion().getProducto().getCompradores().add(compradorActual);
                            }
                            for (ProductoTransaccion comprado : ordenpagar.getProductosTransaccion()) {
                                compradorActual.agregarProductoComprado(comprado);
                            }
                            ordenpagar.pagado();
                            System.out.println("Pago realizado con exito");
                            System.out.println("El descuento por membresia es de: "+(ordenpagar.calcularTotal()-totalpagar));
                            System.out.println("Total pagado: " + totalpagar);
                            continue menuProductoLoop;
                        
                        case "2":
                            System.out.println("\n");
                            System.out.println("Cancelando....");
                            continue menuProductoLoop;
                        default:
                            System.out.println("\n");
                            System.out.println("La opcion elegida no es valida, regresando al menu");
                    }
                    return;
                
                case 10:
                    System.out.println("\n");
                    if (compradorActual.getOrdenes().isEmpty()){
                        System.out.println("No hay ordenes de pago que eliminar, regresando al menu");
                        continue menuProductoLoop;
                    }
                    System.out.println("Advertencia:  si elimina las ordenes de pago ya no podra hacer una devolucion de estas, ¿desea continuar?");
                    System.out.println(opcionesPago3());
                    String opcion6 = scanner.nextLine().trim();
                    switch (opcion6){
                        case "1":
                            System.out.println("\n");
                            compradorActual.getOrdenes().clear();
                            System.out.println();
                            System.out.println("Ordenes de compra eliminadas, regresando al menu de compra");
                            continue menuProductoLoop;
                        case "2":
                            System.out.println("\n");
                            System.out.println("Regresando al menu de compra...");
                            continue menuProductoLoop;
                        default:
                            System.out.println("\n");
                            System.out.println("La opcion elegida no es valida, regresando al menu");
                    }
                    continue;
                
                case 11:
                    System.out.println("\n");
                    if (compradorActual.getOrdenes().isEmpty()){
                        System.out.println("No hay ordenes de pago, regresando al menu");
                        continue menuProductoLoop;
                    }
                    int contador4 = 1;
                    for (Orden ora : compradorActual.getOrdenes()) {
                        System.out.println(contador4 + ". " + ora.mostrarOrden());
                        contador4++;
                    }
                    continue;
                
                case 12:
                    return;
                default:
                    System.out.println("\n");
                    System.out.println("Has elegido una opción invalida. Regresando al menú");
                    continue menuProductoLoop;
            }
        } while (true);
    }


    private static String pro() {
        return "\nSeleccione una opcion\n"
                + "1. Mostrar lista de productos\n"
                + "2. Agregar productos recomendados al carrito\n"
                + "3. Agregar productos al carrito\n"
                + "4. Eliminar productos del carrito\n"
                + "5. Mostrar carrito\n"
                + "6. Modificar carrito\n"
                + "7. Modificar informacion de pago\n"
                + "8. Crear orden de compra\n"
                + "9. Realizar pago\n"
                + "10. Vaciar ordenes de pago\n"
                + "11. Ver ordenes de pago\n"
                + "12. Volver al menu principal\n";
    }


    private static String opciones_5() {
        return "Que desea modificar?\n"
                + "1. Modificar cantidad de un producto\n"
                + "2. Vaciar carrito completamente\n"
                + "3. Volver al menu principal\n";
    }


    private static String opciones_6() {
        return "Desea crear esta orden(Esto vaciara el carrito)\n"
                + "1. Si\n"
                + "2. No\n";
    }


    private static String opcionesPago() {
        return "¿Seleccione una opcion?\n"
                + "1. Mostrar saldo disponible\n"
                + "2. Agregar fondos\n";
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

    private static String opcionesPago2() {
        return "Desea agregar algun producto de la lista de recomendados al carrito\n"
                + "1. Si\n"
                + "2. No\n";
    }

    private static String opcionesPago3() {
        return "¿Desea continuar?\n"
                + "1. Si\n"
                + "2. No\n";
    }
}
