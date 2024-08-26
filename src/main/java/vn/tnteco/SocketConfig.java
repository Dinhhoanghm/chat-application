package vn.tnteco;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Configuration
public class SocketConfig {
    private final SocketCommon socketCommon;


    public SocketConfig(SocketCommon socketCommon) {
        this.socketCommon = socketCommon;
    }


    @SneakyThrows
    @PostConstruct
    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(socketCommon.getPort());
            System.out.println("Server is running and waiting for connections..");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
