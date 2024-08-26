package vn.tnteco;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.net.Socket;

@Data
@Accessors(chain = true)
public class ChatMember extends Socket {
    private String username;
    private String room;

    public ChatMember(String username, String room, String host, int port) throws IOException {
        super(host, port);
        this.username = username;
        this.room = room;
    }

    public ChatMember(String username, String room) throws IOException {
        this.username = username;
        this.room = room;
    }
}
