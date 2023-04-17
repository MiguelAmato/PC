import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try {
            System.out.println("Servidor iniciado");
            while (!serverSocket.isClosed()) { 
                // Se espera a que un cliente se conecte.
                // Cuando un cliente se conecta, se crea un socket para
                // comunicarse con el cliente.
                // El método accept() establece la coneccion con el cliente
                Socket socket = serverSocket.accept();
                // Se crea un hilo para atender al cliente.
                ClientListener clientListener = new ClientListener(socket);
                // Se inicia el hilo.
                clientListener.start();
            }
        } catch (IOException ex) {
            System.out.println("Error al iniciar el servidor");
        }
    }

    public void closeServerSocket() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Error al cerrar el socket del servidor");
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            Server server = new Server(serverSocket);
            server.startServer();
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }


}

