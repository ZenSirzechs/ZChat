package zen.nukkit.zchat.EventListener.EventHandlers;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import zen.nukkit.zchat.Data.DataCache;
import zen.nukkit.zchat.Data.Properties;
import zen.nukkit.zchat.Objects.FormatType;
import zen.nukkit.zchat.Objects.MessageMethod;
import zen.nukkit.zchat.Utils.Format;
import zen.nukkit.zchat.Utils.Provider;
import zen.nukkit.zchat.Utils.Utils;

public class JoinEvent {

    public static byte[] scopes = new byte[] {FormatType.NEWLINE, FormatType.PLAYER, FormatType.PERMISSION_PROVIDER, FormatType.FACTION};

    public static void HandleJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String format;

        try {
            format = Format.formatString(DataCache.formats.get(Provider.getGroup(player)).getJoinFormat(), player, scopes, true);
        } catch (NullPointerException ignored) {
            format = Format.formatString(DataCache.formats.get("DEFAULT_FORMATS").getJoinFormat(), player, scopes, true);
        }

        //TODO: Have a list of worlds where players don't recieve join/quit messages

        switch (Properties.joinMessageEnabled) {
            case MessageMethod.CHAT:
                event.setJoinMessage(format);
                break;
            case MessageMethod.POPUP:
                event.setJoinMessage("");
                Utils.sendPopUp(format);
                break;
            default:

        }

        player.sendMessage(DataCache.formats.get(Provider.getGroup(player)).getWelcomeMessage());

        if (Properties.nickNameEnabled) {
            player.setDisplayName(DataCache.nickNames.getString(player.getUniqueId().toString()));
        }

    }
}
