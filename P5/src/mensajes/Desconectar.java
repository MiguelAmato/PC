package mensajes;

import utils.TipoMensaje;

public class Desconectar extends Mensaje {
	
	private static final long serialVersionUID = 1L;

	public Desconectar() {
		super(TipoMensaje.DESCONECTAR);
	}

	public TipoMensaje getTipo() {
		return TipoMensaje.DESCONECTAR;
	}

}
