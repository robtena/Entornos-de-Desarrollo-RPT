package ed3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioPedidos {
	// Atributos ------------------------------------------------------------------
	
	//Creamos un nuevo repositorio de clientes
	private final RepositorioClientes repoClientes = new RepositorioClientes();
	
	//Establecemos la acción ProcesarResultado
    public ProcesarResultado pedidoProc(
            String idPedido,
            String idCliente,
            String pais,
            boolean urgente,
            List<PedirLinea> lineas,
            String codigoDescuento,
            boolean tarjetaRegalo,
            String notas,
            boolean depurar
    ) {
    	//Si le hemos pedido que depure el codigo imprime en pantalla mucha información sobre el proceso
        if (depurar) System.out.println("Empezar depuración: " + LocalDateTime.now());

        // Si hay atributos necesarios vacíos lanza un error de un dato mal recogido
        if (idPedido == null || idPedido.isBlank()) throw new IllegalArgumentException("idPedido");
        if (idCliente == null || idCliente.isBlank()) throw new IllegalArgumentException("idCliente");
        if (pais == null || pais.isBlank()) throw new IllegalArgumentException("pais");
        if (lineas == null || lineas.isEmpty()) throw new IllegalArgumentException("lineas");

        //Crea un cliente sacado del repositorio buscándolo por id. Si no lo encuentra lanza un error
        Clientes c = repoClientes.buscarId(idCliente);
        if (c == null) throw new IllegalArgumentException("Customer not found: " + idCliente);

        // Creamos un pedido y lo incluimos en la linea
        Pedido pedido = new Pedido(idPedido, idCliente, pais, urgente);
        for (PedirLinea l : lineas) {
            pedido.incluirLinea(l);
        }

        // Vamos contando el precio inicial y los elementos fragiles, ya que vienen con recargo
        int subtotal = 0;
        int contarFragiles = 0;
        for (PedirLinea l : pedido.getLineas()) {
            subtotal += l.totalCentimosLinea();
            if (l.esFragil()) contarFragiles += l.getCantidad();
        }

        // Cálculo para el envio----------------------------------------------------------------------------------------
        int enviar = 0;
        
        if ("ES".equalsIgnoreCase(pais)) {			//Si se envía a ES(España) el envio es 3'99 euros (399 cent)
            enviar = 399;
        } else if ("PT".equalsIgnoreCase(pais)) {	//Si se envía a PT(Portugal) el envio es 5'99 euros (599 cent)
            enviar = 599;
        } else {									//En cualquier otro caso el envio es 12'99 euros (1299 cent)
            enviar = 1299;
        }
        
        if (urgente) {								//Si es urgente se añade al envio un recargo de 5 euros (500 cent)
            enviar += 500;
        }
        if (contarFragiles > 0) {					//Si hay elementos frágiles se añade un recargo de 2 euros (200 cent)
            enviar += 200; 
        }

        // Cálculo de descuento -------------------------------------------------------------------------------------
        int descuento = 0;
        
        if (codigoDescuento != null && !codigoDescuento.isBlank()) {  		//Si hay codigo de descuento
            if ("10%menos".equalsIgnoreCase(codigoDescuento)) {				//Si es 10%menos calculamos en el descuento el 10%
                descuento = (int) Math.round(subtotal * 0.10);
            } else if ("VIP5".equalsIgnoreCase(codigoDescuento)) {
                descuento = (int) Math.round(subtotal * 0.05);
            } else if ("ENVIOGRATIS".equalsIgnoreCase(codigoDescuento)) {
                descuento = 0;
                enviar = 0; 
            }
        }

        if (c.esVip()) {
            if (codigoDescuento == null || codigoDescuento.isBlank() || !"10%menos".equalsIgnoreCase(codigoDescuento)) {
                descuento += (int) Math.round(subtotal * 0.03);
            }
        }

        int envoltorio = tarjetaRegalo ? 250 : 0;

        int total = subtotal + enviar + envoltorio - descuento;
        if (total < 0) total = 0;

        StringBuilder recibo = new StringBuilder();
        recibo.append("PEDIDO ").append(idPedido).append("\n");
        recibo.append("CLIENTE ").append(c.getNombre()).append("\n");
        recibo.append("LINEAS ").append(pedido.getLineas().size()).append("\n");
        recibo.append("SUBTOTAL ").append(subtotal).append("\n");
        recibo.append("ENVIO ").append(enviar).append("\n");
        recibo.append("ENVOLTORIO ").append(envoltorio).append("\n");
        recibo.append("DESCUENTO ").append(descuento).append("\n");
        recibo.append("TOTAL ").append(total).append("\n");
        if (notas != null && !notas.isBlank()) recibo.append("NOTAS ").append(notas).append("\n");

        if (depurar) {
            System.out.println("Depurar subtotal=" + subtotal + " envio=" + enviar + " descuento=" + descuento + " total=" + total);
            System.out.println("Terminar depuración " + LocalDateTime.now());
        }

        return new ProcesarResultado(pedido, subtotal, enviar, envoltorio, descuento, total, recibo.toString());
    }

    public static class ProcesarResultado {
        public final Pedido order;
        public final int subtotalCent;
        public final int envioCent;
        public final int envoltorioRegaloCent;
        public final int descuentoCent;
        public final int totalCents;
        public final String reciboText;

        public ProcesarResultado(Pedido pedido, int subtotalCents, int envioCent, int envoltorioRegaloCent, int descuentoCent, int totalCents, String reciboText) {
            this.order = pedido;
            this.subtotalCent = subtotalCents;
            this.envioCent = envioCent;
            this.envoltorioRegaloCent = envoltorioRegaloCent;
            this.descuentoCent = descuentoCent;
            this.totalCents = totalCents;
            this.reciboText = reciboText;
        }
    }

    public static List<PedirLinea> lines(PedirLinea... ls) {
        List<PedirLinea> out = new ArrayList<>();
        for (PedirLinea l : ls) out.add(l);
        return out;
    }
}
