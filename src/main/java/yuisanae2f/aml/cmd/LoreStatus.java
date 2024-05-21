package yuisanae2f.aml.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yuisanae2f.aml.cCmd;
import yuisanae2f.aml.cInventory;
import yuisanae2f.aml.iCmd;
import yuisanae2f.aml.rStatus;

import java.util.List;

public class LoreStatus {
    public static cCmd exe = new cCmd("loreStatus", new iCmd() {
        @Override
        public boolean exe(CommandSender sender, String[] argv) {
            Player player = null;

            switch(argv.length) {
                case 0: {
                    if (sender instanceof Player play) {
                        player = play;
                    } else {
                        sender.sendMessage("Thou art no Player.");
                        return true;
                    }
                } break;

                case 1: {
                    if(argv[0] == "@s") {
                        if(sender instanceof Player play) {
                            player = play;
                        } else {
                            sender.sendMessage("Thou art no Player.");
                            return true;
                        }
                    } else {
                        player = Bukkit.getPlayerExact(argv[0]);
                        if (player == null) {
                            sender.sendMessage("No such player existeth.");
                            return true;
                        }
                    }
                } break;
                default: return false;
            }

            List<String> lore = new cInventory(player.getInventory()).getStatus().lorise();
            sender.sendMessage(player.getName() + "'s current status:");

            for(String str : lore) {
                sender.sendMessage(str);
            }

            return true;
        }
    });
}
