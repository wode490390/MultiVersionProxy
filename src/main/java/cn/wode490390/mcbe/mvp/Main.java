package cn.wode490390.mcbe.mvp;

import cn.wode490390.mcbe.mvp.console.Console;
import cn.wode490390.mcbe.mvp.console.ConsoleCommandManager;
import cn.wode490390.mcbe.mvp.network.PacketHelper;
import cn.wode490390.mcbe.mvp.network.ServerEventHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.nukkitx.protocol.bedrock.BedrockPacketCodec;
import com.nukkitx.protocol.bedrock.BedrockServer;
import com.nukkitx.protocol.bedrock.v388.Bedrock_v388;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

@Log4j2
public class Main {

    public static final BedrockPacketCodec CODEC = Bedrock_v388.V388_CODEC;
    public static final int PROTOCOL_VERSION = CODEC.getProtocolVersion();
    public static final String MINECRAFT_VERSION = CODEC.getMinecraftVersion();

    public static final Properties GIT_INFO = getGitInfo();
    public static final String VERSION = getVersion();

    public static final JsonMapper JSON_MAPPER = (JsonMapper) new JsonMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    public static final YAMLMapper YAML_MAPPER = (YAMLMapper) new YAMLMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private static boolean TITLE = false;

    private static Main instance;
    private final AtomicBoolean running = new AtomicBoolean(true);

    private Config config;

    private final Thread consoleThread;
    private final Console console;

    private final ConsoleCommandManager commandManager;

    private static int maxClient = 0;

    private InetSocketAddress targetAddress;

    private final BedrockServer server;

    public static void main(String[] args) {
        if (instance != null) {
            throw new IllegalStateException("Already initialized");
        }

        System.setProperty("log4j.skipJansi", "false");

        InternalLoggerFactory.setDefaultFactory(Log4J2LoggerFactory.INSTANCE);
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);

        OptionParser parser = new OptionParser();
        parser.allowsUnrecognizedOptions();
        OptionSpec<Void> helpSpec = parser.accepts("help", "Shows this page").forHelp();
        OptionSpec<Void> titleSpec = parser.accepts("enable-title", "Enables title at the top of the window");
        OptionSpec<String> vSpec = parser.accepts("v", "Set verbosity of logging").withRequiredArg().ofType(String.class);
        OptionSpec<String> verbositySpec = parser.accepts("verbosity", "Set verbosity of logging").withRequiredArg().ofType(String.class);
        OptionSet options = parser.parse(args);

        if (options.has(helpSpec)) {
            try {
                parser.printHelpOn(System.out);
            } catch (IOException ignore) {

            }
            return;
        }

        TITLE = options.has(titleSpec);

        String verbosity = options.valueOf(vSpec);
        if (verbosity == null) {
            verbosity = options.valueOf(verbositySpec);
        }
        if (verbosity != null) {
            try {
                Level level = Level.valueOf(verbosity);
                setLogLevel(level);
            } catch (Exception ignore) {

            }
        }

        try {
            if (TITLE) {
                System.out.print((char) 0x1b + "]0;Proxy is starting up..." + (char) 0x07);
            }
            new Main();
        } catch (Throwable t) {
            log.throwing(t);
        }

        if (TITLE) {
            System.out.print((char) 0x1b + "]0;Proxy Stopped" + (char) 0x07);
        }

        System.exit(0);
    }

    private Main() throws IOException {
        instance = this;

        console = new Console(this);
        consoleThread = new Thread() {
            @Override
            public void run() {
                setName("Console");
                console.start();
            }
        };
        consoleThread.start();

        log.info("Loading configuration...");
        Path configPath = Paths.get(".").resolve("config.yml");
        if (Files.notExists(configPath) || !Files.isRegularFile(configPath)) {
            Files.copy(Main.class.getClassLoader().getResourceAsStream("config.yml"), configPath, StandardCopyOption.REPLACE_EXISTING);
        }
        config = Config.load(configPath);

        targetAddress = config.getDestination().getAddress();
        InetSocketAddress bindingAddress = config.getProxy().getAddress();

        log.info("Loading server...");
        commandManager = new ConsoleCommandManager(this);

        RuntimePaletteManager.init();
        InventoryManager.init();
        PacketHelper.init();

        log.info("Loading RakNet server...");
        server = new BedrockServer(bindingAddress);
        server.setHandler(new ServerEventHandler());
        server.bind().join();
        log.info("Proxy server started on {}", bindingAddress);

        int level = config.getLogLevel();
        switch (level) {
            case 1:
                setLogLevel(Level.INFO);
                break;
            case 2:
                setLogLevel(Level.DEBUG);
                break;
            default:
                if (level <= 0) {
                    setLogLevel(Level.OFF);
                } else {
                    setLogLevel(Level.TRACE);
                }
                break;
        }

        //Debuger.init();

        loop();
    }

    public Config getConfig() {
        return config;
    }

    public InetSocketAddress getTargetAddress() {
        return targetAddress;
    }

    public ConsoleCommandManager getCommandManager() {
        return commandManager;
    }

    private void loop() {
        while (running.get()) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException ignore) {

            }
        }

        server.close("disconnect.closed");

        consoleThread.interrupt();
    }

    public void shutdown() {
        if (running.compareAndSet(true, false)) {
            synchronized (this) {
                notify();
            }
        }
    }

    public boolean isRunning() {
        return running.get();
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setLogLevel(Level level) {
        if (level != null) {
            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            Configuration log4jConfig = ctx.getConfiguration();
            LoggerConfig loggerConfig = log4jConfig.getLoggerConfig(org.apache.logging.log4j.LogManager.ROOT_LOGGER_NAME);
            loggerConfig.setLevel(level);
            ctx.updateLoggers();
        } else {
            throw new NullPointerException("Log level cannot be null");
        }
    }

    private static Properties getGitInfo() {
        InputStream gitFileStream = Main.class.getClassLoader().getResourceAsStream("git.properties");
        if (gitFileStream != null) {
            Properties properties = new Properties();
            try {
                properties.load(gitFileStream);
            } catch (IOException e) {
                return null;
            }
            return properties;
        }
        return null;
    }

    private static String getVersion() {
        StringBuilder version = new StringBuilder("git-");
        String commitId;
        if (GIT_INFO == null || (commitId = GIT_INFO.getProperty("git.commit.id.abbrev")) == null) {
            return version.append("null").toString();
        }
        return version.append(commitId).toString();
    }
}
