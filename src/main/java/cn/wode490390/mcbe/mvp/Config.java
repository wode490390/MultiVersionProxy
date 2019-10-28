package cn.wode490390.mcbe.mvp;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Config {

    public static Config load(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return Main.YAML_MAPPER.readValue(reader, Config.class);
        }
    }

    public static Config load(InputStream stream) throws IOException {
        return Main.YAML_MAPPER.readValue(stream, Config.class);
    }

    public static void save(Path path, Config configuration) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            Main.YAML_MAPPER.writerWithDefaultPrettyPrinter().writeValue(writer, configuration);
        }
    }

    private Address proxy;
    private Address destination;
    @JsonProperty("outernet-destination")
    private Address outernetDestination;
    @JsonProperty("log-level")
    private int logLevel = 1;

    private String motd = "Multi-version Proxy";
    @JsonProperty("sub-motd")
    private String subMotd = "wodeMVP";
    @JsonProperty("max-player")
    private int maxPlayer = 20; //TODO
    @JsonProperty("online-mode")
    private boolean onlineMode = false;
    private boolean whitelist = false; //TODO

    public Address getProxy() {
        return proxy;
    }

    public Address getDestination() {
        return destination;
    }

    public Address getOuternetDestination() {
        return outernetDestination;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public String getMotd() {
        return motd;
    }

    public String getSubMotd() {
        return subMotd;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public boolean isOnlineMode() {
        return onlineMode;
    }

    public boolean isWhitelist() {
        return whitelist;
    }

    public static class Address {

        private String host;
        private int port;

        InetSocketAddress getAddress() {
            return new InetSocketAddress(host, port);
        }

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }
    }
}
