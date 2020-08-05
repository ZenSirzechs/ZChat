package zen.nukkit.zchat.Commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

import java.util.Objects;

public class TellCommand extends VanillaCommand {

    public TellCommand() {
        super("tell", "Private message a player", "Try /tell [Name] [Message]", new String[] {"w", "msg"});
        this.setPermission("nukkit.command.tell");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
                new CommandParameter("player", CommandParamType.TARGET, false),
                new CommandParameter("message", CommandParamType.TEXT, false)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }

        if (args.length < 2) {
            return false;
        }

        String name = args[0].toLowerCase();

        Player player = sender.getServer().getPlayer(name);
        if (player == null) {
            sender.sendMessage(new TranslationContainer("commands.generic.player.notFound"));
            return true;
        }

        if (Objects.equals(player, sender)) {
            sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.message.sameTarget"));
            return true;
        }

        StringBuilder msg = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            msg.append(args[i]).append(' ');
        }
        if (msg.length() > 0) {
            msg = new StringBuilder(msg.substring(0, msg.length() - 1));
        }

        String displayName = (sender instanceof Player ? ((Player) sender).getDisplayName() : sender.getName());
        String msgFinal = '[' + displayName + " -> " + player.getDisplayName() + "] " + msg;

        Server.getInstance().getOnlinePlayers().values().forEach(p -> {
            if (p == sender || p == player) {
                p.sendMessage(msgFinal);
            } else if (p.hasPermission("zchat.show.pm")) {
                p.sendMessage("\u00A7o\u00A77" + msgFinal);
            }
        });

        return true;
    }
}
