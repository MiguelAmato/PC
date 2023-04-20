package cliente;

import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String nombre;
	InetAddress ip;
	String directorio;
	List<File> listaArchivos;
	
	public User(String nombre, String directorio) {
		this.nombre = nombre;
		this.directorio = directorio;
		try {
			ip = InetAddress.getLocalHost();
			//this.ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		listaArchivos = new ArrayList<File>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public String getDirectorio() {
		return directorio;
	}

	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}

	public List<File> getListaArchivos() {
		return listaArchivos;
	}

	public void setListaArchivos(List<File> listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	
	
}
