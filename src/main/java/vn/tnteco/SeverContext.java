package vn.tnteco;

import java.util.concurrent.CopyOnWriteArrayList;

public class SeverContext {
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void addClients(ClientHandler client) {
        clients.add(client);
    }

    public static void remove(ClientHandler client) {
        clients.remove(client);
    }

}
