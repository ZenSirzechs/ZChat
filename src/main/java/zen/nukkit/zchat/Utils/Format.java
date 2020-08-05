package zen.nukkit.zchat.Utils;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.utils.TextFormat;
import zen.nukkit.zchat.Objects.FormatType;

public class Format {

    public static String formatString(String input, Player player, byte[] scopes, boolean colorize) {
        for (byte scope : scopes) {
            switch (scope) {
                case FormatType.PLAYER:
                    input = getPlayerFormat(input, player);
                    break;
                case FormatType.INVENTORY:
                    input = getInventoryFormat(input, player);
                    break;
                case FormatType.PERMISSION_PROVIDER:
                    input = getPermissionProviderFormat(input, player);
                    break;
                case FormatType.NEWLINE:
                    input = getNewLineFormat(input);
                    break;
                case FormatType.WILDCARD:
                    input = getWildCardFormat(input, player);
                    break;
                case FormatType.FACTION:
                    input = getFactionFormat(input, player);
                    break;
                case FormatType.PLAYER_STATUS:
                    input = playerStatusFormat(input, player);
                    break;
            }
        }
        if (colorize) {
            return TextFormat.colorize('&', input);
        } else {
            return input;
        }
    }

    public static String getPlayerFormat(String input, Player player) {
        return input.replace("%name%", player.getName())
                .replace("%display_name%", player.getDisplayName())
                .replace("%level%", player.getLevel().getName());
    }

    public static String getInventoryFormat(String input, Player player) {
        PlayerInventory inventory = player.getInventory();
        return input.replace("%holding_item%", inventory.getItemInHand().getName())
                .replace("%helmet%", inventory.getHelmet().getName())
                .replace("%chest_plate%", inventory.getChestplate().getName())
                .replace("%leggings%", inventory.getLeggings().getName())
                .replace("%boots%", inventory.getBoots().getName());
    }

    public static String getPermissionProviderFormat(String input, Player player) {
        return input.replace("%group%", Provider.getGroup(player))
                .replace("%prefix%", Provider.getPrefix(player))
                .replace("%suffix%", Provider.getSuffix(player));
    }

    public static String getNewLineFormat(String input) {
        return input.replace("%n%", "\n")
                .replace("%t%", "\t");
    }

    public static String getWildCardFormat(String input, Player player) {
        String output = input.replace("@s", player.getName());
        if (output.contains("@r")) {
            output = output.replace("@r", Utils.getRandomOnlinePlayer().getName());
        }
        return output;
    }

    public static String getFactionFormat(String input, Player player) {
        return input.replace("%faction%", Provider.getFaction(player));
    }

    public static String playerStatusFormat(String input, Player player) {
        return input.replace("%player_x%", String.valueOf(player.getFloorX()))
                .replace("%player_y%", String.valueOf(player.getFloorY()))
                .replace("%player_z%", String.valueOf(player.getFloorZ()))
                .replace("%facing_direction%", player.getDirection().getName())
                .replace("%player_exp%", String.valueOf(player.getExperience()))
                .replace("%player_exp_to_next%", String.valueOf(Player.calculateRequireExperience(player.getExperienceLevel() + 1)))
                .replace("%player_exp_level%", String.valueOf(player.getExperienceLevel()))
                .replace("%player_saturation%", String.valueOf(player.getFoodData().getFoodSaturationLevel()))
                .replace("%player_speed%", String.valueOf(player.getMovementSpeed()))
                .replace("%player_ping%", String.valueOf(player.getPing()))
                .replace("%player_health%", String.valueOf(player.getHealth()))
                .replace("%player_max_health%", String.valueOf(player.getMaxHealth()))
                .replace("%player_food%", String.valueOf(player.getFoodData().getLevel()))
                .replace("%player_uuid%", String.valueOf(player.getUniqueId()))
                .replace("%player_gamemode%", String.valueOf(player.getGamemode()))
                .replace("%player_ip%", player.getAddress())
                .replace("%player_scale%", String.valueOf(player.getScale()))
                .replace("player_os", getOS(player));
    }

    private static String getOS(Player player) {
        switch (player.getLoginChainData().getDeviceOS()) {
            case 1: {
                return "Android";
            }
            case 2: {
                return "iOS";
            }
            case 3: {
                return "Mac";
            }
            case 4: {
                return "Fire";
            }
            case 5: {
                return "Gear VR";
            }
            case 6: {
                return "HoloLens";
            }
            case 7: {
                return "Windows 10";
            }
            case 8: {
                return "Windows";
            }
            case 9: {
                return "Dedicated";
            }
            case 10: {
                return "tvOS";
            }
            case 11: {
                return "PlayStation";
            }
            case 12: {
                return "NX";
            }
            case 13: {
                return "Xbox";
            }
            default: {
                return "Unknown";
            }
        }
    }
}
