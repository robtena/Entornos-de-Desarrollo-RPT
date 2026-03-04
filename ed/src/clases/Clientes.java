package clases;

public class Clientes {
	//Atributos--------------------------------
	private final String idCliente;
    private final String nombre;
    private final boolean vip;

    //Constructor ---------------------------------------
    public Clientes(String idCliente, String nombre, boolean vip) {
        if (idCliente == null || idCliente.isBlank()) throw new IllegalArgumentException("idCliente");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre");
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.vip = vip;
    }

    //Getters--------------------------------------------
    public String getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public boolean esVip() { return vip; }
}
