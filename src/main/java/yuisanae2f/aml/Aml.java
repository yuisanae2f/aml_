package yuisanae2f.aml;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yuisanae2f.aml.cmd.LoreAnvilise;
import yuisanae2f.aml.cmd.LoreStatus;
import yuisanae2f.aml.cmd.Lorise;
import yuisanae2f.aml.ev.cAttackEv;

import java.io.IOException;
import java.util.logging.Logger;

public final class Aml extends JavaPlugin {
    public static String Manual = "AMLGlobalState.txt";
    public static Logger cout = Bukkit.getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        cout.info("Hello World!");

        // cmd addition
        this.getCommand("lorise").setExecutor(Lorise.exe);
        this.getCommand("loreAnvilise").setExecutor(LoreAnvilise.exe);
        this.getCommand("loreStatus").setExecutor(LoreStatus.exe);

        // EvManager
        Bukkit.getPluginManager().registerEvents(new cAttackEv(), this);

        FStream.dir = getDataFolder();
        FStream.dir.mkdirs();

        try {
            FStream.load(Manual);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().fine("hi");
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
