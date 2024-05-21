package yuisanae2f.aml.ev;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.InventoryHolder;
import yuisanae2f.aml.cInventory;

import java.util.List;

import static yuisanae2f.aml.Aml.cout;

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
        atkReturn res;
        InventoryHolder atk, tar;

        if(
                e.getDamager() instanceof InventoryHolder
            &&  e.getEntity() instanceof InventoryHolder
        ) {
            atk = (InventoryHolder)e.getDamager();
            tar = (InventoryHolder)e.getEntity();

            res = this.dmg(new cInventory(atk.getInventory()), new cInventory(tar.getInventory()));
        } else if (
                e.getDamager() instanceof Projectile proj
                && e.getEntity() instanceof InventoryHolder
        ) {
            tar = (InventoryHolder)e.getEntity();
            if(proj.getShooter() instanceof InventoryHolder) {
                atk = (InventoryHolder)proj.getShooter();
                res = this.dmg(new cInventory(atk.getInventory()), new cInventory(tar.getInventory()));
            } else return;
        } else return;

        
        cout.info("attacker: ");
        cout.info(s(new cInventory(atk.getInventory()).getStatus().lorise()));

        cout.info("defender: ");
        cout.info(s(new cInventory(tar.getInventory()).getStatus().lorise()));
        if (atk instanceof Player player) {

            player.sendMessage("dmg:" + res.atk);
        }
    }

    private String s(List<String> a) {
        StringBuilder rtn = new StringBuilder();

        for(String str : a) {
            rtn.append(str).append('\n');
        }

        return rtn.toString();
    }

    private atkReturn dmg(cInventory atk, cInventory tar) {

        return new atkReturn(1.0);
    }
}