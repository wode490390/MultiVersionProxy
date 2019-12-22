package cn.wode490390.mcbe.mvp;

import com.nukkitx.nbt.NbtUtils;
import com.nukkitx.nbt.stream.NBTInputStream;
import com.nukkitx.nbt.tag.Tag;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DedicatedData {

    private static InputStream load(String file) throws IOException {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(file);
        if (stream == null) {
            throw new FileNotFoundException("Unable to locate " + file);
        }
        return stream;
    }

    public static Map loadMap(String file) {
        try {
            return Main.JSON_MAPPER.readValue(load(file), LinkedHashMap.class);
        } catch (IOException e) {
            log.error("Unable to load " + file, e);
        }
        return new HashMap<>();
    }

    public static List loadArray(String file) {
        try {
            return Main.JSON_MAPPER.readValue(load(file), ArrayList.class);
        } catch (IOException e) {
            log.error("Unable to load " + file, e);
        }
        return new ArrayList<>();
    }

    public static Tag<?> loadNbt(String file) {
        try (NBTInputStream reader = NbtUtils.createNetworkReader(load(file))) {
            return reader.readTag();
        } catch (IOException e) {
            log.error("Unable to load " + file);
            throw new RuntimeException(e);
        }
    }
}
