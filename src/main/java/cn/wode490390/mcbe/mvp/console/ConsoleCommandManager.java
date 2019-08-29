package cn.wode490390.mcbe.mvp.console;

import cn.wode490390.mcbe.mvp.Main;
import cn.wode490390.mcbe.mvp.PlayerManager;
import com.nukkitx.protocol.bedrock.BedrockServerSession;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConsoleCommandManager {

    //private final Main main;

    private final Map<String, ConsoleCommand> commandMap;

    public ConsoleCommandManager(Main main) {
        //this.main = main;
        commandMap = new HashMap<String, ConsoleCommand>(){
            {
                put("stop", (args) -> {
                    log.info("Stopping the server...");
                    main.shutdown();
                });
                put("version", (args) -> {
                    log.info("This server is running wodeMultiVersionProxy {} for Minecraft: BE 1.7.0-{} (target protocol version {})", Main.VERSION, Main.MINECRAFT_VERSION, Main.PROTOCOL_VERSION);
                });
                put("kick", (args) -> {
                    if (args.length <= 2) {
                        log.info("Usage: /kick <host> <port>");
                        return;
                    }
                    InetSocketAddress address = new InetSocketAddress(args[1], Integer.parseInt(args[2]));
                    for (BedrockServerSession session : PlayerManager.getPlayers().keySet()) {
                        if (session.getAddress().equals(address)) {
                            session.disconnect("disconnect.kicked");
                            PlayerManager.getPlayers().remove(session);
                            log.info("Succeed!");
                            return;
                        }
                    }
                    log.info("No targets matched");
                });
            }
        };
    }

    public void dispatch(String command) {
        String[] commandLine = command.split(" ");
        ConsoleCommand cmd = commandMap.get(commandLine[0]);
        if (cmd != null) {
            try {
                cmd.execute(commandLine);
            } catch (Exception e) {
                log.error("An unknown error occurred while attempting to perform this command: " + cmd, e);
            }
        } else {
            log.info("Unknown command");
        }
    }

    public Map<String, ConsoleCommand> getCommandMap() {
        return commandMap;
    }
}
