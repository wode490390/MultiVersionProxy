package cn.wode490390.mcbe.mvp.network;

import cn.wode490390.mcbe.mvp.Main;
import cn.wode490390.mcbe.mvp.PlayerManager;
import com.nukkitx.protocol.bedrock.BedrockPong;
import com.nukkitx.protocol.bedrock.BedrockServerEventHandler;
import com.nukkitx.protocol.bedrock.BedrockServerSession;

import javax.annotation.Nonnull;
import java.net.InetSocketAddress;

public class ServerEventHandler implements BedrockServerEventHandler {

    private static final BedrockPong ADVERTISEMENT = new BedrockPong();

    static {
        ADVERTISEMENT.setEdition("MCPE");
        ADVERTISEMENT.setGameType("Survival");
        ADVERTISEMENT.setVersion(Main.MINECRAFT_VERSION);
        ADVERTISEMENT.setProtocolVersion(Main.PROTOCOL_VERSION);
        ADVERTISEMENT.setMotd(Main.getInstance().getConfig().getMotd());
        ADVERTISEMENT.setMaximumPlayerCount(Main.getInstance().getConfig().getMaxPlayer());
        ADVERTISEMENT.setSubMotd(Main.getInstance().getConfig().getSubMotd());
    }

    @Override
    public boolean onConnectionRequest(InetSocketAddress address) {
        return true;
    }

    @Nonnull
    @Override
    public BedrockPong onQuery(InetSocketAddress address) {
        ADVERTISEMENT.setPlayerCount(PlayerManager.getPlayers().size());
        return ADVERTISEMENT;
    }

    @Override
    public void onSessionCreation(BedrockServerSession session) {
       session.setPacketHandler(new ServerPacketHandler(session));
    }
}
