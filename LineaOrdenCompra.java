package itcr.tarea3poo.Logica;

public class LineaOrdenCompra {
    private float cantidad;
    private Productos producto;

    public LineaOrdenCompra(float cantidad, Productos producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

        public float getCantidad() {
    return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
    

    public float calcularCosto() {
        return cantidad * producto.getPrecio();
    }

    
    @Override
    public String toString() {
        String resultado = "Cantidad: " + cantidad;
        resultado += ", Producto: " + producto.getNombre();
        resultado+= ", Subtotal: $" + calcularCosto();
        return resultado;
    }


}


