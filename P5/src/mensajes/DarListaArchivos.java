package mensajes;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import utils.TipoMensaje;

public class DarListaArchivos extends Mensaje {
	private static final long serialVersionUID = 1L;
	
	private Set<String> listaArchivos;

	public DarListaArchivos(Set<String> listaArchivos) {
		super(TipoMensaje.DAR_LISTA_ARCHIVOS);
		this.listaArchivos = new HashSet<String>(listaArchivos);
	}

	public TipoMensaje getTipo() {
		return tipo;
	}
	
	/*
	public void listaConectados(Set<String> listaConectados) {
		this.listaConectados = new HashSet<String>(listaConectados);
	}*/
	
	public Set<String> getListaArchivos() {
		return listaArchivos;
	}
}
