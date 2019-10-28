package cn.wode490390.mcbe.mvp.network;

import cn.wode490390.mcbe.mvp.PlayerManager;
import com.nukkitx.network.util.DisconnectReason;
import com.nukkitx.protocol.bedrock.BedrockClientSession;
import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.BedrockServerSession;
import com.nukkitx.protocol.bedrock.util.EncryptionUtils;
import java.security.KeyPair;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProxySession {

    private final BedrockServerSession c2p;
    private final BedrockClientSession p2s;
    private final KeyPair keyPair = EncryptionUtils.createKeyPair();

    private int protocol;

    public String displayName;
    public UUID identity;
    public String xuid;

    public ProxySession(BedrockServerSession c2p, BedrockClientSession p2s) {
        this.c2p = c2p;
        this.p2s = p2s;
        p2s.addDisconnectHandler(reason -> {
            try {
                c2p.disconnect(reason.toString().toLowerCase());
                PlayerManager.getPlayers().remove(c2p);
            } catch (Exception ignore) {

            }
        });
        c2p.addDisconnectHandler(reason -> {
            try {
                if (reason != DisconnectReason.DISCONNECTED) {
                    p2s.disconnect();
                }
                PlayerManager.getPlayers().remove(c2p);
                log.info("{}[{}] logged out due to {}", displayName, c2p.getAddress(), reason.toString().toLowerCase());
            } catch (Exception e) {
                log.info("{} logged out", displayName);
            }
        });
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getProtocol() {
        return protocol;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void sendPacketToClient(@Nonnull BedrockPacket packet) {
        c2p.sendPacket(packet);
    }

    public void sendPacketToServer(@Nonnull BedrockPacket packet) {
        p2s.sendPacket(packet);
    }

    public void sendPacketToClientImmediately(@Nonnull BedrockPacket packet) {
        c2p.sendPacketImmediately(packet);
    }

    public void sendPacketToServerImmediately(@Nonnull BedrockPacket packet) {
        p2s.sendPacketImmediately(packet);
    }
}
