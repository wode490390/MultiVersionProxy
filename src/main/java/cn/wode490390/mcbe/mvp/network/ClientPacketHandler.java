package cn.wode490390.mcbe.mvp.network;

import cn.wode490390.mcbe.mvp.InventoryManager;
import cn.wode490390.mcbe.mvp.RuntimePaletteManager;
import cn.wode490390.mcbe.mvp.util.BitArray;
import cn.wode490390.mcbe.mvp.util.BitArrayVersion;
import com.nimbusds.jwt.SignedJWT;
import com.nukkitx.nbt.NbtUtils;
import com.nukkitx.nbt.stream.NBTInputStream;
import com.nukkitx.nbt.stream.NBTOutputStream;
import com.nukkitx.nbt.tag.Tag;
import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockClientSession;
import com.nukkitx.protocol.bedrock.BedrockPacket;
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
import com.nukkitx.protocol.bedrock.v389.Bedrock_v389;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
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

@Log4j2
public class ClientPacketHandler implements BedrockPacketHandler { //TODO: fix player movement 1.7-1.10

    private final BedrockClientSession p2s;
    private final ProxySession session;

    private boolean gameStarted = false; // Nukkit bug...
    private final BedrockPacket[] orderedPackets = new BedrockPacket[3]; // StartGamePacket + BiomeDefinitionListPacket + AvailableEntityIdentifiersPacket
    private final List<BedrockPacket> waiting = new ObjectArrayList<>();

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
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AddItemEntityPacket packet) { //TODO: item
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AddPaintingPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AddPlayerPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AdventureSettingsPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AnimatePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AutomationClientConnectPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AvailableCommandsPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(AvailableEntityIdentifiersPacket packet) {
        switch (session.getProtocol()) {
            case 390:
            case 389:
                //packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V389);
                break;
            case 388:
                packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V388);
                break;
            case 361:
                packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V361);
                break;
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
        if (!this.gameStarted) {
            this.orderedPackets[2] = packet;
            for (BedrockPacket pk : this.orderedPackets) {
                if (pk == null) {
                    return true;
                }
            }
            session.sendStartGame(this.orderedPackets);
            this.gameStarted = true;
            this.waiting.forEach(session::sendPacketToClient);
        }
        return true;
    }

    @Override
    public boolean handle(BiomeDefinitionListPacket packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion()) {
            switch (session.getProtocol()) {
                case 390:
                case 389:
                case 388:
                    packet.setTag(PacketHelper.BIOME_DEFINITION_LIST_V388);
                    break;
                case 361:
                case 354:
                case 340:
                    packet.setTag(PacketHelper.BIOME_DEFINITION_LIST_V340);
                    break;
                case 332:
                case 313:
                    packet.setTag(PacketHelper.ENTITY_IDENTIFIERS_V313);
                    break;
                default:
                    return true;
            }
            if (!this.gameStarted) {
                this.orderedPackets[1] = packet;
                for (BedrockPacket pk : this.orderedPackets) {
                    if (pk == null) {
                        return true;
                    }
                }
                session.sendStartGame(this.orderedPackets);
                this.gameStarted = true;
                this.waiting.forEach(session::sendPacketToClient);
            }
        }
        return true;
    }

    @Override
    public boolean handle(BlockEntityDataPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(BlockEventPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(BossEventPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(CameraPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ChangeDimensionPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ChunkRadiusUpdatedPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ClientboundMapItemDataPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ClientCacheMissResponsePacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(CommandOutputPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(CompletedUsingItemPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(ContainerClosePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ContainerOpenPacket packet) {
        if (packet.getType() <= 23) { //LAB_TABLE
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        } else if (session.getProtocol() >= Bedrock_v354.V354_CODEC.getProtocolVersion()) { //27-29 1.11+
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(ContainerSetDataPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(CraftingDataPacket packet) { //TODO: fix
        List<CraftingData> craftingData = packet.getCraftingData();
        List<PotionMixData> potionData = packet.getPotionMixData();
        List<ContainerMixData> containerData = packet.getContainerMixData();
        switch (session.getProtocol()) {
            case 390:
            case 389:
                //craftingData.clear();
                //potionData.clear();
                //containerData.clear();
                //craftingData.addAll(InventoryManager.getRecipes_v389());
                //potionData.addAll(InventoryManager.getPotions_v389());
                //containerData.addAll(InventoryManager.getContainers_v389());
                break;
            case 388:
                craftingData.clear();
                potionData.clear();
                containerData.clear();
                craftingData.addAll(InventoryManager.getRecipes_v388());
                potionData.addAll(InventoryManager.getPotions_v388());
                containerData.addAll(InventoryManager.getContainers_v388());
                break;
            case 361:
                craftingData.clear();
                potionData.clear();
                containerData.clear();
                craftingData.addAll(InventoryManager.getRecipes_v361());
                break;
            case 354:
                craftingData.clear();
                potionData.clear();
                containerData.clear();
                craftingData.addAll(InventoryManager.getRecipes_v354());
                break;
            case 340:
                craftingData.clear();
                potionData.clear();
                containerData.clear();
                craftingData.addAll(InventoryManager.getRecipes_v340());
                break;
            case 332:
                craftingData.clear();
                potionData.clear();
                containerData.clear();
                craftingData.addAll(InventoryManager.getRecipes_v332());
                break;
            case 313:
                craftingData.clear();
                potionData.clear();
                containerData.clear();
                craftingData.addAll(InventoryManager.getRecipes_v313());
                break;
            case 291:
                craftingData.clear();
                potionData.clear();
                containerData.clear();
                craftingData.addAll(InventoryManager.getRecipes_v291());
                break;
        }
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
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
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(EmotePacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(EntityEventPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(EventPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ExplodePacket packet) { //-1.12
        if (session.getProtocol() < Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(GameRulesChangedPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(GuiDataPickItemPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(HurtArmorPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(InventoryContentPacket packet) { //TODO: item
        if (packet.getContainerId() == ContainerId.CREATIVE) {
            switch (session.getProtocol()) {
                case 390:
                case 389:
                    //packet.setContents(InventoryManager.getCreative_v389());
                    break;
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
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(InventorySlotPacket packet) { //TODO: item
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(InventoryTransactionPacket packet) { //TODO: item
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LabTablePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LevelChunkPacket packet) {
        if (session.getProtocol() >= Bedrock_v389.V389_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
            return true;
        }
        ByteBuf data = Unpooled.wrappedBuffer(packet.getData());
        ByteBuf fixer = Unpooled.buffer();
        int count = packet.getSubChunksLength();
        if (session.getProtocol() < Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            fixer.writeByte(count);
        }
        BitArray[] sections = new BitArray[count];
        for (int i = 0; i < count; i++) {
            fixer.writeByte(data.readByte()); // version: 8
            byte storageCount = data.readByte(); //2
            fixer.writeByte(storageCount);
            for (int j = 0; j < storageCount; j++) {
                byte header = data.readByte();
                fixer.writeByte(header);
                int bit = header >> 1;
                BitArrayVersion version = BitArrayVersion.get(bit, true);
                int expectedWordCount = version.getWordsForSize(4096);
                boolean isFirst = j == 0;
                int[] words = isFirst ? new int[expectedWordCount] : null;
                for (int k = 0; k < expectedWordCount; k++) {
                    int value = data.readIntLE();
                    if (isFirst) {
                        words[k] = value;
                    }
                    fixer.writeIntLE(value);
                }
                if (isFirst) {
                    sections[i] = version.createPalette(4096, words);
                }
                int paletteSize = VarInts.readInt(data);
                VarInts.writeInt(fixer, paletteSize);
                for (int l = 0; l < paletteSize; l++) {
                    int runtimeId = VarInts.readInt(data);
                    switch (session.getProtocol()) {
                        case 390:
                        case 389:
                            //runtimeId = RuntimePaletteManager.getRuntimeId_v389(RuntimePaletteManager.getLegacyId_v389(runtimeId));
                            break;
                        case 388:
                            runtimeId = RuntimePaletteManager.getRuntimeId_v388(RuntimePaletteManager.getLegacyId_v389(runtimeId));
                            break;
                        case 361:
                            runtimeId = RuntimePaletteManager.getRuntimeId_v361(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(runtimeId)));
                            break;
                        case 354:
                            runtimeId = RuntimePaletteManager.getRuntimeId_v354(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(runtimeId)));
                            break;
                        case 340:
                            runtimeId = RuntimePaletteManager.getRuntimeId_v340(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(runtimeId)));
                            break;
                        case 332:
                            runtimeId = RuntimePaletteManager.getRuntimeId_v332(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(runtimeId)));
                            break;
                        case 313:
                            runtimeId = RuntimePaletteManager.getRuntimeId_v313(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(runtimeId)));
                            break;
                        case 291:
                            runtimeId = RuntimePaletteManager.getRuntimeId_v291(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(runtimeId)));
                            break;
                    }
                    VarInts.writeInt(fixer, runtimeId);
                }
            }
        }
        if (session.getProtocol() < Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            if (count > 0) {
                byte[] heightMap = new byte[256];
                for (int x = 0; x < 16; ++x) {
                    int xi = x << 8;
                    for (int z = 0; z < 16; ++z) {
                        int zi = z << 4;
                        int xz = xi + zi;
                        y:
                        for (int i = count - 1; i >= 0; --i) {
                            BitArray section = sections[i];
                            for (int y = 15; y >= 0; --y) {
                                if (section.get(xz + y) != 0) {
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
        }
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
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        data.release();
        fixer.release();
        return true;
    }

    @Override
    public boolean handle(LevelEventGenericPacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(LevelEventPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(LevelSoundEvent2Packet packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion() && packet.getSound().ordinal() <= 0xff) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
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
            LevelSoundEvent1Packet v1 = new LevelSoundEvent1Packet();
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
    public boolean handle(LevelSoundEventPacket packet) { //1.9+
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
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
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
    public boolean handle(LevelSoundEvent1Packet packet) {
        if (packet.getSound().ordinal() <= 0xff) {
            if ((packet.getSound().ordinal() > 214 || packet.getSound().ordinal() == 187) && session.getProtocol() < Bedrock_v313.V313_CODEC.getProtocolVersion()) { //SOUND_BLOCK_FLETCHING_TABLE_USE
                return true;
            }
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(MobArmorEquipmentPacket packet) { //TODO: item
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MobEffectPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MobEquipmentPacket packet) { //TODO: item
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ModalFormRequestPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MoveEntityAbsolutePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MoveEntityDeltaPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MovePlayerPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(MultiplayerSettingsPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(NetworkChunkPublisherUpdatePacket packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(NetworkSettingsPacket packet) { //1.13+
        //if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
        //  if (this.gameStarted) {
        //    session.sendPacketToClient(packet);
        //  } else {
        //    this.waiting.add(packet);
        //  }
        //}
        return true;
    }

    @Override
    public boolean handle(NetworkStackLatencyPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(OnScreenTextureAnimationPacket packet) { //1.11+
        if (session.getProtocol() >= Bedrock_v354.V354_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(PhotoTransferPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PlayerHotbarPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
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
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PlaySoundPacket packet) { //TODO: check?
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(PlayStatusPacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(RemoveEntityPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(RemoveObjectivePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
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
        } else if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            session.sendPacketToClient(PacketHelper.getNetworkSettingsPacket1());
        }
        return true;
    }

    @Override
    public boolean handle(ResourcePackStackPacket packet) {
        switch (session.getProtocol()) {
            case 390:
                packet.setGameVersion("1.14.60");
                break;
            case 389:
                packet.setGameVersion("1.14.0");
                break;
            case 388:
                packet.setGameVersion("1.13.0");
                break;
        }
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(RespawnPacket packet) {
        if (session.getProtocol() < Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            switch (packet.getState()) {
                case SERVER_SEARCHING:
                    RespawnPacket res = new RespawnPacket();
                    res.setPosition(packet.getPosition());
                    res.setState(RespawnPacket.State.CLIENT_READY);
                    res.setRuntimeEntityId(packet.getRuntimeEntityId());
                    p2s.sendPacket(res);
                    break;
                case SERVER_READY:
                    if (this.gameStarted) {
                        session.sendPacketToClient(packet);
                    } else {
                        this.waiting.add(packet);
                    }
                    break;
            }
        } else {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(ServerSettingsResponsePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
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
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetDefaultGameTypePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetDifficultyPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetDisplayObjectivePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetEntityDataPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetEntityLinkPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetEntityMotionPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetHealthPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetLastHurtByPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetPlayerGameTypePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetScoreboardIdentityPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetScorePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetSpawnPositionPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SetTimePacket packet) {
        session.sendPacketToClient(packet);
        return true;
    }

    @Override
    public boolean handle(SetTitlePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ShowCreditsPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ShowProfilePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(ShowStoreOfferPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SimpleEventPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(SpawnParticleEffectPacket packet) { //1.8+
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(StartGamePacket packet) {
        switch (session.getProtocol()) {
            case 390:
                packet.setVanillaVersion("1.14.60"); //...
            case 389:
                packet.setVanillaVersion("1.14.0");
                //packet.setItemEntries(RuntimePaletteManager.getItemPalette_v389());
                //packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v389());
                break;
            case 388:
                packet.setVanillaVersion("1.13.0");
                packet.setItemEntries(RuntimePaletteManager.getItemPalette_v388());
                packet.setBlockPalette(RuntimePaletteManager.getBlockPalette_v388());
                break;
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
        if (session.getProtocol() >= Bedrock_v313.V313_CODEC.getProtocolVersion()) {
            this.orderedPackets[0] = packet;
            for (BedrockPacket pk : this.orderedPackets) {
                if (pk == null) {
                    return true;
                }
            }
            session.sendStartGame(this.orderedPackets);
        } else {
            session.sendPacketToClientImmediately(packet);
        }
        this.gameStarted = true;
        this.waiting.forEach(session::sendPacketToClient);
        return true;
    }

    @Override
    public boolean handle(StopSoundPacket packet) { //TODO: check?
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(StructureTemplateDataExportResponsePacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(TakeItemEntityPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(TextPacket packet) {
        if (packet.getType().ordinal() == 9 && session.getProtocol() < Bedrock_v332.V332_CODEC.getProtocolVersion()) { //TextPacket.Type.JSON 1.9+
            packet.setType(TextPacket.Type.RAW);
        }
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(TickSyncPacket packet) { //1.13+
        if (session.getProtocol() >= Bedrock_v388.V388_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(TransferPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClientImmediately(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(UpdateAttributesPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(UpdateBlockPacket packet) {
        switch (session.getProtocol()) {
            case 390:
            case 389:
                //packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v389(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId())));
                break;
            case 388:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v388(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId())));
                break;
            case 361:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v361(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 354:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v354(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 340:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v340(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 332:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v332(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 313:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v313(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 291:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v291(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            default:
                return true;
        }
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(UpdateBlockPropertiesPacket packet) { //1.12+
        if (session.getProtocol() >= Bedrock_v361.V361_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }

    @Override
    public boolean handle(UpdateBlockSyncedPacket packet) {
        switch (session.getProtocol()) {
            case 390:
            case 389:
                //packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v389(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId())));
                break;
            case 388:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v388(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId())));
                break;
            case 361:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v361(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 354:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v354(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 340:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v340(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 332:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v332(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 313:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v313(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            case 291:
                packet.setRuntimeId(RuntimePaletteManager.getRuntimeId_v291(RuntimePaletteManager.convertToLegacy(RuntimePaletteManager.getLegacyId_v389(packet.getRuntimeId()))));
                break;
            default:
                return true;
        }
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(UpdateEquipPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(UpdateSoftEnumPacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(UpdateTradePacket packet) {
        if (this.gameStarted) {
            session.sendPacketToClient(packet);
        } else {
            this.waiting.add(packet);
        }
        return true;
    }

    @Override
    public boolean handle(VideoStreamConnectPacket packet) { //1.10+
        if (session.getProtocol() >= Bedrock_v340.V340_CODEC.getProtocolVersion()) {
            if (this.gameStarted) {
                session.sendPacketToClient(packet);
            } else {
                this.waiting.add(packet);
            }
        }
        return true;
    }
}
