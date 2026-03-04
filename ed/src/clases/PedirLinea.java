package clases;

public class PedirLinea {
	//Atributos --------------------------------------------------------------------------
	private final String codStock;
    private final int cantidad;
    private final int precioCentimos; 
    private final boolean fragil;

    //Constructor --------------------------------------------------------------------------------------
    public PedirLinea(String codStock, int cantidad, int precioCentimos, boolean fragil) {
        // Si no hay codStock, cantidad o precio no se calcula la linea, salta un error de dato mal relleno
    	if (codStock == null || codStock.isBlank()) throw new IllegalArgumentException("codStock");
        if (cantidad <= 0) throw new IllegalArgumentException("cantidad");
        if (precioCentimos < 0) throw new IllegalArgumentException("precioCentimos");
        //
        this.codStock = codStock;
        this.cantidad = cantidad;
        this.precioCentimos = precioCentimos;
        this.fragil = fragil;
    }

    //Getters----------------------------------------------
    public String getCodStock() { return codStock; }
    public int getCantidad() { return cantidad; }
    public int getPrecioCentimos() { return precioCentimos; }
    public boolean esFragil() { return fragil; }

	// ============================= MÉTODOS ======================================
    // Método que calcula el precio por la cantidad, obteniendo el dinero total.
    public int totalCentimosLinea() {
        return precioCentimos * cantidad;
    }
}
