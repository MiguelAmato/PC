package mensajes;

import cliente.User;
import utils.TipoMensaje;

public class Conectar extends Mensaje {

	private static final long serialVersionUID = 1L;

	public Conectar(User user) {
		super(TipoMensaje.CONECTAR);
		this.user = user;
	}

	public TipoMensaje getTipo() {
		return TipoMensaje.CONECTAR;
	}

}
