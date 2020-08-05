package zen.nukkit.zchat.Tasks;

import cn.nukkit.Server;
import zen.nukkit.zchat.Data.DataCache;
import zen.nukkit.zchat.Data.Properties;
import zen.nukkit.zchat.Objects.FormatType;
import zen.nukkit.zchat.Utils.Format;
import zen.nukkit.zchat.Utils.Provider;

public class TagUpdateTask extends Thread {

    public static byte[] scopes = new byte[] {FormatType.NEWLINE, FormatType.PLAYER, FormatType.PERMISSION_PROVIDER, FormatType.FACTION};

    @Override
    public void run() {
        Server.getInstance().getOnlinePlayers().values().forEach(player -> {
            String tag;

            if (Properties.tagFormattingEnabled == 2) {
                tag = DataCache.formats.get(Provider.getGroup(player)).getTagFormat(player.level);
            } else {
                tag = DataCache.formats.get(Provider.getGroup(player)).getTagFormat();
            }

            tag = Format.formatString(tag, player, scopes, true);

            if (!tag.equals(player.getNameTag())) {
                player.setNameTag(tag);
            }

        });
    }
}
