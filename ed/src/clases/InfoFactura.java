package clases;

import java.time.LocalDate;

public class InfoFactura {
	
	//Esta clase no tiene atributos
	
	// Constructor de una factura en tipo text------------------------------------------------------------------------------------------------------
    public String infoFacturaTexto(String idFactura, String nombreCliente, int centimosSubtotal, int centimosImpuestos, int centimosTotales) {
        String factura = "FACTURA " + idFactura + "\n";
        String fecha = "FECHA " + LocalDate.now() + "\n";
        String cliente = "CLIENTE " + nombreCliente + "\n";
        String subTotal = "SUBTOTAL " + dinero(centimosSubtotal) + "\n";
        String impuestos = "IMPUESTOS " + dinero(centimosImpuestos) + "\n";
        String totalDinero = "TOTAL " + dinero(centimosTotales) + "\n";
        return factura + fecha + cliente + subTotal + impuestos + totalDinero;
    }

    //Constructor de una factura en tipo html ---------------------------------------------------------------------------------------------------
    public String infoFacturaHtml(String idFactura, String nombreCliente, int centimosSubtotal, int centimosImpuestos, int centimosTotales) {       
        String factura = "<h1>FACTURA " + idFactura + "</h1>";
        String fecha = "<p>FECHA " + LocalDate.now() + "</p>";
        String cliente = "<p>CLIENTE " + nombreCliente + "</p>";
        String subTotal = "<p>SUBTOTAL " + dinero(centimosSubtotal) + "</p>";
        String impuestos = "<p>IMPUESTOS " + dinero(centimosImpuestos) + "</p>";
        String totalDinero = "<p>TOTAL " + dinero(centimosTotales) + "</p>";
        return factura + fecha + cliente + subTotal + impuestos + totalDinero;
    }

	// ============================= MÉTODOS ======================================
    // Método dinero que calcula el dinero en euros y lo devuelve en formato euros.CC€----------------------------------------------------------------
    private String dinero(int cents) {
        return String.format("%.2f€", cents / 100.0);
    }
}
