package cn.wode490390.mcbe.mvp;

import com.nukkitx.protocol.bedrock.BedrockClient;
import com.nukkitx.protocol.bedrock.BedrockServerSession;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerManager {

    private static final Map<BedrockServerSession, BedrockClient> players = new ConcurrentHashMap<>();

    public static BedrockClient newProxy(BedrockServerSession c2p) {
        BedrockClient client = new BedrockClient(new InetSocketAddress("0.0.0.0", ThreadLocalRandom.current().nextInt(20000, 60000)));
        players.put(c2p, client);
        client.bind().join();
        return client;
    }

    public static Map<BedrockServerSession, BedrockClient> getPlayers() {
        return players;
    }
}
