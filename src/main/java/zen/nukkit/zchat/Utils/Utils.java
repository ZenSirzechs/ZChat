package zen.nukkit.zchat.Utils;

import cn.nukkit.Player;
import cn.nukkit.Server;
import zen.nukkit.zchat.ZChat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static Random random = new Random();

    public static Player getRandomOnlinePlayer() {
        List<Player> playerList = new ArrayList<>(ZChat.instance.getServer().getOnlinePlayers().values());
        return playerList.get(random.nextInt(playerList.size()));
    }

    public static void sendPopUp(String message) {
        Server.getInstance().getOnlinePlayers().values().forEach(player -> {
            player.sendPopup(message);
        });
    }
}
