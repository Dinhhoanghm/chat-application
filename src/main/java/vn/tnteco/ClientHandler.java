package vn.tnteco;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static vn.tnteco.RoomContext.*;
import static vn.tnteco.SocketContext.addClient;
import static vn.tnteco.SocketContext.remove;

@Data
@Accessors(chain = true)
public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;
    private String room;

    // Constructor
    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run method to handle client communication
    @Override
    public void run() {
        try {
            username = getUsernameInput();
            if (createRoomOrNot()) {
                room = getRoomInput();
                Room room = new Room(this.room);
                addRoom(room);
            } else {
                room = getRoomInput();
            }
            addClient(this);
            System.out.println("User " + this.username + " connected."); // Use Username consistently

            out.println("Welcome to the chat, " + this.username + "!"); // Use Username consistently
            out.println("Type Your Message");
            String inputLine;
            System.out.println(getRooms());
            while ((inputLine = in.readLine()) != null) {
                System.out.println("[" + this.username + "]: " + inputLine); // Use Username consistently
                broadcast("[" + this.username + "]: " + inputLine, this); // Use Username consistently
            }
            remove(this);
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUsernameInput() throws IOException {
        out.println("Enter your username:");
        return in.readLine();
    }

    private Boolean createRoomOrNot() throws IOException {
        out.println("Do you want to create a new room?");
        String confirm = in.readLine();
        if (confirm.equals("y")) {
            return true;
        }
        return false;
    }

    private String getRoomInput() throws IOException {
        out.println("Enter your room:");
        return in.readLine();
    }

    public void sendMessage(String message) {
        out.println(message);
        out.println("Type Your Message");
    }

    public void broadcast(String message, ClientHandler sender) {
        Room currentRoom = getRoomByName(this.room);
        System.out.println("[" + this.room + "]: " + message);
        List<ClientHandler> clients = currentRoom.getClientHandlers();
        System.out.println(clients.size());
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}

