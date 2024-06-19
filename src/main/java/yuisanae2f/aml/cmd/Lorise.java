package yuisanae2f.aml.cmd;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yuisanae2f.aml.*;

import java.util.*;


public class Lorise {
    public static cCmd exe = new cCmd("lorise", new iCmd() {
        @Override
        public boolean exe(CommandSender sender, String[] argv) {
            sender.sendMessage("You've activated the command /lorise.");

            

            switch(argv.length) {
                case 2: case 3:
                    break;
                default: return false;
            }



            Player player;

            if(Objects.equals(argv[0], "@s")) {
                if (!((sender instanceof Player))) {
                    sender.sendMessage("Target is not a player.");
                    return false;
                }

                player = (Player)sender;
            } else if ((player = sender.getServer().getPlayerExact(argv[0])) == null) {
                sender.sendMessage("There is no such player.");
                return false;
            }

            ItemStack main = player.getInventory().getItemInMainHand();
            ItemMeta meta = main.getItemMeta();

            if(meta == null) {
                player.sendMessage("Hath no item on thy hand.");
                return false;
            }

            List<String> lore = meta.getLore();

            cStatus status = null;
            if ((meta.hasCustomModelData() && 0 != meta.getCustomModelData())) {
                status = new cStatus(lore);
            }

            eStatus tag;
            if ((tag = Tags.enumise(argv[1])) == null) {
                player.sendMessage("Thy tag is invalid.");
                return false;
            }

            switch(argv.length) {
                case 2: {
                    // get
                    if(status == null) {
                        player.sendMessage("No data on thy hand.");
                        break;
                    }

                    player.sendMessage("The value of " + argv[1] + ": " + status.get(tag));
                    break;
                }

                case 3: {
                    // set
                    if (status == null) {
                        status = new cStatus();
                        meta.setCustomModelData(1);
                    }

                    double ezdicb = status.get(tag);

                    status.set(tag, Double.parseDouble(argv[2]));
                    lore = status.lorise();

                    player.sendMessage("The value of " + argv[1] + " has changed from " + ezdicb + " to " + status.get(tag) + ".");

                    meta.setLore(lore);
                    main.setItemMeta(meta);
                    break;
                }
            }
            return true;
        }
    });
}
