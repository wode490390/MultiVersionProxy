package cn.wode490390.mcbe.mvp;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket.BlockPaletteEntry;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket.ItemEntry;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class RuntimePaletteManager {

    private static final TIntIntMap legacyToRuntimeId_v361 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v361 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v361 = new AtomicInteger(0);
    private static final ByteBuf compiledBlockPalette_v361 = PooledByteBufAllocator.DEFAULT.directBuffer();

    private static final TIntIntMap legacyToRuntimeId_v354 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v354 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v354 = new AtomicInteger(0);
    private static final ByteBuf compiledBlockPalette_v354 = PooledByteBufAllocator.DEFAULT.directBuffer();

    private static final TIntIntMap legacyToRuntimeId_v340 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v340 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v340 = new AtomicInteger(0);
    private static final ByteBuf compiledBlockPalette_v340 = PooledByteBufAllocator.DEFAULT.directBuffer();

    private static final TIntIntMap legacyToRuntimeId_v332 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v332 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v332 = new AtomicInteger(0);
    private static final ByteBuf compiledBlockPalette_v332 = PooledByteBufAllocator.DEFAULT.directBuffer();

    private static final TIntIntMap legacyToRuntimeId_v313 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v313 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v313 = new AtomicInteger(0);
    private static final ByteBuf compiledBlockPalette_v313 = PooledByteBufAllocator.DEFAULT.directBuffer();

    private static final TIntIntMap legacyToRuntimeId_v291 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v291 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v291 = new AtomicInteger(0);
    private static final ByteBuf compiledBlockPalette_v291 = PooledByteBufAllocator.DEFAULT.directBuffer();

    private static final List<ItemEntry> itemPalette_v361 = new ArrayList<>();

    static {
        List<BlockPaletteEntry> blockPalette_v361 = new ArrayList<>();
        ByteBuf compiler_v361 = Unpooled.buffer();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v361.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v361(id, meta);
            String identifier = String.valueOf(entry.get("name"));
            com.nukkitx.protocol.bedrock.v361.BedrockUtils.writeString(compiler_v361, identifier);
            compiler_v361.writeShortLE(meta);
            blockPalette_v361.add(new BlockPaletteEntry(identifier, meta, id));
        });
        VarInts.writeUnsignedInt(compiledBlockPalette_v361, blockPalette_v361.size());
        compiledBlockPalette_v361.writeBytes(compiler_v361);
        compiler_v361.release();

        List<BlockPaletteEntry> blockPalette_v354 = new ArrayList<>();
        ByteBuf compiler_v354 = Unpooled.buffer();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v354.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v354(id, meta);
            String identifier = String.valueOf(entry.get("name"));
            com.nukkitx.protocol.bedrock.v354.BedrockUtils.writeString(compiler_v354, identifier);
            compiler_v354.writeShortLE(meta);
            blockPalette_v354.add(new BlockPaletteEntry(identifier, meta, id));
        });
        VarInts.writeUnsignedInt(compiledBlockPalette_v354, blockPalette_v354.size());
        compiledBlockPalette_v354.writeBytes(compiler_v354);
        compiler_v354.release();

        List<BlockPaletteEntry> blockPalette_v340 = new ArrayList<>();
        ByteBuf compiler_v340 = Unpooled.buffer();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v340.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v340(id, meta);
            String identifier = String.valueOf(entry.get("name"));
            com.nukkitx.protocol.bedrock.v340.BedrockUtils.writeString(compiler_v340, identifier);
            compiler_v340.writeShortLE(meta);
            blockPalette_v340.add(new BlockPaletteEntry(identifier, meta, id));
        });
        VarInts.writeUnsignedInt(compiledBlockPalette_v340, blockPalette_v340.size());
        compiledBlockPalette_v340.writeBytes(compiler_v340);
        compiler_v340.release();

        List<BlockPaletteEntry> blockPalette_v332 = new ArrayList<>();
        ByteBuf compiler_v332 = Unpooled.buffer();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v332.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v332(id, meta);
            String identifier = String.valueOf(entry.get("name"));
            com.nukkitx.protocol.bedrock.v332.BedrockUtils.writeString(compiler_v332, identifier);
            compiler_v332.writeShortLE(meta);
            blockPalette_v332.add(new BlockPaletteEntry(identifier, meta, id));
        });
        VarInts.writeUnsignedInt(compiledBlockPalette_v332, blockPalette_v332.size());
        compiledBlockPalette_v332.writeBytes(compiler_v332);
        compiler_v332.release();

        List<BlockPaletteEntry> blockPalette_v313 = new ArrayList<>();
        ByteBuf compiler_v313 = Unpooled.buffer();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v313.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v313(id, meta);
            String identifier = String.valueOf(entry.get("name"));
            com.nukkitx.protocol.bedrock.v313.BedrockUtils.writeString(compiler_v313, identifier);
            compiler_v313.writeShortLE(meta);
            blockPalette_v313.add(new BlockPaletteEntry(identifier, meta, id));
        });
        VarInts.writeUnsignedInt(compiledBlockPalette_v313, blockPalette_v313.size());
        compiledBlockPalette_v313.writeBytes(compiler_v313);
        compiler_v313.release();

        List<BlockPaletteEntry> blockPalette_v291 = new ArrayList<>();
        ByteBuf compiler_v291 = Unpooled.buffer();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v282.json")).forEach(entry -> { //1.6-1.7
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v291(id, meta);
            String identifier = String.valueOf(entry.get("name"));
            com.nukkitx.protocol.bedrock.v291.BedrockUtils.writeString(compiler_v291, identifier);
            compiler_v291.writeShortLE(meta);
            blockPalette_v291.add(new BlockPaletteEntry(identifier, meta, id));
        });
        VarInts.writeUnsignedInt(compiledBlockPalette_v291, blockPalette_v291.size());
        compiledBlockPalette_v291.writeBytes(compiler_v291);
        compiler_v291.release();

        ((List<Map<String, Object>>) DedicatedData.loadArray("items_v361.json")).forEach(entry -> {
            itemPalette_v361.add(new ItemEntry(String.valueOf(entry.get("name")), ((Number) entry.get("id")).shortValue()));
        });
    }

    public static int getLegacyId_v361(int runtimeId) {
        return runtimeIdToLegacy_v361.get(runtimeId);
    }

    public static int getLegacyId_v354(int runtimeId) {
        return runtimeIdToLegacy_v354.get(runtimeId);
    }

    public static int getLegacyId_v340(int runtimeId) {
        return runtimeIdToLegacy_v340.get(runtimeId);
    }

    public static int getLegacyId_v332(int runtimeId) {
        return runtimeIdToLegacy_v332.get(runtimeId);
    }

    public static int getLegacyId_v313(int runtimeId) {
        return runtimeIdToLegacy_v313.get(runtimeId);
    }

    public static int getLegacyId_v291(int runtimeId) {
        return runtimeIdToLegacy_v291.get(runtimeId);
    }

    public static int getRuntimeId_v361(int blockId, int blockMeta) {
        return getRuntimeId_v361(getLegacyId(blockId, blockMeta));
    }

    public static int getRuntimeId_v354(int blockId, int blockMeta) {
        return getRuntimeId_v354(getLegacyId(blockId, blockMeta));
    }

    public static int getRuntimeId_v340(int blockId, int blockMeta) {
        return getRuntimeId_v340(getLegacyId(blockId, blockMeta));
    }

    public static int getRuntimeId_v332(int blockId, int blockMeta) {
        return getRuntimeId_v332(getLegacyId(blockId, blockMeta));
    }

    public static int getRuntimeId_v313(int blockId, int blockMeta) {
        return getRuntimeId_v313(getLegacyId(blockId, blockMeta));
    }

    public static int getRuntimeId_v291(int blockId, int blockMeta) {
        return getRuntimeId_v291(getLegacyId(blockId, blockMeta));
    }

    public static int getRuntimeId_v361(int legacyId) throws NoSuchElementException {
        //if (legacyToRuntimeId_v361.containsKey(legacyId)) {
            return legacyToRuntimeId_v361.get(legacyId);
        //}
        //throw new NoSuchElementException("Unmapped v361 block registered id: " + getBlockId(legacyId) + " meta: " + getBlockMeta(legacyId));
    }

    public static int getRuntimeId_v354(int legacyId) throws NoSuchElementException {
        //if (legacyToRuntimeId_v354.containsKey(legacyId)) {
            return legacyToRuntimeId_v354.get(legacyId);
        //}
        //throw new NoSuchElementException("Unmapped v354 block registered id: " + getBlockId(legacyId) + " meta: " + getBlockMeta(legacyId));
    }

    public static int getRuntimeId_v340(int legacyId) throws NoSuchElementException {
        //if (legacyToRuntimeId_v340.containsKey(legacyId)) {
            return legacyToRuntimeId_v340.get(legacyId);
        //}
        //throw new NoSuchElementException("Unmapped v340 block registered id: " + getBlockId(legacyId) + " meta: " + getBlockMeta(legacyId));
    }

    public static int getRuntimeId_v332(int legacyId) throws NoSuchElementException {
        //if (legacyToRuntimeId_v332.containsKey(legacyId)) {
            return legacyToRuntimeId_v332.get(legacyId);
        //}
        //throw new NoSuchElementException("Unmapped v332 block registered id: " + getBlockId(legacyId) + " meta: " + getBlockMeta(legacyId));
    }

    public static int getRuntimeId_v313(int legacyId) throws NoSuchElementException {
        //if (legacyToRuntimeId_v313.containsKey(legacyId)) {
            return legacyToRuntimeId_v313.get(legacyId);
        //}
        //throw new NoSuchElementException("Unmapped v313 block registered id: " + getBlockId(legacyId) + " meta: " + getBlockMeta(legacyId));
    }

    public static int getRuntimeId_v291(int legacyId) throws NoSuchElementException {
        //if (legacyToRuntimeId_v291.containsKey(legacyId)) {
            return legacyToRuntimeId_v291.get(legacyId);
        //}
        //throw new NoSuchElementException("Unmapped v291 block registered id: " + getBlockId(legacyId) + " meta: " + getBlockMeta(legacyId));
    }

    private static int registerMapping_v361(int blockId, int blockMeta) {
        int legacyId = getLegacyId(blockId, blockMeta);
        int runtimeId = runtimeIdAllocator_v361.getAndIncrement();
        runtimeIdToLegacy_v361.put(runtimeId, legacyId);
        legacyToRuntimeId_v361.put(legacyId, runtimeId);
        return runtimeId;
    }

    private static int registerMapping_v354(int blockId, int blockMeta) {
        int legacyId = getLegacyId(blockId, blockMeta);
        int runtimeId = runtimeIdAllocator_v354.getAndIncrement();
        runtimeIdToLegacy_v354.put(runtimeId, legacyId);
        legacyToRuntimeId_v354.put(legacyId, runtimeId);
        return runtimeId;
    }

    private static int registerMapping_v340(int blockId, int blockMeta) {
        int legacyId = getLegacyId(blockId, blockMeta);
        int runtimeId = runtimeIdAllocator_v340.getAndIncrement();
        runtimeIdToLegacy_v340.put(runtimeId, legacyId);
        legacyToRuntimeId_v340.put(legacyId, runtimeId);
        return runtimeId;
    }

    private static int registerMapping_v332(int blockId, int blockMeta) {
        int legacyId = getLegacyId(blockId, blockMeta);
        int runtimeId = runtimeIdAllocator_v332.getAndIncrement();
        runtimeIdToLegacy_v332.put(runtimeId, legacyId);
        legacyToRuntimeId_v332.put(legacyId, runtimeId);
        return runtimeId;
    }

    private static int registerMapping_v313(int blockId, int blockMeta) {
        int legacyId = getLegacyId(blockId, blockMeta);
        int runtimeId = runtimeIdAllocator_v313.getAndIncrement();
        runtimeIdToLegacy_v313.put(runtimeId, legacyId);
        legacyToRuntimeId_v313.put(legacyId, runtimeId);
        return runtimeId;
    }

    private static int registerMapping_v291(int blockId, int blockMeta) {
        int legacyId = getLegacyId(blockId, blockMeta);
        int runtimeId = runtimeIdAllocator_v291.getAndIncrement();
        runtimeIdToLegacy_v291.put(runtimeId, legacyId);
        legacyToRuntimeId_v291.put(legacyId, runtimeId);
        return runtimeId;
    }

    public static ByteBuf getBlockPalette_v361() {
        compiledBlockPalette_v361.retain();
        return compiledBlockPalette_v361;
    }

    public static ByteBuf getBlockPalette_v354() {
        compiledBlockPalette_v354.retain();
        return compiledBlockPalette_v354;
    }

    public static ByteBuf getBlockPalette_v340() {
        compiledBlockPalette_v340.retain();
        return compiledBlockPalette_v340;
    }

    public static ByteBuf getBlockPalette_v332() {
        compiledBlockPalette_v332.retain();
        return compiledBlockPalette_v332;
    }

    public static ByteBuf getBlockPalette_v313() {
        compiledBlockPalette_v313.retain();
        return compiledBlockPalette_v313;
    }

    public static ByteBuf getBlockPalette_v291() {
        compiledBlockPalette_v291.retain();
        return compiledBlockPalette_v291;
    }

    public static int getLegacyId(int blockId, int blockMeta) {
        return (blockId << 4) | blockMeta;
    }

    public static int getBlockId(int legacyId) {
        return legacyId >> 4;
    }

    public static int getBlockMeta(int legacyId) {
        return legacyId & 0xf;
    }

    public static List<ItemEntry> getItemPalette_v361() {
        return itemPalette_v361;
    }

    public static void init() {
        //nothing
    }
}
