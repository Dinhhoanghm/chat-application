package vn.tnteco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static vn.tnteco.SeverContext.broadcast;
import static vn.tnteco.SeverContext.remove;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String Username; // Use Username consistently

    // Constructor
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run method to handle client communication
    @Override
    public void run() {
        try {
            Username = getUsername(); // Use Username consistently
            System.out.println("User " + Username + " connected."); // Use Username consistently

            out.println("Welcome to the chat, " + Username + "!"); // Use Username consistently
            out.println("Type Your Message");
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("[" + Username + "]: " + inputLine); // Use Username consistently
                broadcast("[" + Username + "]: " + inputLine, this); // Use Username consistently
            }
            remove(this);
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the username from the client
    private String getUsername() throws IOException {
        out.println("Enter your username:");
        return in.readLine();
    }

    public void sendMessage(String message) {
        out.println(message);
        out.println("Type Your Message");
    }
}

