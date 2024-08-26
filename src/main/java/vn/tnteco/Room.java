package vn.tnteco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    private String name;
    private List<ClientHandler> clients;

    public Room(String name) {
        this.name = name;
        this.clients = new ArrayList<>();
    }

    public Room(String name, ClientHandler clientHandler) {
        this.name = name;
        this.clients = Arrays.asList(clientHandler);
    }

    public String getRoomName() {
        return this.name;
    }


    public List<ClientHandler> getClientHandlers() {
        return this.clients;
    }

    public void addClientToRoom(List<ClientHandler> clientHandlers) {
        this.clients.addAll(clientHandlers);
    }

    public void removeClient(List<ClientHandler> clientHandlers) {
        this.clients.removeAll(clientHandlers);
    }
}
