package mensajes;

import java.io.Serializable;

import cliente.User;
import utils.TipoMensaje;

public abstract class Mensaje implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	TipoMensaje tipo;
	
	User user = null;
	
	public Mensaje(TipoMensaje tipo) {
		this.tipo = tipo;
	}
	
	public abstract TipoMensaje getTipo();
	
	public User getOrigen() {
		return user;
	}
}
