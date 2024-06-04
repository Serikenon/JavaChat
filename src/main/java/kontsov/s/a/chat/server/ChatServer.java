package kontsov.s.a.chat.server;

import java.io.IOException;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerListener serverListener = new ServerListener();
        serverListener.start(27015);
    }
}
