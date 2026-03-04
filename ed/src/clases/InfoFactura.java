package clases;

import java.time.LocalDate;

public class InfoFactura {
	
	//Esta clase no tiene atributos
	
	// Constructor de una factura en tipo text------------------------------------------------------------------------------------------------------
    public String infoFacturaTexto(String idFactura, String nombreCliente, int centimosSubtotal, int centimosImpuestos, int centimosTotales) {
        String a = "FACTURA " + idFactura + "\n";
        String b = "FECHA " + LocalDate.now() + "\n";
        String c = "CLIENTE " + nombreCliente + "\n";
        String d = "SUBTOTAL " + dinero(centimosSubtotal) + "\n";
        String e = "IMPUESTOS " + dinero(centimosImpuestos) + "\n";
        String f = "TOTAL " + dinero(centimosTotales) + "\n";
        return a + b + c + d + e + f;
    }

    //Constructor de una factura en tipo html ---------------------------------------------------------------------------------------------------
    public String infoFacturaHtml(String idFactura, String nombreCliente, int centimosSubtotal, int centimosImpuestos, int centimosTotales) {       
        String a = "<h1>FACTURA " + idFactura + "</h1>";
        String b = "<p>FECHA " + LocalDate.now() + "</p>";
        String c = "<p>CLIENTE " + nombreCliente + "</p>";
        String d = "<p>SUBTOTAL " + dinero(centimosSubtotal) + "</p>";
        String e = "<p>IMPUESTOS " + dinero(centimosImpuestos) + "</p>";
        String f = "<p>TOTAL " + dinero(centimosTotales) + "</p>";
        return a + b + c + d + e + f;
    }

	// ============================= MÉTODOS ======================================
    // Método dinero que calcula el dinero en euros y lo devuelve en formato euros.CC€----------------------------------------------------------------
    private String dinero(int cents) {
        return String.format("%.2f€", cents / 100.0);
    }
}
