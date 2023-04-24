package cliente;

import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;



public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String nombre;
	InetAddress ip;
	File directorio;
	Set<String> listaArchivos;
	
	public User(String nombre, File directorio) {
		this.nombre = nombre;
		this.directorio = directorio;
		try {
			this.ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		listaArchivos = new HashSet<String>();
		for(File archivo : directorio.listFiles()) 
			if (!archivo.isDirectory()) 
				listaArchivos.add(archivo.getName());
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

	public File getDirectorio() {
		return directorio;
	}

	public void setDirectorio(File directorio) {
		this.directorio = directorio;
	}

	public Set<String> getListaArchivos() {
		return listaArchivos;
	}

	public void setListaArchivos(Set<String> listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	
	
}
