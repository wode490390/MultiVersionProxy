package cn.wode490390.mcbe.mvp.network;

import cn.wode490390.mcbe.mvp.Main;
import cn.wode490390.mcbe.mvp.PlayerManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.SignedJWT;
import com.nukkitx.protocol.bedrock.BedrockServerSession;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.*;
import com.nukkitx.protocol.bedrock.util.EncryptionUtils;
import com.nukkitx.protocol.bedrock.v291.Bedrock_v291;
import com.nukkitx.protocol.bedrock.v313.Bedrock_v313;
import com.nukkitx.protocol.bedrock.v332.Bedrock_v332;
import com.nukkitx.protocol.bedrock.v340.Bedrock_v340;
import com.nukkitx.protocol.bedrock.v354.Bedrock_v354;
import com.nukkitx.protocol.bedrock.v361.Bedrock_v361;
import io.netty.util.AsciiString;
import java.io.IOException;
import java.security.interfaces.ECPublicKey;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;

@Log4j2
public class ServerPacketHandler implements BedrockPacketHandler {

    private final BedrockServerSession c2p;
    private ProxySession session;

    public ServerPacketHandler(BedrockServerSession c2p) {
        this.c2p = c2p;
    }

    @Override
    public boolean handle(AdventureSettingsPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AnimatePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(BlockEntityDataPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(BlockPickRequestPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(BookEditPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(BossEventPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ClientCacheBlobStatusPacket packet) { //1.12+
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ClientCacheStatusPacket packet) { //1.12+
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ClientToServerHandshakePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServerImmediately(packet);
        }
        return true;
    }

    @Override
    public boolean handle(CommandBlockUpdatePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(CommandRequestPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ContainerClosePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(CraftingEventPacket packet) { //TODO: item
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(DisconnectPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServerImmediately(packet);
        }
        return true;
    }

    @Override
    public boolean handle(EntityEventPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(EntityFallPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(EntityPickRequestPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(InteractPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(InventoryTransactionPacket packet) { //TODO: item
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LabTablePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LecternUpdatePacket packet) { //1.10+
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LevelSoundEvent2Packet packet) { //1.8+
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LevelSoundEvent3Packet packet) { //1.9+
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LevelSoundEventPacket packet) { //TODO
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LoginPacket packet) {
        int protocol = packet.getProtocolVersion();
        log.info("{} logged in with protocol version {}", c2p.getAddress(), protocol);
        switch (protocol) {
            case 361:
                c2p.setPacketCodec(Bedrock_v361.V361_CODEC);
                c2p.sendPacket(PacketHelper.getPlayStatusPacket0());
                c2p.sendPacket(PacketHelper.getResourcePacksInfoPacket());
                return true;
            case 354:
                c2p.setPacketCodec(Bedrock_v354.V354_CODEC);
                break;
            case 340:
                c2p.setPacketCodec(Bedrock_v340.V340_CODEC);
                break;
            case 332:
                c2p.setPacketCodec(Bedrock_v332.V332_CODEC);
                break;
            case 313:
                c2p.setPacketCodec(Bedrock_v313.V313_CODEC);
                break;
            case 291:
                c2p.setPacketCodec(Bedrock_v291.V291_CODEC);
                break;
            default:
                c2p.setPacketCodec(Main.CODEC);
                PlayStatusPacket status = new PlayStatusPacket();
                if (protocol > Main.PROTOCOL_VERSION) {
                    status.setStatus(PlayStatusPacket.Status.FAILED_SERVER);
                } else {
                    status.setStatus(PlayStatusPacket.Status.FAILED_CLIENT);
                }
                c2p.sendPacket(status);
                return true;
        }

        JsonNode certData;
        try {
            certData = Main.JSON_MAPPER.readTree(packet.getChainData().toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Certificate JSON can not be read.");
        }

        JsonNode certChainData = certData.get("chain");
        if (certChainData.getNodeType() != JsonNodeType.ARRAY) {
            throw new RuntimeException("Certificate data is not valid");
        }
        ArrayNode chainData = (ArrayNode) certChainData;

        boolean validChain;
        try {
            validChain = PacketHelper.validateChainData(certChainData);

            log.debug("Is player data valid? {}", validChain);
            JWSObject jwt = JWSObject.parse(certChainData.get(certChainData.size() - 1).asText());
            JsonNode payload = Main.JSON_MAPPER.readTree(jwt.getPayload().toBytes());

            if (payload.get("extraData").getNodeType() != JsonNodeType.OBJECT) {
                throw new RuntimeException("AuthData was not found!");
            }

            JSONObject extraData = (JSONObject) jwt.getPayload().toJSONObject().get("extraData");

            String displayName = extraData.getAsString("displayName");
            UUID identity = UUID.fromString(extraData.getAsString("identity"));
            String xuid = extraData.getAsString("XUID");

            if (payload.get("identityPublicKey").getNodeType() != JsonNodeType.STRING) {
                throw new RuntimeException("Identity Public Key was not found!");
            }
            ECPublicKey identityPublicKey = EncryptionUtils.generateKey(payload.get("identityPublicKey").textValue());

            JWSObject clientJwt = JWSObject.parse(packet.getSkinData().toString());
            PacketHelper.verifyJwt(clientJwt, identityPublicKey);
            JSONObject skinData = clientJwt.getPayload().toJSONObject();

            PlayerManager.newProxy(c2p).connect(Main.getInstance().getTargetAddress()).whenComplete((p2s, throwable) -> {
                if (throwable != null) {
                    log.error("Unable to connect to target server", throwable);
                    return;
                }

                p2s.setPacketCodec(Main.CODEC);
                session = new ProxySession(c2p, p2s);
                session.setProtocol(protocol);

                session.displayName = displayName;
                session.identity = identity;
                session.xuid = xuid;

                p2s.setPacketHandler(new ClientPacketHandler(p2s, session));

                SignedJWT forgedAuthData = PacketHelper.forgeAuthData(session.getKeyPair(), extraData);
                JWSObject forgedSkinData = PacketHelper.forgeSkinData(session.getKeyPair(), skinData);
                chainData.remove(chainData.size() - 1);
                chainData.add(forgedAuthData.serialize());
                JsonNode json = Main.JSON_MAPPER.createObjectNode().set("chain", chainData);
                AsciiString forgedChainData;
                try {
                    forgedChainData = new AsciiString(Main.JSON_MAPPER.writeValueAsBytes(json));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                LoginPacket login = new LoginPacket();
                login.setChainData(forgedChainData);
                login.setSkinData(AsciiString.of(forgedSkinData.serialize()));
                login.setProtocolVersion(Main.PROTOCOL_VERSION);
                p2s.sendPacketImmediately(login);
            });
        } catch (Exception e) {
            c2p.disconnect("disconnectionScreen.internalError.cantConnect");
            throw new RuntimeException("Unable to complete login", e);
        }
        return true;
    }

    @Override
    public boolean handle(MapCreateLockedCopyPacket packet) { //1.11+
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MapInfoRequestPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MobArmorEquipmentPacket packet) { //TODO: item
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MobEquipmentPacket packet) { //TODO: item
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ModalFormResponsePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MoveEntityAbsolutePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MovePlayerPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(NetworkStackLatencyPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(NpcRequestPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PlayerActionPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PlayerHotbarPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PlayerInputPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PlayerSkinPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PurchaseReceiptPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(RequestChunkRadiusPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ResourcePackChunkRequestPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ResourcePackClientResponsePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() == Main.PROTOCOL_VERSION) {
            switch (packet.getStatus()) {
                case HAVE_ALL_PACKS:
                    c2p.sendPacket(PacketHelper.getResourcePackStackPacket());
                    break;
                case COMPLETED:
                    c2p.sendPacket(PacketHelper.getStartGamePacket());
                    c2p.sendPacket(PacketHelper.getPlayStatusPacket3());
                    break;
            }
        } else {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(RiderJumpPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ServerSettingsRequestPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetDefaultGameTypePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetDifficultyPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetEntityDataPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetLocalPlayerAsInitializedPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() == Main.PROTOCOL_VERSION) {
            log.info("{} joined the game", c2p.getAddress());
            c2p.sendPacket(PacketHelper.createTransferPacket());
        } else {
            log.info("{}[{}] joined the game", session.displayName, c2p.getAddress());
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetPlayerGameTypePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ShowCreditsPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SimpleEventPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SpawnExperienceOrbPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(StructureBlockUpdatePacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(StructureTemplateDataExportRequestPacket packet) { //1.12+
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SubClientLoginPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServerImmediately(packet);
        }
        return true;
    }

    @Override
    public boolean handle(TextPacket packet) {
        if (c2p.getPacketCodec().getProtocolVersion() != Main.PROTOCOL_VERSION) {
            session.sendPacketToServer(packet);
        }
        return true;
    }
}
