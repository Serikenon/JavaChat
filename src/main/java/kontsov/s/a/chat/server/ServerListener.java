package kontsov.s.a.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListener {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        ChatLog log = new ChatLog();
        while (true) {
            Socket incomingConnection = serverSocket.accept();
            ClientHandler client = new ClientHandler(incomingConnection, new ChatLog());
            executorService.execute(client);
        }
    }

    private static ArrayList<ClientHandler> clients;

    public ServerListener(){
        clients = new ArrayList<>();
    }

    public static List<ClientHandler> getClients(){
        return clients;
    }

    public static void removeClient(ClientHandler clientHandler){
        System.out.println("Client "+clientHandler+" is deleted");
        clients.remove(clientHandler);
    }
}
