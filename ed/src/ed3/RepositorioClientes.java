package ed3;

import java.util.HashMap;
import java.util.Map;

public class RepositorioClientes {
	//Atributo map 
	private final Map<String, Clientes> clientes = new HashMap<>();

	// Creación del repositorio---------------------------------------------------
	public RepositorioClientes() {
	   clientes.put("C-001", new Clientes("C-001", "Lucia", true));
	   clientes.put("C-002", new Clientes("C-002", "Mario", false));
	   clientes.put("C-003", new Clientes("C-003", "Sara", false));
	}

	// ============================= MÉTODOS ======================================
	
	//Método que busca clientes por su ID------------------------------------------
	public Clientes buscarId(String id) {
	    return clientes.get(id);
	}
}
