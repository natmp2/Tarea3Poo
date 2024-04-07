package itcr.tarea3poo.Logica;

public enum EstadoCompra {
    INICIADA,
    PENDIENTE,
    COMPLETA;

    @Override
    public String toString() {
        switch (this) {
            case INICIADA:
                return "INICIADA";
            case PENDIENTE:
                return "PAGO PENDIENTE";
            case COMPLETA:
                return "COMPLETA";
            default:
                return "Estado inválido";
        }
    }
}


