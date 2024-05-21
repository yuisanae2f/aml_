package yuisanae2f.aml.ev;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.InventoryHolder;
import yuisanae2f.aml.cInventory;
import yuisanae2f.aml.cStatus;

public class cAttackEv implements Listener {
    private class atkReturn {
        public final double
                atk;

        public atkReturn(double atk) {
            this.atk = atk;
        }
    }



    @EventHandler
    public void onAtk(EntityDamageByEntityEvent e) {
        if(
                e.getDamager() instanceof InventoryHolder atk
            &&  e.getEntity() instanceof InventoryHolder tar
        ) {

            atkReturn res = this.dmg(new cInventory(atk.getInventory()), new cInventory(tar.getInventory()));

            if (atk instanceof Player player) {
                player.sendMessage("dmg:" + res.atk);
            }
        }
    }

    private atkReturn dmg(cInventory atk, cInventory tar) {

        return new atkReturn(1.0);
    }
}