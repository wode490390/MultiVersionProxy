package cn.wode490390.mcbe.mvp;

import cn.wode490390.mcbe.mvp.network.PacketHelper;
import com.nukkitx.nbt.tag.CompoundTag;
import com.nukkitx.protocol.bedrock.data.ContainerMixData;
import com.nukkitx.protocol.bedrock.data.CraftingData;
import com.nukkitx.protocol.bedrock.data.CraftingType;
import com.nukkitx.protocol.bedrock.data.ItemData;
import com.nukkitx.protocol.bedrock.data.PotionMixData;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InventoryManager {

    private static final ItemData[] creative_v389;
    private static final ItemData[] creative_v388;
    private static final ItemData[] creative_v361;
    private static final ItemData[] creative_v354;
    private static final ItemData[] creative_v340;
    private static final ItemData[] creative_v332;
    private static final ItemData[] creative_v313;
    private static final ItemData[] creative_v291;

    private static final List<CraftingData> recipes_v389 = new ArrayList<>();
    private static final List<CraftingData> recipes_v388 = new ArrayList<>();
    private static final List<CraftingData> recipes_v361 = new ArrayList<>();
    private static final List<CraftingData> recipes_v354 = new ArrayList<>();
    private static final List<CraftingData> recipes_v340 = new ArrayList<>();
    private static final List<CraftingData> recipes_v332 = new ArrayList<>();
    private static final List<CraftingData> recipes_v313 = new ArrayList<>();
    private static final List<CraftingData> recipes_v291 = new ArrayList<>();

    private static final List<PotionMixData> potions_v389 = new ArrayList<>();
    private static final List<PotionMixData> potions_v388 = new ArrayList<>();

    private static final List<ContainerMixData> containers_v389 = new ArrayList<>();
    private static final List<ContainerMixData> containers_v388 = new ArrayList<>();

    static {
        List<ItemData> creativeItems_v389 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v389.json")).forEach(entry -> {
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v389.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v389.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v389 = creativeItems_v389.toArray(new ItemData[0]);

        List<ItemData> creativeItems_v388 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v388.json")).forEach(entry -> {
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v388.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v388.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v388 = creativeItems_v388.toArray(new ItemData[0]);

        List<ItemData> creativeItems_v361 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v361.json")).forEach(entry -> {
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v361.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v361.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v361 = creativeItems_v361.toArray(new ItemData[0]);

        List<ItemData> creativeItems_v354 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v354.json")).forEach(entry -> {
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v354.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v354.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v354 = creativeItems_v354.toArray(new ItemData[0]);

        List<ItemData> creativeItems_v340 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v340.json")).forEach(entry -> {
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v340.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v340.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v340 = creativeItems_v340.toArray(new ItemData[0]);

        List<ItemData> creativeItems_v332 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v332.json")).forEach(entry -> {
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v332.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v332.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v332 = creativeItems_v332.toArray(new ItemData[0]);

        List<ItemData> creativeItems_v313 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v313.json")).forEach(entry -> {
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v313.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v313.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v313 = creativeItems_v313.toArray(new ItemData[0]);

        List<ItemData> creativeItems_v291 = new ArrayList<>();
        ((List<Map<String, Object>>) DedicatedData.loadArray("creative_v282.json")).forEach(entry -> { //1.6-1.7
            int id = ((Number) entry.get("id")).intValue();
            short meta;
            Number damage = (Number) entry.get("damage");
            if (damage != null) {
                meta = damage.shortValue();
            } else {
                meta = 0;
            }
            Object nbt_b64 = entry.get("nbt_b64");
            if (nbt_b64 != null) {
                creativeItems_v291.add(ItemData.of(id, meta, 1, (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64)))));
            } else {
                creativeItems_v291.add(ItemData.of(id, meta, 1));
            }
        });
        creative_v291 = creativeItems_v291.toArray(new ItemData[0]);

        Map<String, List<Map<String, Object>>> recipeData_v389 = (Map<String, List<Map<String, Object>>>) DedicatedData.loadMap("recipes_v389.json");
        for (Map<String, Object> entry : recipeData_v389.get("recipes")) {
            int type = ((Number) entry.get("type")).intValue();
            CraftingData data = null;
            switch (type) {
                case 0:
                //case 5:
                    String craftingTag0 = String.valueOf(entry.get("block"));
                    if (!craftingTag0.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    int priority0 = ((Number) entry.get("priority")).intValue();
                    String recipeId0 = String.valueOf(entry.get("id"));
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case 0:
                            data = CraftingData.fromShapeless(recipeId0, inputs0, outputs0, uuid0, craftingTag0, priority0);
                            break;
                        case 5:
                            data = CraftingData.fromShulkerBox(recipeId0, inputs0, outputs0, uuid0, craftingTag0, priority0);
                            break;
                    }
                    break;
                case 1:
                    String craftingTag1 = String.valueOf(entry.get("block"));
                    if (!craftingTag1.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    int priority1 = ((Number) entry.get("priority")).intValue();
                    String recipeId1 = String.valueOf(entry.get("id"));
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    data = CraftingData.fromShaped(recipeId1, width, height, inputs1, outputs1, uuid1, craftingTag1, priority1);
                    break;
                case 3:
                    String craftingTag2 = String.valueOf(entry.get("block"));
                    if (!craftingTag2.equalsIgnoreCase("furnace")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number count = (Number) outputEntry2.get("count");
                    Object nbt_b64 = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null);
                    Map<String, Object> inputEntry2 = (Map<String, Object>) entry.get("input");
                    int inputId = ((Number) inputEntry2.get("id")).intValue();
                    Number inputDamage = (Number) inputEntry2.get("damage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, craftingTag2);
                    } else {
                        data = fromFurnace(inputId, output, craftingTag2);
                    }
                    break;
                //case 4:
                //    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                //    break;
                default:
                    continue;
            }
            recipes_v389.add(data);
        }
        recipeData_v389.get("potionMixes").forEach(entry -> potions_v389.add(new PotionMixData(((Number) entry.get("fromPotionId")).intValue(), ((Number) entry.get("ingredient")).intValue(), ((Number) entry.get("toPotionId")).intValue())));
        recipeData_v389.get("containerMixes").forEach(entry -> containers_v389.add(new ContainerMixData(((Number) entry.get("fromItemId")).intValue(), ((Number) entry.get("ingredient")).intValue(), ((Number) entry.get("toItemId")).intValue())));

        Map<String, List<Map<String, Object>>> recipeData_v388 = (Map<String, List<Map<String, Object>>>) DedicatedData.loadMap("recipes_v388.json");
        for (Map<String, Object> entry : recipeData_v388.get("recipes")) {
            int type = ((Number) entry.get("type")).intValue();
            CraftingData data = null;
            switch (type) {
                case 0:
                //case 5:
                    String craftingTag0 = String.valueOf(entry.get("block"));
                    if (!craftingTag0.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    int priority0 = ((Number) entry.get("priority")).intValue();
                    String recipeId0 = String.valueOf(entry.get("id"));
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case 0:
                            data = CraftingData.fromShapeless(recipeId0, inputs0, outputs0, uuid0, craftingTag0, priority0);
                            break;
                        case 5:
                            data = CraftingData.fromShulkerBox(recipeId0, inputs0, outputs0, uuid0, craftingTag0, priority0);
                            break;
                    }
                    break;
                case 1:
                    String craftingTag1 = String.valueOf(entry.get("block"));
                    if (!craftingTag1.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    int priority1 = ((Number) entry.get("priority")).intValue();
                    String recipeId1 = String.valueOf(entry.get("id"));
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    data = CraftingData.fromShaped(recipeId1, width, height, inputs1, outputs1, uuid1, craftingTag1, priority1);
                    break;
                case 3:
                    String craftingTag2 = String.valueOf(entry.get("block"));
                    if (!craftingTag2.equalsIgnoreCase("furnace")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number count = (Number) outputEntry2.get("count");
                    Object nbt_b64 = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null);
                    Map<String, Object> inputEntry2 = (Map<String, Object>) entry.get("input");
                    int inputId = ((Number) inputEntry2.get("id")).intValue();
                    Number inputDamage = (Number) inputEntry2.get("damage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, craftingTag2);
                    } else {
                        data = fromFurnace(inputId, output, craftingTag2);
                    }
                    break;
                //case 4:
                //    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                //    break;
                default:
                    continue;
            }
            recipes_v388.add(data);
        }
        recipeData_v388.get("potionMixes").forEach(entry -> potions_v388.add(new PotionMixData(((Number) entry.get("fromPotionId")).intValue(), ((Number) entry.get("ingredient")).intValue(), ((Number) entry.get("toPotionId")).intValue())));
        recipeData_v388.get("containerMixes").forEach(entry -> containers_v388.add(new ContainerMixData(((Number) entry.get("fromItemId")).intValue(), ((Number) entry.get("ingredient")).intValue(), ((Number) entry.get("toItemId")).intValue())));

        for (Map<String, Object> entry : (List<Map<String, Object>>) DedicatedData.loadArray("recipes_v361.json")) {
            String type = String.valueOf(entry.get("type")).toLowerCase();
            CraftingData data = null;
            switch (type) {
                case "shapeless":
                //case "shapeless_shulker_box":
                //case "shapeless_chemistry":
                    String craftingTag0 = String.valueOf(entry.get("block"));
                    if (!craftingTag0.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    int priority0 = ((Number) entry.get("priority")).intValue();
                    String recipeId0 = String.valueOf(entry.get("recipe_id"));
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case "shapeless":
                            data = CraftingData.fromShapeless(recipeId0, inputs0, outputs0, uuid0, craftingTag0, priority0);
                            break;
                        case "shapeless_shulker_box":
                            data = CraftingData.fromShulkerBox(recipeId0, inputs0, outputs0, uuid0, craftingTag0, priority0);
                            break;
                        case "shapeless_chemistry":
                            data = CraftingData.fromShapelessChemistry(recipeId0, inputs0, outputs0, uuid0, craftingTag0, priority0);
                            break;
                    }
                    break;
                case "shaped":
                //case "shaped_chemistry":
                    String craftingTag1 = String.valueOf(entry.get("block"));
                    if (!craftingTag1.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    int priority1 = ((Number) entry.get("priority")).intValue();
                    String recipeId1 = String.valueOf(entry.get("recipe_id"));
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    switch (type) {
                        case "shaped":
                            data = CraftingData.fromShaped(recipeId1, width, height, inputs1, outputs1, uuid1, craftingTag1, priority1);
                            break;
                        case "shaped_chemistry":
                            data = CraftingData.fromShapedChemistry(recipeId1, width, height, inputs1, outputs1, uuid1, craftingTag1, priority1);
                            break;
                    }
                    break;
                case "smelting":
                    String craftingTag2 = String.valueOf(entry.get("block"));
                    if (!craftingTag2.equalsIgnoreCase("furnace")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number count = (Number) outputEntry2.get("count");
                    Object nbt_b64 = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null);
                    Map<String, Object> inputEntry2 = (Map<String, Object>) entry.get("input");
                    int inputId = ((Number) inputEntry2.get("id")).intValue();
                    Number inputDamage = (Number) inputEntry2.get("damage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, craftingTag2);
                    } else {
                        data = fromFurnace(inputId, output, craftingTag2);
                    }
                    break;
                //case "special_hardcoded":
                //    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                //    break;
                default:
                    continue;
            }
            recipes_v361.add(data);
        }

        for (Map<String, Object> entry : (List<Map<String, Object>>) DedicatedData.loadArray("recipes_v354.json")) {
            String type = String.valueOf(entry.get("type")).toLowerCase();
            CraftingData data = null;
            switch (type) {
                case "shapeless":
                //case "shapeless_shulker_box":
                //case "shapeless_chemistry":
                    String craftingTag0 = String.valueOf(entry.get("block"));
                    if (!craftingTag0.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case "shapeless":
                            data = CraftingData.fromShapeless(inputs0, outputs0, uuid0, craftingTag0);
                            break;
                        case "shapeless_shulker_box":
                            data = CraftingData.fromShulkerBox(inputs0, outputs0, uuid0, craftingTag0);
                            break;
                        case "shapeless_chemistry":
                            data = CraftingData.fromShapelessChemistry(inputs0, outputs0, uuid0, craftingTag0);
                            break;
                    }
                    break;
                case "shaped":
                //case "shaped_chemistry":
                    String craftingTag1 = String.valueOf(entry.get("block"));
                    if (!craftingTag1.equalsIgnoreCase("crafting_table")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    switch (type) {
                        case "shaped":
                            data = CraftingData.fromShaped(width, height, inputs1, outputs1, uuid1, craftingTag1);
                            break;
                        case "shaped_chemistry":
                            data = CraftingData.fromShapedChemistry(width, height, inputs1, outputs1, uuid1, craftingTag1);
                            break;
                    }
                    break;
                case "smelting":
                    String craftingTag2 = String.valueOf(entry.get("block"));
                    if (!craftingTag2.equalsIgnoreCase("furnace")) { //TODO: filter others out for now to avoid breaking economics
                        continue;
                    }
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number count = (Number) outputEntry2.get("count");
                    Object nbt_b64 = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null);
                    Map<String, Object> inputEntry2 = (Map<String, Object>) entry.get("input");
                    int inputId = ((Number) inputEntry2.get("id")).intValue();
                    Number inputDamage = (Number) inputEntry2.get("damage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, craftingTag2);
                    } else {
                        data = fromFurnace(inputId, output, craftingTag2);
                    }
                    break;
                //case "special_hardcoded":
                //    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                //    break;
                default:
                    continue;
            }
            recipes_v354.add(data);
        }

        for (Map<String, Object> entry : (List<Map<String, Object>>) DedicatedData.loadArray("recipes_v340.json")) {
            int type = ((Number) entry.get("type")).intValue();
            CraftingData data = null;
            switch (type) {
                case 0:
                //case 5:
                //case 6:
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case 0:
                            data = CraftingData.fromShapeless(inputs0, outputs0, uuid0, null);
                            break;
                        case 5:
                            data = CraftingData.fromShulkerBox(inputs0, outputs0, uuid0, null);
                            break;
                        case 6:
                            data = CraftingData.fromShapelessChemistry(inputs0, outputs0, uuid0, null);
                            break;
                    }
                    break;
                case 1:
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    data = CraftingData.fromShaped(width, height, inputs1, outputs1, uuid1, null);
                    break;
                case 2:
                case 3:
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number outputCount = (Number) outputEntry2.get("count");
                    Object outputNbt = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, outputCount != null ? outputCount.intValue() : 1, outputNbt != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(outputNbt))) : null);
                    int inputId = ((Number) entry.get("inputId")).intValue();
                    Number inputDamage = (Number) entry.get("inputDamage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, null);
                    } else {
                        data = fromFurnace(inputId, output, null);
                    }
                    break;
                /*case 4:
                    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                    break;
                case 7:
                    List<ItemData> inputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry3 = entry.get("uuid");
                    UUID uuid3 = null;
                    if (uuidEntry3 != null) {
                        uuid3 = UUID.fromString(String.valueOf(uuidEntry3));
                    }
                    ItemData[] inputs3 = inputItems3.toArray(new ItemData[0]);
                    ItemData[] outputs3 = outputItems3.toArray(new ItemData[0]);
                    data = CraftingData.fromShapedChemistry(((Number) entry.get("width")).intValue(), ((Number) entry.get("height")).intValue(), inputs3, outputs3, uuid3, null);
                    break;*/
                default:
                    continue;
            }
            recipes_v340.add(data);
        }

        for (Map<String, Object> entry : (List<Map<String, Object>>) DedicatedData.loadArray("recipes_v332.json")) {
            int type = ((Number) entry.get("type")).intValue();
            CraftingData data = null;
            switch (type) {
                case 0:
                //case 5:
                //case 6:
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case 0:
                            data = CraftingData.fromShapeless(inputs0, outputs0, uuid0, null);
                            break;
                        case 5:
                            data = CraftingData.fromShulkerBox(inputs0, outputs0, uuid0, null);
                            break;
                        case 6:
                            data = CraftingData.fromShapelessChemistry(inputs0, outputs0, uuid0, null);
                            break;
                    }
                    break;
                case 1:
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    data = CraftingData.fromShaped(width, height, inputs1, outputs1, uuid1, null);
                    break;
                case 2:
                case 3:
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number outputCount = (Number) outputEntry2.get("count");
                    Object outputNbt = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, outputCount != null ? outputCount.intValue() : 1, outputNbt != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(outputNbt))) : null);
                    int inputId = ((Number) entry.get("inputId")).intValue();
                    Number inputDamage = (Number) entry.get("inputDamage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, null);
                    } else {
                        data = fromFurnace(inputId, output, null);
                    }
                    break;
                /*case 4:
                    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                    break;
                case 7:
                    List<ItemData> inputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry3 = entry.get("uuid");
                    UUID uuid3 = null;
                    if (uuidEntry3 != null) {
                        uuid3 = UUID.fromString(String.valueOf(uuidEntry3));
                    }
                    ItemData[] inputs3 = inputItems3.toArray(new ItemData[0]);
                    ItemData[] outputs3 = outputItems3.toArray(new ItemData[0]);
                    data = CraftingData.fromShapedChemistry(((Number) entry.get("width")).intValue(), ((Number) entry.get("height")).intValue(), inputs3, outputs3, uuid3, null);
                    break;*/
                default:
                    continue;
            }
            recipes_v332.add(data);
        }

        for (Map<String, Object> entry : (List<Map<String, Object>>) DedicatedData.loadArray("recipes_v313.json")) {
            int type = ((Number) entry.get("type")).intValue();
            CraftingData data = null;
            switch (type) {
                case 0:
                //case 5:
                //case 6:
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case 0:
                            data = CraftingData.fromShapeless(inputs0, outputs0, uuid0, null);
                            break;
                        case 5:
                            data = CraftingData.fromShulkerBox(inputs0, outputs0, uuid0, null);
                            break;
                        case 6:
                            data = CraftingData.fromShapelessChemistry(inputs0, outputs0, uuid0, null);
                            break;
                    }
                    break;
                case 1:
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    data = CraftingData.fromShaped(width, height, inputs1, outputs1, uuid1, null);
                    break;
                case 2:
                case 3:
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number outputCount = (Number) outputEntry2.get("count");
                    Object outputNbt = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, outputCount != null ? outputCount.intValue() : 1, outputNbt != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(outputNbt))) : null);
                    int inputId = ((Number) entry.get("inputId")).intValue();
                    Number inputDamage = (Number) entry.get("inputDamage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, null);
                    } else {
                        data = fromFurnace(inputId, output, null);
                    }
                    break;
                /*case 4:
                    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                    break;
                case 7:
                    List<ItemData> inputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry3 = entry.get("uuid");
                    UUID uuid3 = null;
                    if (uuidEntry3 != null) {
                        uuid3 = UUID.fromString(String.valueOf(uuidEntry3));
                    }
                    ItemData[] inputs3 = inputItems3.toArray(new ItemData[0]);
                    ItemData[] outputs3 = outputItems3.toArray(new ItemData[0]);
                    data = CraftingData.fromShapedChemistry(((Number) entry.get("width")).intValue(), ((Number) entry.get("height")).intValue(), inputs3, outputs3, uuid3, null);
                    break;*/
                default:
                    continue;
            }
            recipes_v313.add(data);
        }

        for (Map<String, Object> entry : (List<Map<String, Object>>) DedicatedData.loadArray("recipes_v282.json")) { //1.6-1.7
            int type = ((Number) entry.get("type")).intValue();
            CraftingData data = null;
            switch (type) {
                case 0:
                //case 5:
                //case 6:
                    List<ItemData> inputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems0 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems0.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry0 = entry.get("uuid");
                    UUID uuid0 = uuidEntry0 != null ? UUID.fromString(String.valueOf(uuidEntry0)) : UUID.randomUUID();
                    ItemData[] inputs0 = inputItems0.toArray(new ItemData[0]);
                    ItemData[] outputs0 = outputItems0.toArray(new ItemData[0]);
                    switch (type) {
                        case 0:
                            data = CraftingData.fromShapeless(inputs0, outputs0, uuid0, null);
                            break;
                        case 5:
                            data = CraftingData.fromShulkerBox(inputs0, outputs0, uuid0, null);
                            break;
                        case 6:
                            data = CraftingData.fromShapelessChemistry(inputs0, outputs0, uuid0, null);
                            break;
                    }
                    break;
                case 1:
                    Map<String, ItemData> ingredients = new HashMap<>();
                    ((Map<String, Map<String, Object>>) entry.get("input")).forEach((symbol, itemEntry) -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        ingredients.put(symbol, ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    String[] shape = ((List<String>) entry.get("shape")).toArray(new String[0]);
                    List<ItemData> inputItems1 = new ArrayList<>();
                    int width = shape.length;
                    int height = shape[0].length();
                    for (String row : shape) {
                        for (char symbol : row.toCharArray()) {
                            if (symbol == ' ') {
                                inputItems1.add(ItemData.AIR);
                            } else {
                                inputItems1.add(ingredients.get(String.valueOf(symbol)));
                            }
                        }
                    }
                    List<ItemData> outputItems1 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems1.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry1 = entry.get("uuid");
                    UUID uuid1 = uuidEntry1 != null ? UUID.fromString(String.valueOf(uuidEntry1)) : UUID.randomUUID();
                    ItemData[] inputs1 = inputItems1.toArray(new ItemData[0]);
                    ItemData[] outputs1 = outputItems1.toArray(new ItemData[0]);
                    data = CraftingData.fromShaped(width, height, inputs1, outputs1, uuid1, null);
                    break;
                case 2:
                case 3:
                    Map<String, Object> outputEntry2 = (Map<String, Object>) entry.get("output");
                    Number outputDamage = (Number) outputEntry2.get("damage");
                    Number outputCount = (Number) outputEntry2.get("count");
                    Object outputNbt = outputEntry2.get("nbt_b64");
                    short outputMeta = 0;
                    ItemData output = ItemData.of(((Number) outputEntry2.get("id")).intValue(), outputDamage != null && (outputMeta = outputDamage.shortValue()) != -1 ? outputMeta : 0, outputCount != null ? outputCount.intValue() : 1, outputNbt != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(outputNbt))) : null);
                    int inputId = ((Number) entry.get("inputId")).intValue();
                    Number inputDamage = (Number) entry.get("inputDamage");
                    short inputMeta;
                    if (inputDamage != null && (inputMeta = inputDamage.shortValue()) != -1) {
                        data = fromFurnaceData(inputId, inputMeta, output, null);
                    } else {
                        data = fromFurnace(inputId, output, null);
                    }
                    break;
                /*case 4:
                    data = CraftingData.fromMulti(UUID.fromString(String.valueOf(entry.get("uuid"))));
                    break;
                case 7:
                    List<ItemData> inputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("input")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        short meta = 0;
                        inputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, 1));
                    });
                    List<ItemData> outputItems3 = new ArrayList<>();
                    ((List<Map<String, Object>>) entry.get("output")).forEach(itemEntry -> {
                        Number damage = (Number) itemEntry.get("damage");
                        Number count = (Number) itemEntry.get("count");
                        Object nbt_b64 = itemEntry.get("nbt_b64");
                        short meta = 0;
                        outputItems3.add(ItemData.of(((Number) itemEntry.get("id")).intValue(), damage != null && (meta = damage.shortValue()) != -1 ? meta : 0, count != null ? count.intValue() : 1, nbt_b64 != null ? (CompoundTag) PacketHelper.bytes2Nbt(Base64.getDecoder().decode(String.valueOf(nbt_b64))) : null));
                    });
                    Object uuidEntry3 = entry.get("uuid");
                    UUID uuid3 = null;
                    if (uuidEntry3 != null) {
                        uuid3 = UUID.fromString(String.valueOf(uuidEntry3));
                    }
                    ItemData[] inputs3 = inputItems3.toArray(new ItemData[0]);
                    ItemData[] outputs3 = outputItems3.toArray(new ItemData[0]);
                    data = CraftingData.fromShapedChemistry(((Number) entry.get("width")).intValue(), ((Number) entry.get("height")).intValue(), inputs3, outputs3, uuid3, null);
                    break;*/
                default:
                    continue;
            }
            recipes_v291.add(data);
        }
    }

    public static ItemData[] getCreative_v389() {
        return creative_v389;
    }

    public static ItemData[] getCreative_v388() {
        return creative_v388;
    }

    public static ItemData[] getCreative_v361() {
        return creative_v361;
    }

    public static ItemData[] getCreative_v354() {
        return creative_v354;
    }

    public static ItemData[] getCreative_v340() {
        return creative_v340;
    }

    public static ItemData[] getCreative_v332() {
        return creative_v332;
    }

    public static ItemData[] getCreative_v313() {
        return creative_v313;
    }

    public static ItemData[] getCreative_v291() {
        return creative_v291;
    }

    public static List<CraftingData> getRecipes_v389() {
        return recipes_v389;
    }

    public static List<CraftingData> getRecipes_v388() {
        return recipes_v388;
    }

    public static List<CraftingData> getRecipes_v361() {
        return recipes_v361;
    }

    public static List<CraftingData> getRecipes_v354() {
        return recipes_v354;
    }

    public static List<CraftingData> getRecipes_v340() {
        return recipes_v340;
    }

    public static List<CraftingData> getRecipes_v332() {
        return recipes_v332;
    }

    public static List<CraftingData> getRecipes_v313() {
        return recipes_v313;
    }

    public static List<CraftingData> getRecipes_v291() {
        return recipes_v291;
    }

    public static List<PotionMixData> getPotions_v389() {
        return potions_v389;
    }

    public static List<PotionMixData> getPotions_v388() {
        return potions_v388;
    }

    public static List<ContainerMixData> getContainers_v389() {
        return containers_v389;
    }

    public static List<ContainerMixData> getContainers_v388() {
        return containers_v388;
    }

    public static CraftingData fromFurnace(int inputId, ItemData output, String craftingTag) { //CraftingData.fromFurnace()
        return new CraftingData(CraftingType.FURNACE, null, -1, -1, inputId, -1, null, new ItemData[]{output}, null, craftingTag, -1);
    }

    public static CraftingData fromFurnaceData(int inputId, int inputDamage, ItemData output, String craftingTag) { //CraftingData.fromFurnaceData()
        return new CraftingData(CraftingType.FURNACE_DATA, null, -1, -1, inputId, inputDamage, null, new ItemData[]{output}, null, craftingTag, -1);
    }

    public static void init() {
        //nothing
    }
}
