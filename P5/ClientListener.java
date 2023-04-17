import java.io.IOException;
import java.net.Socket;

public class ClientListener extends Thread {

    private Socket socket;

    public ClientListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}

