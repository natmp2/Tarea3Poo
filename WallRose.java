package itcr.tarea3poo.Control;
// diamante relleno composicion, diamante hueco agregacion no se muy bien que hace cada uno pero diay
import itcr.tarea3poo.Logica.Clientes;
import itcr.tarea3poo.Logica.OrdenCompra;
import itcr.tarea3poo.Logica.Productos;
import itcr.tarea3poo.Logica.EstadoCompra;
import itcr.tarea3poo.Logica.LineaOrdenCompra;
import java.util.Scanner;
import java.util.ArrayList;

public class WallRose {
    private ArrayList<Clientes> listaClientes;
    private ArrayList<OrdenCompra> listaOrdenes;
    private ArrayList<Productos> listaProductos; 

    public WallRose(){
        this.listaClientes = new ArrayList<>(); //netbeans me recomendó usar new ArrayList<>() en vez de new ArrayList<Clientes>() no se por que
        this.listaOrdenes = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
    }

    private Clientes obtenerCliente(int numero) throws Exception {
        for (Clientes cliente : listaClientes){
            if (cliente.getNumero() == numero) {
                return cliente;
            }
        }
        throw new Exception("Numero de cliente no encontrado.");
    }

    private Productos obtenerProductos(int codigo) throws Exception{
        for (Productos producto : listaProductos){
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        throw new Exception("Codigo de producto no encontrado.");
    }

    private OrdenCompra obtenerOrdenCompra(int numero) throws Exception{
        for (OrdenCompra orden : listaOrdenes){
            if (orden.getNumero() == numero){
                return orden;
            }
        }
        throw new Exception("Numero de orden no encontrado.");
    }



    public ArrayList<Clientes> verListaClientes() {
            return listaClientes;
    }
    
    public ArrayList<OrdenCompra> verListaOrdenes() {
        return listaOrdenes;
    }
    
    public ArrayList<Productos> verListaProductos() {
        return listaProductos;
    }
    
    
    public String verProducto(int codigoProducto) throws Exception{
        for (Productos producto : listaProductos){
            if (producto.getCodigo() == codigoProducto){
                return producto.getNombre();
            }
        }
        throw new Exception("Codigo de producto no encontrado.");
    }

    public int CrearProducto(String nombre, float precio, String unidad, float existencias) {
            Productos nuevoProducto = new Productos(nombre, precio, unidad, existencias);
            listaProductos.add(nuevoProducto);
            return nuevoProducto.getCodigo();
        }
    
    
    public int agregarCliente(String nombre, String email) { 
        Clientes nuevoCliente = new Clientes(nombre, email);
        listaClientes.add(nuevoCliente);
        return nuevoCliente.getNumero();
    }

    public ArrayList<OrdenCompra> obtenerOrdenesClienteTodas(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesTodas();
        }
        throw new Exception("Numero de cliente no encontrado");
    }
            
    public ArrayList<OrdenCompra> obtenerOrdenesClienteIniciadas(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesClienteIniciadas();
        }
        throw new Exception("Numero de cliente no encontrado");
    }
    
    public ArrayList<OrdenCompra> obtenerOrdenesClientePendientes(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesClientePendientes();
        }
        throw new Exception("Numero de cliente no encontrado");
    }
    
    public ArrayList<OrdenCompra> obtenerOrdenesClienteCompletadas(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesCompletadas();
        }
        throw new Exception("Numero de cliente no encontrado");
    }


    public int crearOrden(int numeroOrden) throws Exception { //no tuve que usar el paramentro de numeroOrden no supe adonde ponerlo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista de clientes:\n");
        for (Clientes cliente : listaClientes){
            System.out.println(cliente.toString());
        }
        System.out.println("Por favor escriba el numero de cliente al que le desea crear la orden:");
        int numeroCliente = scanner.nextInt();
        for (Clientes cliente : listaClientes){
            if (cliente.getNumero() == numeroCliente){
                OrdenCompra nuevaOrden = new OrdenCompra(cliente);
                listaOrdenes.add(nuevaOrden);
                System.out.println("se ha creado la orden corrrectamente, el numero de orden seria: " + nuevaOrden.getNumero());
                return nuevaOrden.getNumero();
        }
    }
    throw new Exception("numero de cliente no encontrado.");
    }


    public void agregarLineaOrden(int numeroOrden, int codigoProducto, float cantidad) throws Exception {
        OrdenCompra orden = obtenerOrdenCompra(numeroOrden); //ya dentro de los metodos obtener se ejecutan las excepciones
        Productos producto = obtenerProductos(codigoProducto);
        System.out.println("Las ordenes registradas a ese numero son:");
        for (OrdenCompra ordenesTotales : listaOrdenes){
            System.out.println("orden numero: " + ordenesTotales.getNumero() + "\t" +  ordenesTotales.getLineas()); ///aqui no se muy bien como devuelve esto habria que probarlo 
        }
        
        for (Productos nuevoProducto : listaProductos){
            if (nuevoProducto.getCodigo() == codigoProducto){
                orden.agregarLinea(cantidad, producto);
                orden.setEstado(EstadoCompra.PENDIENTE);
                producto.setExistencias(producto.getExistencias() - cantidad); //trainwreck medio feo pero basicamente le resta al total de existencias la cantidad que agregó a la orden
            }
            throw new Exception("no existe un producto con ese codigo.");
        }
    }


    public void EliminarLineaOrden(int numeroOrden, int indiceLinea) throws Exception {
        boolean ordenEncontrada = false;
        for (OrdenCompra orden : listaOrdenes) {
            if (orden.getNumero() == numeroOrden) {
                ordenEncontrada = true;
                
                System.out.println("Orden registrada en el sistema:");
                System.out.println(orden.toString());

                if (indiceLinea >= 0 && indiceLinea < orden.getLineas().size()) { // un toque confuso pero mientras el numero de indice este en el rango de la orden... 
                    LineaOrdenCompra linea = orden.getLineas().get(indiceLinea); //se encuentra la linea por borrar
                    Productos producto = linea.getProducto();
                    producto.agregarExistencias(linea.getCantidad()); //como se borra la linea la cantidad de productos en la orden, los productos disponibles aumentan
                    orden.getLineas().remove(indiceLinea); //finalmente se borra la linea
                    System.out.println("la linea " + indiceLinea + " se ha eliminado del numero de orden: " + numeroOrden);
                }
                else {
                    throw new Exception("El índice debe estar dentro de las líneas disponibles de la orden.");
                }   
            }
        }

        if (!ordenEncontrada) {
            throw new Exception("No se encontró el número de orden.");
        }
    }
        
        

    public void agregarExistenciasProducto(int codigoProducto, float nuevasExistencias) throws Exception {
        for (Productos producto : listaProductos){
            if (producto.getCodigo() == codigoProducto){
                producto.agregarExistencias(nuevasExistencias);
            }
        }
        throw new Exception("codigo de produco no encontrado.");
    }

} 
