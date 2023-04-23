package mensajes;

import utils.TipoMensaje;

public class FalloConexionP2P extends Mensaje{

	private static final long serialVersionUID = 1L;

	public FalloConexionP2P() {
		super(TipoMensaje.FALLO_CONEXION_P2P);
	}

	@Override
	public TipoMensaje getTipo() {
		return null;
	}

}
