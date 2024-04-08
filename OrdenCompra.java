package itcr.tarea3poo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrdenCompra {
    private static int consecutivo = 1;
    private static final float IV = 0.13f;
    private int numero;
    private LocalDateTime fechaHora;
    private EstadoCompra estado;
    private Clientes cliente;
    private ArrayList<LineaOrdenCompra> lineas;

    public OrdenCompra(Clientes cliente) {
        this.numero = consecutivo++;
        this.fechaHora = LocalDateTime.now();
        this.estado = EstadoCompra.INICIADA; // cuando se crea por primera vez ya va a salir como iniciada
        this.cliente = cliente;
        this.lineas = new ArrayList();
    }
    
    
    public int getNumero() {
        return numero;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public ArrayList<LineaOrdenCompra> getLineas() {
        return lineas;
    }
   
    public void agregarLinea(float cantidad, Productos producto){
        LineaOrdenCompra nuevaLinea = new LineaOrdenCompra(cantidad,producto);
        lineas.add(nuevaLinea);
    }
    
    public float calcularCosto(){
        float totalSinImpuestos = 0;
        for (LineaOrdenCompra linea : lineas){
            totalSinImpuestos+= linea.calcularCosto();
        }
        return totalSinImpuestos;
    }
    
    public float calcularImpuesto(){
        return calcularCosto() * IV;
    }
    
    public float calcularTotal() {
        return calcularCosto() + calcularImpuesto();
    }
    
    
    @Override 
    public String toString() {
        String resultado = "Número de orden: " + numero + "\n";
        resultado += "Fecha: " + fechaHora + "\n";
        resultado += "Estado: " + estado + "\n";
        resultado += "Cliente: " + cliente.getNombre() + "\n";
        resultado += "orden:\n";
        for (LineaOrdenCompra linea : lineas) {
            resultado += linea.toString() + "\n";
        }
        resultado += "Costo sin impuestos: $" + calcularCosto() + "\n";
        resultado += "Impuestos: $" + calcularImpuesto() + "\n";
        resultado += "Total a pagar: $" + calcularTotal() + "\n";
        return resultado;
    }
}






