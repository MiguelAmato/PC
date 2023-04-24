package mensajes;

import cliente.User;
import utils.TipoMensaje;

public class Desconectar extends Mensaje {
	
	private static final long serialVersionUID = 1L;

	
	public Desconectar(User user) {
		super(TipoMensaje.DESCONECTAR);
		this.user = user;
	}

	public TipoMensaje getTipo() {
		return TipoMensaje.DESCONECTAR;
	}
	
	

}
