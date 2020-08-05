package zen.nukkit.zchat.Utils;

import cn.nukkit.Player;
import com.massivecraft.factions.P;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import ru.nukkit.multipass.Multipass;
import zen.nukkit.zchat.Objects.PermissionProvider;
import zen.nukkit.zchat.ZChat;

public class Provider {

    public static String getGroup(Player player) {
        String group = "default";
        try {
            if (ZChat.permissionProvider == PermissionProvider.MULTIPASS) {
                group = Multipass.getGroup(player);
            } else if (ZChat.permissionProvider == PermissionProvider.LUCKPERMS) {
                User user = LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId());
                group = user.getPrimaryGroup();
            }
            return group;
        } catch (Exception e) {
            ZChat.instance.getLogger().alert("Unable to get group for player " + player.getName());
            return "";
        }
    }

    public static boolean isValidGroup(String group) {
        if (ZChat.permissionProvider == PermissionProvider.MULTIPASS) {
            return  Multipass.isGroupExist(group);

        } else if (ZChat.permissionProvider == PermissionProvider.LUCKPERMS) {
            return LuckPermsProvider.get().getGroupManager().isLoaded(group);
        }
        return false;
    }

    public static String getPrefix(Player player) {
        String prefix = "";
        try {
            if (ZChat.permissionProvider == PermissionProvider.MULTIPASS) {
                if (Multipass.getPrefix(player) != null) {
                    prefix = Multipass.getPrefix(player);
                }
            } else if (ZChat.permissionProvider == PermissionProvider.LUCKPERMS) {
                User user = LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId());
                CachedMetaData metaData = user.getCachedData().getMetaData(LuckPermsProvider.get().getContextManager().getQueryOptions(player));
                if (metaData.getPrefix() != null) {
                    prefix = metaData.getPrefix();
                }
            }
        } catch (Exception e) {
            ZChat.instance.getLogger().alert("Unable to get prefix for player " + player.getName());
        }
        return prefix;
    }

    public static String getSuffix(Player player) {
        String suffix = "";
        try {
            if (ZChat.permissionProvider == PermissionProvider.MULTIPASS) {
                if (Multipass.getSuffix(player) != null) {
                    suffix = Multipass.getSuffix(player);
                }
            } else if (ZChat.permissionProvider == PermissionProvider.LUCKPERMS) {
                User user = LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId());
                CachedMetaData metaData = user.getCachedData().getMetaData(LuckPermsProvider.get().getContextManager().getQueryOptions(player));
                if (metaData.getSuffix() != null) {
                    suffix = metaData.getSuffix();
                }
            }
        } catch (Exception e) {
            ZChat.instance.getLogger().alert("Unable to get prefix for player " + player.getName());
        }
        return suffix;
    }

    public static String getFaction(Player player) {
        if (ZChat.factionsLoaded) {
            return P.p.getPlayerFactionTag(player);
        } else {
            return "FactionsNotLoaded";
        }
    }
}
