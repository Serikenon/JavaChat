package kontsov.s.a.chat.server;

import java.io.IOException;
import java.util.ArrayList;

public class ChatLog {
    private final ArrayList<String> chatHistory;
    private int pointer = 0;

    public ChatLog(){
        chatHistory = new ArrayList<>();
    }

    public void put(String message, ClientHandler clientSender) throws IOException {
        chatHistory.add(message);
        System.out.println(message);
        update(clientSender);
        pointer++;
    }

    private void update(ClientHandler clientSender) throws IOException {
        for (ClientHandler client : ServerListener.getClients()){
            if(client != clientSender)
                client.sendMessageToClient(chatHistory.get(pointer));
        }
    }

}
