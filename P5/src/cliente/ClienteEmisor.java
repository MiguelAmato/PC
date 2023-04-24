package cliente;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteEmisor extends Thread {
	
	private Socket clientSocket;
	
	private Client emisor;
	
	String fileName;
	
	ObjectOutputStream out;
	
	public ClienteEmisor(Socket clientSocket, Client emisor, String fileName) {
		this.clientSocket = clientSocket;
		this.fileName = fileName;
		this.emisor = emisor;
		
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			File archivo = new File(emisor.getInfo().getDirectorio().getAbsolutePath() + "\\" + fileName);
			FileInputStream in = new FileInputStream(archivo);
			BufferedInputStream bin = new BufferedInputStream(in);
			
			byte[] buffer = new byte[4096];
			int aux;
			while ((aux= bin.read(buffer)) != -1) {
				out.write(buffer, 0, aux);
			}
			out.flush();
			bin.close();
			out.close();
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
