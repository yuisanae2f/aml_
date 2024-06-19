package yuisanae2f.aml.ev;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import yuisanae2f.aml.cInventory;
import yuisanae2f.aml.eStatus;

import java.util.List;

import static yuisanae2f.aml.Aml.cout;

public class cAttackEv implements Listener {
    private static abstract class atkReturn {
        public double
                atk = 0,
                vamp = 0;

        abstract double trueDmg(double a);

        public atkReturn() {}
    }

    @EventHandler
    public void onAtk(EntityDamageByEntityEvent e) {
        cInventory atk = null, tar = new cInventory(e.getEntity());

        if(e.getDamager() instanceof Projectile proj) {
            ProjectileSource source = proj.getShooter();
            if(source instanceof Entity ntt)
                atk = new cInventory(ntt);
            else if (source instanceof Block block)
                atk = new cInventory(block);
        } else atk = new cInventory(e.getDamager());

        atkReturn res = this.dmg(atk, tar);

        cout.info("attacker: ");
        cout.info(s(atk.getStatus().lorise()));

        cout.info("defender: ");
        cout.info(s(tar.getStatus().lorise()));
    }

    private String s(List<String> a) {
        StringBuilder rtn = new StringBuilder();
        for(String str : a) {
            rtn.append(str).append('\n');
        }

        return rtn.toString();
    }

    private atkReturn dmg(cInventory atk, cInventory tar) {
        atkReturn rtn = new atkReturn() {
            @Override
            double trueDmg(double a) {
                return a > 10 ? a / 10 : 1;
            }
        };

        atk.getStatus().get(eStatus.ATK_DMG_MAX);

        return rtn;
    }
}