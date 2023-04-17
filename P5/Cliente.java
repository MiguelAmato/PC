import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class Cliente {
    private Socket socket;
    private String userName;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public Cliente(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception e) {
            System.out.println("Error en el constructor de cliente");
        }
    }

    public void startConection() {

    }

    public void sendMessage() {
        try {
            // bufferedWriter.write(userName + ": ");
            // bufferedWriter.flush();
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String mensaje = scanner.nextLine();
                bufferedWriter.write(userName + ": " + mensaje);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        catch (IOException e) {
            System.out.println("Error al enviar el mensaje");
        }
    }
    public static void main(String[] args) {
        try {
            System.out.println("Introduce tu nombre de usuario:");
            String userName = System.console().readLine();
            Socket socket = new Socket("localHost", 5555);
        }
        catch (IOException e) {

        }

    }
}