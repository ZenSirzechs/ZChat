package zen.nukkit.zchat;

import zen.nukkit.zchat.Commands.TellCommand;
import zen.nukkit.zchat.Data.Properties;
import zen.nukkit.zchat.EventListener.NukkitEvents;
import zen.nukkit.zchat.Tasks.TagUpdateTask;

public class Loader {

    static void initilizeEvents() {
        ZChat.instance.getServer().getPluginManager().registerEvents(new NukkitEvents(), ZChat.instance);
    }

    static void registerCommands() {
        ZChat.instance.getServer().getCommandMap().register("tell", new TellCommand());
    }

    static void startTasks() {
        if (Properties.tagFormatUpdateInterval > 0) {
            ZChat.instance.getServer().getScheduler().scheduleRepeatingTask(ZChat.instance, new TagUpdateTask(), Properties.tagFormatUpdateInterval, Properties.async);
        }
    }

}
