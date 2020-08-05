package zen.nukkit.zchat.EventListener.EventHandlers;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.utils.TextFormat;
import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import zen.nukkit.zchat.Data.DataCache;
import zen.nukkit.zchat.Data.Properties;
import zen.nukkit.zchat.Objects.ChatChannel;
import zen.nukkit.zchat.Objects.FormatType;
import zen.nukkit.zchat.Utils.Format;
import zen.nukkit.zchat.Utils.Provider;
import zen.nukkit.zchat.Utils.TextUtils;

import java.util.HashSet;
import java.util.Set;

public class ChatEvent {

    public static final Long2LongMap cooldowns = new Long2LongOpenHashMap();
    //Gonna make it available for API usage
    public static byte[] scopes = new byte[] {FormatType.NEWLINE, FormatType.PLAYER, FormatType.PERMISSION_PROVIDER, FormatType.FACTION};

    public static void handleChatEvent(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (Properties.chatCooldown > 0) {
            if (cooldowns.containsKey(player.getId()) &&
                cooldowns.get(player.getId()) > System.currentTimeMillis() + 1000 * Properties.chatCooldown) {

                event.setCancelled();
                player.sendMessage(Properties.chatCooldownMessage);
                return;
            }
            cooldowns.put(player.getId(), System.currentTimeMillis());
        }
        if (DataCache.blockedTexts.size() > 0 && TextUtils.textIsBlocked(message)) {
            event.setCancelled();
            player.sendMessage(Properties.textBlockedMessage);
        } else if (DataCache.textCensor.size() > 0) {
            message = TextUtils.censorTexts(message);
        }

        if (player.hasPermission("zchat.chat.colorize")) {
            message = TextFormat.colorize('&', message);
        }

        String format;

        if (Properties.chatFormattingPerWorld) {
            format = DataCache.formats.get(Provider.getGroup(player)).getChatFormat(player.level);
        } else {
            format = DataCache.formats.get(Provider.getGroup(player)).getChatFormat();
        }

        format = Format.formatString(format, player, scopes, true).replace("%message%", message);

        if (Properties.chatFormatting == ChatChannel.LEVEL) {
            Set<CommandSender> players = new HashSet<>(player.getLevel().getPlayers().values());
            event.setRecipients(players);
        } else if (Properties.chatFormatting == ChatChannel.GROUP) {
            Set<CommandSender> players = new HashSet<>();
            Server.getInstance().getOnlinePlayers().values().forEach(p -> {
                if (Provider.getGroup(p).equals(Provider.getGroup(player))) {
                    players.add(p);
                }
            });
            event.setRecipients(players);
        } else if (Properties.chatFormatting == ChatChannel.FACTION) {
            Set<CommandSender> players = new HashSet<>();
            Server.getInstance().getOnlinePlayers().values().forEach(p -> {
                if (Provider.getFaction(p).equals(Provider.getFaction(player))) {
                    players.add(p);
                }
            });
            event.setRecipients(players);
        } else if (Properties.chatFormatting >= 5) { // Means radius
            Set<CommandSender> players = new HashSet<>();
            player.getLevel().getPlayers().values().forEach(p -> {
                if (player.distance(p.getPosition()) <= Properties.chatFormatting) {
                    players.add(p);
                }
            });
            event.setRecipients(players);
        } else {
            event.setFormat(format);
        }

    }
}
