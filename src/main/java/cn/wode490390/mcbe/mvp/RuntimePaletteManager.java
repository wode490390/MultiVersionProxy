package cn.wode490390.mcbe.mvp;

import com.nukkitx.nbt.CompoundTagBuilder;
import com.nukkitx.nbt.tag.CompoundTag;
import com.nukkitx.nbt.tag.ListTag;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket.ItemEntry;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class RuntimePaletteManager {

    private static final TIntIntMap legacyToRuntimeId_v388 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v388 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v388 = new AtomicInteger(0);
    private static final ListTag<CompoundTag> blockPalette_v388;

    private static final TIntIntMap legacyToRuntimeId_v361 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v361 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v361 = new AtomicInteger(0);
    private static final ListTag<CompoundTag> blockPalette_v361;

    private static final TIntIntMap legacyToRuntimeId_v354 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v354 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v354 = new AtomicInteger(0);
    private static final ListTag<CompoundTag> blockPalette_v354;

    private static final TIntIntMap legacyToRuntimeId_v340 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v340 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v340 = new AtomicInteger(0);
    private static final ListTag<CompoundTag> blockPalette_v340;

    private static final TIntIntMap legacyToRuntimeId_v332 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v332 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v332 = new AtomicInteger(0);
    private static final ListTag<CompoundTag> blockPalette_v332;

    private static final TIntIntMap legacyToRuntimeId_v313 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v313 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v313 = new AtomicInteger(0);
    private static final ListTag<CompoundTag> blockPalette_v313;

    private static final TIntIntMap legacyToRuntimeId_v291 = new TIntIntHashMap();
    private static final TIntIntMap runtimeIdToLegacy_v291 = new TIntIntHashMap();
    private static final AtomicInteger runtimeIdAllocator_v291 = new AtomicInteger(0);
    private static final ListTag<CompoundTag> blockPalette_v291;

    private static final List<ItemEntry> itemPalette_v388 = new ArrayList<>();
    private static final List<ItemEntry> itemPalette_v361 = new ArrayList<>();

    static {
        blockPalette_v388 = (ListTag<CompoundTag>) DedicatedData.loadNbt("blocks_v388.nbt");
        blockPalette_v388.getValue().forEach(entry -> {
            int runtimeId = runtimeIdAllocator_v388.getAndIncrement();
            if (entry.contains("meta")) {
                short id = entry.getAsShort("id");
                int[] meta = entry.getAsIntArray("meta");
                runtimeIdToLegacy_v388.put(runtimeId, getLegacyIdExpanded(id, meta[0]));
                for (int value : meta) {
                    legacyToRuntimeId_v388.put(getLegacyIdExpanded(id, value), runtimeId);
                }
            }
        });

        List<CompoundTag> blocks_v361 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v361.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v361(id, meta);
            blocks_v361.add(CompoundTagBuilder.builder()
                    .tag(CompoundTagBuilder.builder()
                            .stringTag("name", String.valueOf(entry.get("name")))
                            .build("block"))
                    .shortTag("id", id)
                    .shortTag("meta", meta)
                    .buildRootTag());
        });
        blockPalette_v361 = new ListTag<>("Palette", CompoundTag.class, blocks_v361);

        List<CompoundTag> blocks_v354 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v354.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v354(id, meta);
            blocks_v354.add(CompoundTagBuilder.builder()
                    .tag(CompoundTagBuilder.builder()
                            .stringTag("name", String.valueOf(entry.get("name")))
                            .build("block"))
                    .shortTag("meta", meta)
                    .buildRootTag());
        });
        blockPalette_v354 = new ListTag<>("Palette", CompoundTag.class, blocks_v354);

        List<CompoundTag> blocks_v340 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v340.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v340(id, meta);
            blocks_v340.add(CompoundTagBuilder.builder()
                    .tag(CompoundTagBuilder.builder()
                            .stringTag("name", String.valueOf(entry.get("name")))
                            .build("block"))
                    .shortTag("meta", meta)
                    .buildRootTag());
        });
        blockPalette_v340 = new ListTag<>("Palette", CompoundTag.class, blocks_v340);

        List<CompoundTag> blocks_v332 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v332.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v332(id, meta);
            blocks_v332.add(CompoundTagBuilder.builder()
                    .tag(CompoundTagBuilder.builder()
                            .stringTag("name", String.valueOf(entry.get("name")))
                            .build("block"))
                    .shortTag("meta", meta)
                    .buildRootTag());
        });
        blockPalette_v332 = new ListTag<>("Palette", CompoundTag.class, blocks_v332);

        List<CompoundTag> blocks_v313 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v313.json")).forEach(entry -> {
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v313(id, meta);
            blocks_v313.add(CompoundTagBuilder.builder()
                    .tag(CompoundTagBuilder.builder()
                            .stringTag("name", String.valueOf(entry.get("name")))
                            .build("block"))
                    .shortTag("meta", meta)
                    .buildRootTag());
        });
        blockPalette_v313 = new ListTag<>("Palette", CompoundTag.class, blocks_v313);

        List<CompoundTag> blocks_v291 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("blocks_v282.json")).forEach(entry -> { //1.6-1.7
            short id = ((Number) entry.get("id")).shortValue();
            short meta = ((Number) entry.get("data")).shortValue();
            registerMapping_v291(id, meta);
            blocks_v291.add(CompoundTagBuilder.builder()
                    .tag(CompoundTagBuilder.builder()
                            .stringTag("name", String.valueOf(entry.get("name")))
                            .build("block"))
                    .shortTag("meta", meta)
                    .buildRootTag());
        });
        blockPalette_v291 = new ListTag<>("Palette", CompoundTag.class, blocks_v291);

        ((List<Map<String, Object>>) DedicatedData.loadArray("items_v388.json")).forEach(entry -> {
            itemPalette_v388.add(new ItemEntry(String.valueOf(entry.get("name")), ((Number) entry.get("id")).shortValue()));
        });

        ((List<Map<String, Object>>) DedicatedData.loadArray("items_v361.json")).forEach(entry -> {
            itemPalette_v361.add(new ItemEntry(String.valueOf(entry.get("name")), ((Number) entry.get("id")).shortValue()));
        });
    }

    public static int getLegacyId_v388(int runtimeId) {
        return runtimeIdToLegacy_v388.get(runtimeId);
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

    public static int getRuntimeId_v388(int blockId, int blockMeta) {
        return getRuntimeId_v388(getLegacyIdExpanded(blockId, blockMeta));
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

    public static int getRuntimeId_v388(int legacyId) throws NoSuchElementException {
        //if (legacyToRuntimeId_v388.containsKey(legacyId)) {
            return legacyToRuntimeId_v388.get(legacyId);
        //}
        //throw new NoSuchElementException("Unmapped v388 block registered id: " + getBlockId(legacyId) + " meta: " + getBlockMeta(legacyId));
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

    public static ListTag<CompoundTag> getBlockPalette_v388() {
        return blockPalette_v388;
    }

    public static ListTag<CompoundTag> getBlockPalette_v361() {
        return blockPalette_v361;
    }

    public static ListTag<CompoundTag> getBlockPalette_v354() {
        return blockPalette_v354;
    }

    public static ListTag<CompoundTag> getBlockPalette_v340() {
        return blockPalette_v340;
    }

    public static ListTag<CompoundTag> getBlockPalette_v332() {
        return blockPalette_v332;
    }

    public static ListTag<CompoundTag> getBlockPalette_v313() {
        return blockPalette_v313;
    }

    public static ListTag<CompoundTag> getBlockPalette_v291() {
        return blockPalette_v291;
    }

    public static int getLegacyId(int blockId, int blockMeta) {
        return (blockId << 4) | blockMeta;
    }

    public static int getLegacyIdExpanded(int blockId, int blockMeta) {
        return (blockId << 6) | blockMeta;
    }

    public static int convertToLegacy(int expanded) {
        int meta = getBlockMetaExpanded(expanded);
        return getLegacyId(getBlockIdExpanded(expanded), meta > 0xf ? 0 : meta);
    }

    public static int getBlockId(int legacyId) {
        return legacyId >> 4;
    }

    public static int getBlockIdExpanded(int legacyId) {
        return legacyId >> 6;
    }

    public static int getBlockMeta(int legacyId) {
        return legacyId & 0xf;
    }

    public static int getBlockMetaExpanded(int legacyId) {
        return legacyId & 0x7f;
    }

    public static List<ItemEntry> getItemPalette_v388() {
        return itemPalette_v388;
    }

    public static List<ItemEntry> getItemPalette_v361() {
        return itemPalette_v361;
    }

    public static void init() {
        //NOOP
    }
}
