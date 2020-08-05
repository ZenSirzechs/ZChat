package zen.nukkit.zchat;

import cn.nukkit.plugin.PluginBase;
import zen.nukkit.zchat.Data.PluginConfig;

public class ZChat extends PluginBase {

    public static ZChat instance;
    public static PluginConfig pluginConfig;
    public static byte permissionProvider;
    public static boolean factionsLoaded;

    @Override
    public void onLoad() {
        instance = this;
        pluginConfig = new PluginConfig();
    }



    @Override
    public void onEnable() {



    }


}
