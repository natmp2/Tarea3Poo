// Tarea 3 POO
// Desarrollada por Felipe Benavides y Nathaniel Motykiewicz
package itcr.tarea3poo;

import itcr.tarea3poo.WallRose;
import java.util.Scanner;

public class Tarea3POO {
    private static WallRose wallRose = new WallRose();
    private static Scanner scanner = new Scanner(System.in);
    
    private static void menuPrincipal() {
        
    }
    
    private static void menuClientes() {
        
    }
    
    private static void menuOrdenes() {
       
    }
    
    private static void menuProductos() {
        
    }
    
    private static float leerFloat() {
        return scanner.nextFloat();
    }
    
    private static int leerInt() {
        return scanner.nextInt();
    }
    
    
    public static void main(String[] args) throws Exception {
        // Crear clientes
        int cliente1 = wallRose.agregarCliente("Felipe", "felipe@gmail.com");
        int cliente2 = wallRose.agregarCliente("Nathaniel", "nathaniel@gmail.com");
        int cliente3 = wallRose.agregarCliente("Mauricio", "mauricio@gmail.com");

        // Crear productos
        int producto1 = wallRose.CrearProducto("Arroz", (float) 3.50, "Bolsa 1 kg", 150);
        int producto2 = wallRose.CrearProducto("Camisa de algodón", 20, "Unidad", 80);
        int producto3 = wallRose.CrearProducto("Juego de cuchillos", 35, "Set 5 piezas", 40);
        int producto4 = wallRose.CrearProducto("Taladro inalámbrico", 80, "unidad", 25);
        int producto5 = wallRose.CrearProducto("Smart TV de 55 pulgadas", 600, "Unidad", 15);

        // Crear ordenes de cliente
        int ordenCliente1 = wallRose.crearOrden(cliente1);
        wallRose.agregarLineaOrden(ordenCliente1, producto1, 1);
        wallRose.agregarLineaOrden(ordenCliente1, producto2, 2);
        wallRose.agregarLineaOrden(ordenCliente1, producto3, 1);

        int ordenCliente2 = wallRose.crearOrden(cliente2);
        wallRose.agregarLineaOrden(ordenCliente2, producto3, 2);
        wallRose.agregarLineaOrden(ordenCliente2, producto4, 1);
        wallRose.agregarLineaOrden(ordenCliente2, producto5, 2);

        int ordenCliente3 = wallRose.crearOrden(cliente3);
        wallRose.agregarLineaOrden(ordenCliente3, producto1, 2);
        wallRose.agregarLineaOrden(ordenCliente3, producto3, 3);
        wallRose.agregarLineaOrden(ordenCliente3, producto5, 1);

        // Imprimir info después de crear clientes, productos y órdenes
        System.out.println("\nLista de clientes:");
        wallRose.verListaClientes();

        System.out.println("\nLista de productos:");
        wallRose.verListaProductos();

        System.out.println("\nLista de órdenes:");
        wallRose.verListaOrdenes();
        
    }
}
