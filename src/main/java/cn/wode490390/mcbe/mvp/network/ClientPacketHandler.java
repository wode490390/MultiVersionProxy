package cn.wode490390.mcbe.mvp.network;

import cn.wode490390.mcbe.mvp.InventoryManager;
import cn.wode490390.mcbe.mvp.RuntimePaletteManager;
import com.nimbusds.jwt.SignedJWT;
import com.nukkitx.nbt.NbtUtils;
import com.nukkitx.nbt.stream.NBTInputStream;
import com.nukkitx.nbt.stream.NBTOutputStream;
import com.nukkitx.nbt.tag.Tag;
import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockClientSession;
import com.nukkitx.protocol.bedrock.data.ContainerId;
import com.nukkitx.protocol.bedrock.data.ContainerMixData;
import com.nukkitx.protocol.bedrock.data.CraftingData;
import com.nukkitx.protocol.bedrock.data.PotionMixData;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.*;
import com.nukkitx.protocol.bedrock.util.EncryptionUtils;
import com.nukkitx.protocol.bedrock.v291.Bedrock_v291;
import com.nukkitx.protocol.bedrock.v313.Bedrock_v313;
import com.nukkitx.protocol.bedrock.v332.Bedrock_v332;
import com.nukkitx.protocol.bedrock.v340.Bedrock_v340;
import com.nukkitx.protocol.bedrock.v354.Bedrock_v354;
import com.nukkitx.protocol.bedrock.v361.Bedrock_v361;
import com.nukkitx.protocol.bedrock.v388.Bedrock_v388;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.SecretKey;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClientPacketHandler implements BedrockPacketHandler {

    private final BedrockClientSession p2s;
    private final ProxySession session;

    public ClientPacketHandler(BedrockClientSession p2s, ProxySession session) {
        this.p2s = p2s;
        this.session = session;
    }

    @Override
    public boolean handle(AddBehaviorTreePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AddEntityPacket packet) {
        if (session.getProtocol() == Bedrock_v291.V291_CODEC.getProtocolVersion()) {
            if (PacketHelper.ENTITY_ID_V291.containsKey(packet.getIdentifier())) {
                packet.setEntityType(PacketHelper.ENTITY_ID_V291.get(packet.getIdentifier()));
            } else {
                return true;
            }
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AddItemEntityPacket packet) { //TODO: item
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AddPaintingPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AddPlayerPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AdventureSettingsPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AnimatePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AutomationClientConnectPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AvailableCommandsPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(AvailableEntityIdentifiersPacket packet) {
        switch (session.getProtocol()) {
            case 361:
                session.sendPacketToClient(packet);
                //packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V361);
                return true;
            case 354:
                packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V354);
                break;
            case 340:
                packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V340);
                break;
            case 332:
                packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V332);
                break;
            case 313:
                packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V313);
                break;
            default:
                return true;
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(BiomeDefinitionListPacket packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }/* else if (false) {
            packet.setTag(PacketHelper.BIOME_DEFINITION_LIST_V340);
            session.sendPacketToClient(packet);
        }*/
        return true;
    }

    @Override
    public boolean handle(BlockEntityDataPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(BlockEventPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(BossEventPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(CameraPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ChangeDimensionPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ChunkRadiusUpdatedPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ClientboundMapItemDataPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ClientCacheMissResponsePacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(CommandOutputPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(CompletedUsingItemPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ContainerClosePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ContainerOpenPacket packet) {
        if (packet.getType() <= 23) { //LAB_TABLE
            session.sendPacketToClient(packet);
        } else if (session.getProtocol() >= Bedrock_v354.V354_CODEC.getProtocolVersion()) { //27-29 1.11+
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ContainerSetDataPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(CraftingDataPacket packet) {
        List<CraftingData> craftingData = packet.getCraftingData();
        List<PotionMixData> potionData = packet.getPotionMixData();
        List<ContainerMixData> containerData = packet.getContainerMixData();
        craftingData.clear();
        potionData.clear();
        containerData.clear();
        switch (session.getProtocol()) {
            case 388:
                craftingData.addAll(InventoryManager.getRecipes_v388());
                potionData.addAll(InventoryManager.getPotions_v388());
                containerData.addAll(InventoryManager.getContainers_v388());
                break;
            case 361:
                craftingData.addAll(InventoryManager.getRecipes_v361());
                break;
            case 354:
                craftingData.addAll(InventoryManager.getRecipes_v354());
                break;
            case 340:
                craftingData.addAll(InventoryManager.getRecipes_v340());
                break;
            case 332:
                craftingData.addAll(InventoryManager.getRecipes_v332());
                break;
            case 313:
                craftingData.addAll(InventoryManager.getRecipes_v313());
                break;
            case 291:
                craftingData.addAll(InventoryManager.getRecipes_v291());
                break;
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(DisconnectPacket packet) {
        session.sendPacketToClientImmediately(packet);
        p2s.disconnect();
        return true;
    }

    @Override
    public boolean handle(EducationSettingsPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(EmotePacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(EntityEventPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(EventPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ExplodePacket packet) { //-1.12
        if (session.getProtocol() < Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(GameRulesChangedPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(GuiDataPickItemPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(HurtArmorPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(InventoryContentPacket packet) { //TODO: item
        if (packet.getContainerId() == ContainerId.CREATIVE) {
            switch (session.getProtocol()) {
                case 388:
                    packet.setContents(InventoryManager.getCreative_v388());
                    break;
                case 361:
                    packet.setContents(InventoryManager.getCreative_v361());
                    break;
                case 354:
                    packet.setContents(InventoryManager.getCreative_v354());
                    break;
                case 340:
                    packet.setContents(InventoryManager.getCreative_v340());
                    break;
                case 332:
                    packet.setContents(InventoryManager.getCreative_v332());
                    break;
                case 313:
                    packet.setContents(InventoryManager.getCreative_v313());
                    break;
                case 291:
                    packet.setContents(InventoryManager.getCreative_v291());
                    break;
            }
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(InventorySlotPacket packet) { //TODO: item
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(InventoryTransactionPacket packet) { //TODO: item
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(LabTablePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(LevelChunkPacket packet) {
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
            return true;
        }
        ByteBuf data = Unpooled.wrappedBuffer(packet.getData());
        ByteBuf fixer = Unpooled.buffer();
        int count = packet.getSubChunksLength();
        fixer.writeByte(count);
        byte[][] sections = new byte[count][];
        for (int i = 0; i < count; i++) {
            fixer.writeByte(data.readByte());
            byte[] ids = new byte[4096];
            sections[i] = ids;
            data.readBytes(ids);
            fixer.writeBytes(ids);
            byte[] meta = new byte[2048];
            data.readBytes(meta);
            fixer.writeBytes(meta);
        }
        if (count > 0) {
            byte[] heightMap = new byte[256];
            for (int x = 0; x < 16; ++x) {
                int xi = x << 8;
                for (int z = 0; z < 16; ++z) {
                    int zi = z << 4;
                    int xz = xi + zi;
                    y:
                    for (int i = count - 1; i >= 0; --i) {
                        byte[] section = sections[i];
                        for (int y = 15; y >= 0; --y) {
                            if (section[xz + y] != 0) {
                                heightMap[(z << 4) | x] = (byte) ((i << 4) + y);
                                break y;
                            }
                        }
                    }
                }
            }
            fixer.writeBytes(heightMap);
        } else {
            fixer.writeBytes(PacketHelper.PAD_256); //height map
        }
        fixer.writeBytes(PacketHelper.PAD_256);
        byte[] biome = new byte[256]; //16 * 16
        data.readBytes(biome);
        fixer.writeBytes(biome);
        fixer.writeByte(data.readByte()); //border block array count
        //Border block entry format: 1 byte (4 bits X, 4 bits Z). These are however useless since they crash the regular client.
        VarInts.writeInt(fixer, VarInts.readInt(data));
        if (data.readableBytes() > 0) {
            List<Tag<?>> tiles = new ArrayList<>();
            try (NBTInputStream reader = NbtUtils.createNetworkReader(new ByteBufInputStream(data))) {
                while (data.readableBytes() > 0) {
                    tiles.add(reader.readTag());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try (NBTOutputStream writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(fixer))) {
                for (Tag<?> tile : tiles) {
                    writer.write(tile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        byte[] fixed = new byte[fixer.readableBytes()];
        fixer.readBytes(fixed);
        packet.setData(fixed);
        session.sendPacketToClient(packet);
        data.release();
        fixer.release();
        return true;
    }

    @Override
    public boolean handle(LevelEventGenericPacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LevelEventPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(LevelSoundEvent2Packet packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion() && packet.getSound().ordinal() <= 0xff) {
            session.sendPacketToClient(packet);
        } else if (packet.getSound().ordinal() <= 214) {
            switch (packet.getSound().ordinal()) {
                case 151:
                case 187: //SOUND_BLOCK_FLETCHING_TABLE_USE 1.11+
                case 195:
                case 196:
                case 197:
                case 198:
                    return true;
            }
            LevelSoundEventPacket v1 = new LevelSoundEventPacket();
            v1.setSound(packet.getSound());
            v1.setPosition(packet.getPosition());
            v1.setExtraData(packet.getExtraData());
            v1.setPitch(1);
            v1.setBabySound(packet.isBabySound());
            v1.setRelativeVolumeDisabled(packet.isRelativeVolumeDisabled());
            this.handle(v1);
        }
        return true;
    }

    @Override
    public boolean handle(LevelSoundEvent3Packet packet) { //1.9+
        if (session.getProtocol() >= Bedrock_v332.V332_CODEC.getProtocolVersion()) {
            switch (session.getProtocol()) {
                case 361: //1.12
                    if (packet.getSound().ordinal() > 281) {
                        return true;
                    }
                    break;
                case 354: //1.11
                    if (packet.getSound().ordinal() > 274) {
                        return true;
                    }
                    break;
                case 340: //1.10
                case 332: //1.9
                    if (packet.getSound().ordinal() > 0xff || packet.getSound().ordinal() == 187) { //SOUND_BLOCK_FLETCHING_TABLE_USE
                        return true;
                    }
                    break;
            }
            session.sendPacketToClient(packet);
        } else if (packet.getSound().ordinal() <= 0xff) { //TODO: NullPointerException???
            LevelSoundEvent2Packet v2 = new LevelSoundEvent2Packet();
            v2.setSound(packet.getSound());
            v2.setPosition(packet.getPosition());
            v2.setExtraData(packet.getExtraData());
            v2.setIdentifier(packet.getIdentifier());
            v2.setBabySound(packet.isBabySound());
            v2.setRelativeVolumeDisabled(packet.isRelativeVolumeDisabled());
            this.handle(v2);
        }
        return true;
    }

    @Override
    public boolean handle(LevelSoundEventPacket packet) {
        if (packet.getSound().ordinal() <= 0xff) {
            if ((packet.getSound().ordinal() > 214 || packet.getSound().ordinal() == 187) && session.getProtocol() < Bedrock_v313.V313_CODEC.getProtocolVersion()) { //SOUND_BLOCK_FLETCHING_TABLE_USE
                return true;
            }
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MobArmorEquipmentPacket packet) { //TODO: item
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(MobEffectPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(MobEquipmentPacket packet) { //TODO: item
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ModalFormRequestPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(MoveEntityAbsolutePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(MoveEntityDeltaPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(MovePlayerPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override //TODO: this might be clientbound too, but unsure
    public boolean handle(MultiplayerSettingsPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(NetworkChunkPublisherUpdatePacket packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(NetworkSettingsPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(NetworkStackLatencyPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(OnScreenTextureAnimationPacket packet) { //1.11+
        if (session.getProtocol() >= Bedrock_v354.V354_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PhotoTransferPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(PlayerHotbarPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(PlayerListPacket packet) {
        if (session.getProtocol() < Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            packet.getEntries().forEach(entry -> {
                entry.setSkin(PacketHelper.checkLegacySkin(entry.getSkin()));
            });
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(PlayerSkinPacket packet) {
        if (session.getProtocol() < Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            packet.setSkin(PacketHelper.checkLegacySkin(packet.getSkin()));
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(PlaySoundPacket packet) { //TODO: check?
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(PlayStatusPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(RemoveEntityPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(RemoveObjectivePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ResourcePackChunkDataPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ResourcePackDataInfoPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ResourcePacksInfoPacket packet) {
        session.sendPacketToClient(packet);
        if (session.getProtocol() < Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            p2s.sendPacket(PacketHelper.getClientCacheStatusPacket0());
        }
        return true;
    }

    @Override
    public boolean handle(ResourcePackStackPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(RespawnPacket packet) {
        if (session.getProtocol() < Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            switch (packet.getSpawnState()) {
                case SERVER_SEARCHING:
                    RespawnPacket res = new RespawnPacket();
                    res.setPosition(packet.getPosition());
                    res.setSpawnState(RespawnPacket.State.CLIENT_READY);
                    res.setRuntimeEntityId(packet.getRuntimeEntityId());
                    p2s.sendPacket(res);
                    break;
                case SERVER_READY:
                    session.sendPacketToClient(packet);
                    break;
            }
        } else {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ServerSettingsResponsePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ServerToClientHandshakePacket packet) {
        try {
            SignedJWT saltJwt = SignedJWT.parse(packet.getJwt());
            URI x5u = saltJwt.getHeader().getX509CertURL();
            ECPublicKey serverKey = EncryptionUtils.generateKey(x5u.toASCIIString());
            SecretKey key = EncryptionUtils.getSecretKey(session.getKeyPair().getPrivate(), serverKey, Base64.getDecoder().decode(saltJwt.getJWTClaimsSet().getStringClaim("salt")));
            p2s.enableEncryption(key);
        } catch (ParseException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        p2s.sendPacketImmediately(PacketHelper.getClientToServerHandshakePacket());
        return true;
    }

    @Override
    public boolean handle(SetCommandsEnabledPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetDefaultGameTypePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetDifficultyPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetDisplayObjectivePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetEntityDataPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetEntityLinkPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetEntityMotionPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetHealthPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetLastHurtByPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetPlayerGameTypePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetScoreboardIdentityPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetScorePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetSpawnPositionPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetTimePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetTitlePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ShowCreditsPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ShowProfilePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(ShowStoreOfferPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SimpleEventPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SpawnParticleEffectPacket packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(StartGamePacket packet) {
        switch (session.getProtocol()) {
            case 388:
                session.sendPacketToClient(packet);
                return true;
            case 361:
                packet.setItemEntries(RuntimePaletteManager.getItemPalette_v361());
                packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v361());
                break;
            case 354:
                packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v354());
                break;
            case 340:
                packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v340());
                break;
            case 332:
                packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v332());
                break;
            case 313:
                packet.setTrustingPlayers(false);
                packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v313());
                break;
            case 291:
                packet.setTrustingPlayers(false);
                packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v291());
                break;
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(StopSoundPacket packet) { //TODO: check?
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(StructureTemplateDataExportResponsePacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(TakeItemEntityPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(TextPacket packet) {
        if (packet.getType().ordinal() == 9 && session.getProtocol() < Bedrock_v332.V332_CODEC.getProtocolVersion()) { //TextPacket.Type.JSON 1.9+
            packet.setType(TextPacket.Type.RAW);
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(TickSyncPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(TransferPacket packet) {
        session.sendPacketToClientImmediately(packet);
        return true;
    }

    @Override
    public boolean handle(UpdateAttributesPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(UpdateBlockPacket packet) {
        switch (session.getProtocol()) {
            case 388:
                //packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v388(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId())));
                break;
            case 361:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v361(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 354:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v354(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 340:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v340(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 332:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v332(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 313:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v313(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 291:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v291(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            default:
                return true;
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(UpdateBlockPropertiesPacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }

    @Override
    public boolean handle(UpdateBlockSyncedPacket packet) {
        switch (session.getProtocol()) {
            case 388:
                //packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v388(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId())));
                break;
            case 361:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v361(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 354:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v354(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 340:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v340(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 332:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v332(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 313:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v313(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            case 291:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v291(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v388(packet.getRuntimeId()))));
                break;
            default:
                return true;
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(UpdateEquipPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(UpdateSoftEnumPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(UpdateTradePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(VideoStreamConnectPacket packet) { //1.10+
        if (session.getProtocol() >= Bedrock_v340.V340_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(packet);
        }
        return true;
    }
}
