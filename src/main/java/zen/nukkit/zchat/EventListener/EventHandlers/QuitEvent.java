package zen.nukkit.zchat.EventListener.EventHandlers;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerQuitEvent;
import zen.nukkit.zchat.Data.DataCache;
import zen.nukkit.zchat.Data.Properties;
import zen.nukkit.zchat.Objects.FormatType;
import zen.nukkit.zchat.Objects.MessageMethod;
import zen.nukkit.zchat.Utils.Format;
import zen.nukkit.zchat.Utils.Provider;
import zen.nukkit.zchat.Utils.Utils;

public class QuitEvent {

    public static byte[] scopes = new byte[] {FormatType.NEWLINE, FormatType.PLAYER, FormatType.PERMISSION_PROVIDER, FormatType.FACTION};

    public static void HandleQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String format;

        try {
            format = Format.formatString(DataCache.formats.get(Provider.getGroup(player)).getQuitFormat(), player, scopes, true);
        } catch (NullPointerException ignored) {
            format = Format.formatString(DataCache.formats.get("DEFAULT_FORMATS").getQuitFormat(), player, scopes, true);
        }

        //TODO: Have a list of worlds where players don't recieve join/quit messages

        switch (Properties.quitMessageEnabled) {
            case MessageMethod.CHAT:
                event.setQuitMessage(format);
                break;
            case MessageMethod.POPUP:
                event.setQuitMessage("");
                Utils.sendPopUp(format);
                break;
            default:

        }

    }
}
