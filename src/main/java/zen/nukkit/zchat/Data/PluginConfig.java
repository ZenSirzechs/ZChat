package zen.nukkit.zchat.Data;

import cn.nukkit.utils.Config;
import zen.nukkit.zchat.ZChat;

public class PluginConfig {

    private static final ZChat plugin = ZChat.instance;
    private final Config properties;
    private final Config formats;


    public PluginConfig() {
        properties = new Config(plugin.getDataFolder() + "/Properties.yml", Config.YAML);
        formats = new Config(plugin.getDataFolder() + "/Format.yml", Config.YAML);
    }

    public static void loadConfig() {
        if (Properties.nickNameEnabled) {
            DataCache.nickNames = new Config(plugin.getDataFolder() + "/NickNameData", Config.YAML);
        }
    }

    public Config getProperties() {
        return properties;
    }

    public Config getFormats() {
        return formats;
    }
}
