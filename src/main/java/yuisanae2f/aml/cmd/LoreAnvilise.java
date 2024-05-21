package yuisanae2f.aml.cmd;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yuisanae2f.aml.*;

import java.io.*;
import java.util.Objects;

public class LoreAnvilise {

    public static cCmd exe = new cCmd("loreAnvilise", new iCmd() {
        @Override
        public boolean exe(CommandSender sender, String[] argv) {
            eStatus tag = null;

            switch(argv.length) {
                case 2: case 3: {
                    switch(argv[0]) {
                        case "enum": {
                            try {
                                tag = eStatus.valueOf(argv[1]);
                            } catch (Exception e) {
                                return false;
                            }
                        } break;

                        case "tag": {
                            tag = Tags.enumise(argv[1]);
                            if (tag == null) {
                                return false;
                            }
                        } break;

                        default:
                            sender.sendMessage("Certain parametre[" + argv[0] + "] is invalid.");
                            return false;
                    }
                }

                case 0: break;
                default: return false;
            }

            switch(argv.length) {
                case 0: {
                    for(int i = 0; i < eStatus.LMT.ordinal(); i++) {
                        sender.sendMessage(eStatus.values()[i].toString() + " : " + StatusRule.tagGlobal.tags()[i]);
                    }
                } break;
                case 2: {
                    switch(argv[0]) {
                        case "enum":
                            sender.sendMessage(Tags.toString(tag));
                            break;

                        case "tag":
                            sender.sendMessage(tag.toString());
                            break;
                    }
                } break;
                case 3: {
                    if (Objects.equals(Tags.toString(tag), argv[2]))
                        break;

                    rTags old = new rTags(StatusRule.tagGlobal);
                    Tags.change(tag, argv[2]);

                    for(Player player : Bukkit.getOnlinePlayers()) {
                        new cInventory(player.getInventory()).changeTag(old);
                        new cInventory(player.getEnderChest()).changeTag(old);
                    }

                    for(OfflinePlayer oplayer : Bukkit.getOfflinePlayers()) {
                        Player player = oplayer.getPlayer();

                        if(player == null) continue;

                        new cInventory(player.getInventory()).changeTag(old);
                        new cInventory(player.getEnderChest()).changeTag(old);
                    }

                    try {
                        FStream.save(Aml.Manual);
                    } catch(Exception e) {
                        sender.sendMessage("save failed");
                        sender.sendMessage(e.getMessage());
                        return true;
                    }

                    sender.sendMessage(old.tags()[tag.ordinal()] + " has changed to " + StatusRule.tagGlobal.tags()[tag.ordinal()]);
                } break;

                default: return false;
            }

            return true;
        }
    });
}
