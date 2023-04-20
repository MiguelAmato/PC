package mensajes;

import java.util.HashSet;
import java.util.Set;

import utils.TipoMensaje;

public class Confirmar extends Mensaje {

	private static final long serialVersionUID = 1L;
	
	private Set<String> listaConectados;

	public Confirmar(TipoMensaje tipo) {
		super(tipo);
	}

	public TipoMensaje getTipo() {
		return tipo;
	}
	
	public void listaConectados(Set<String> listaConectados) {
		this.listaConectados = new HashSet<String>(listaConectados);
	}
	
	public Set<String> getListaConectados() {
		return listaConectados;
	}

}
