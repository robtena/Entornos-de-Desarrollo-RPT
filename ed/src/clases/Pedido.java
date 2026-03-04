package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
	
	//Atributos--------------------------------
	private final String idPedido;
    private final String idCliente;
    private final List<PedirLinea> lineas = new ArrayList<>();
    private String paisEnvio; // ISO-2 simple (ES, FR, ...)
    private boolean urgente;

    //Constructor ---------------------------------------
    public Pedido(String idPedido, String idCliente, String paisEnvio, boolean urgente) {
        if (idPedido == null || idPedido.isBlank()) throw new IllegalArgumentException("idPedido");
        if (idCliente == null || idCliente.isBlank()) throw new IllegalArgumentException("idCliente");
        if (paisEnvio == null || paisEnvio.isBlank()) throw new IllegalArgumentException("paisEnvio");
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.paisEnvio = paisEnvio;
        this.urgente = urgente;
    }

  //Geters----------------------------------------------------------
    public String getIdPedido() { return idPedido; }
    public String getIdCliente() { return idCliente; }
    public String getPaisEnvio() { return paisEnvio; }
    public boolean esUrgente() { return urgente; }
    public List<PedirLinea> getLineas() {
        	return Collections.unmodifiableList(lineas);
    	}
    
    
// ============================= MÉTODOS ======================================
    
    // Método de añadir la línea en la lista si no es una línea vacía----------
    public void incluirLinea(PedirLinea linea) {
        if (linea == null) throw new IllegalArgumentException("linea");
        lineas.add(linea);
    }   
    
}
