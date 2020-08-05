package zen.nukkit.zchat.EventListener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import zen.nukkit.zchat.Data.Properties;
import zen.nukkit.zchat.EventListener.EventHandlers.ChatEvent;
import zen.nukkit.zchat.EventListener.EventHandlers.JoinEvent;
import zen.nukkit.zchat.EventListener.EventHandlers.QuitEvent;

public class NukkitEvents implements Listener {

    @EventHandler
    private void onChat(PlayerChatEvent event) {
        if (Properties.chatFormatting != 0) {
            ChatEvent.handleChatEvent(event);
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    private void onJoin(PlayerJoinEvent event) {
        JoinEvent.HandleJoinEvent(event);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    private void onQuit(PlayerQuitEvent event) {
        QuitEvent.HandleQuitEvent(event);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    private void onDeath(PlayerDeathEvent event) {

    }

}
