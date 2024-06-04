package kontsov.s.a.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private final String serverAddress;
    private final int serverPort;

    public ChatClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter your nickname: ");
            String nickName = scanner.nextLine();
            out.write(nickName);
            out.newLine();
            out.flush();

            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            String message;
            while (!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
                out.write(message);
                out.newLine();
                out.flush();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient("localhost", 27015);
        client.start();
    }
}
