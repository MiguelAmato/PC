package mensajes;

import java.util.HashSet;
import java.util.Set;

import utils.TipoMensaje;

public class DarListaClientes extends Mensaje {
	
	private static final long serialVersionUID = 1L;
	
	private Set<String> listaConectados;

	public DarListaClientes(Set<String> listaConectados) {
		super(TipoMensaje.DAR_LISTA_CLIENTES);
		this.listaConectados = new HashSet<String>(listaConectados);
	}

	public TipoMensaje getTipo() {
		return tipo;
	}
	
	/*
	public void listaConectados(Set<String> listaConectados) {
		this.listaConectados = new HashSet<String>(listaConectados);
	}*/
	
	public Set<String> getListaConectados() {
		return listaConectados;
	}


}
