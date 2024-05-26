package yuisanae2f.aml.cmd;
import org.bukkit.command.CommandSender;
import yuisanae2f.aml.*;

import java.io.*;
import java.util.Objects;

import static yuisanae2f.aml.Aml.cout;

public class LoreAnvilise {

    public static cCmd exe = new cCmd("loreAnvilise", new iCmd() {
        @Override
        public boolean exe(CommandSender sender, String[] argv) {
            eStatus tag = null;

            switch(argv.length) {
                case 2: case 3:
                {
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
                        case "load": case "save": default:
                            return false;

                        case "enum":
                            sender.sendMessage(Tags.toString(tag));
                            break;

                        case "tag":
                            sender.sendMessage(tag.toString());
                            break;
                    }
                } break;
                case 3: {
                    switch(argv[0]) {
                        case "load": {
                            try {
                                FStream.load(argv[1]);
                                return true;
                            } catch (IOException e) {
                                cout.info(e.toString());
                                return false;
                            }
                        }
                        case "save": {
                            try {
                                FStream.save(argv[1]);
                                return true;
                            } catch (IOException e) {
                                cout.info(e.toString());
                                return false;
                            }
                        }
                        default: break;
                    }

                    if (Objects.equals(Tags.toString(tag), argv[2]))
                        break;

                    rTags old = new rTags(StatusRule.tagGlobal);
                    Tags.change(tag, argv[2]);

                    boolean bool = cInventory.changeAll(old);

                    if(!bool)
                        sender.sendMessage("save failed");

                    sender.sendMessage(old.tags()[tag.ordinal()] + " has changed to " + StatusRule.tagGlobal.tags()[tag.ordinal()]);
                } break;

                default: return false;
            }

            return true;
        }
    });
}
