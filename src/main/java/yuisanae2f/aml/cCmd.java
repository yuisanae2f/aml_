package yuisanae2f.aml;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class cCmd implements CommandExecutor {
    private final iCmd fp;
    private final String name;

    public cCmd(String name, iCmd fun) {
        this.fp = fun;
        this.name = name;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!Objects.equals(name, command.getName()))
            return false;

        return this.fp.exe(commandSender, strings);
    }
}