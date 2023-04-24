package mensajes;

import utils.TipoMensaje;

public class UsuarioRepetido extends Mensaje{

	public UsuarioRepetido() {
		super(TipoMensaje.USUARIO_REPETIDO);
	}

	@Override
	public TipoMensaje getTipo() {
		return tipo;
	}

}
