package vn.tnteco;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Accessors(chain = true)
@Configuration
public class SocketCommon {
    @Value("${spring.socket.server}")
    private String server;
    @Value("${spring.socket.port}")
    private Integer port;

}
