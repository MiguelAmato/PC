package cliente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClienteReceptor extends Thread{

	private Socket socket;
	
	String file;
	
	private Client client;
	
	private ObjectInputStream in;
	
	public ClienteReceptor(Socket socket, String file, Client client) {
		this.socket = socket;
		this.client = client;
		this.file = file;
		try {
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(!socket.isClosed()) {
			try {
				byte[] buffer = new byte[4096];
				int aux = 0;
				FileOutputStream fos = new FileOutputStream(client.getInfo().getDirectorio().getAbsolutePath() + "\\" + file);
				while ((aux = in.read(buffer)) != -1) {
					fos.write(buffer, 0, aux);
				}
				fos.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
