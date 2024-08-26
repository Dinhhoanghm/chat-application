package vn.tnteco;

import java.util.Arrays;

import static vn.tnteco.RoomContext.getRoomByName;

public class SocketContext {

    public static void addClient(ClientHandler client) {
        Room currentRoom = getRoomByName(client.getRoom());
        System.out.println(currentRoom);
        currentRoom.addClientToRoom(Arrays.asList(client));
    }

    public static void remove(ClientHandler client) {
        Room currentRoom = getRoomByName(client.getRoom());
        currentRoom.removeClient(Arrays.asList(client));
    }

}
