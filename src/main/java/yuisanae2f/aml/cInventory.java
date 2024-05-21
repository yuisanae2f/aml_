package yuisanae2f.aml;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static yuisanae2f.aml.StatusRule.tagGlobal;

public class cInventory {
    private cStatus status;
    private Inventory inventory;

    public cInventory(Inventory inv) {
        this.status = new cStatus();
        this.inventory = inv;
        for(ItemStack item : inventory.getContents()) {
            if(item == null) continue;
            ItemMeta meta = item.getItemMeta();
            if(meta == null) continue;
            List<String> nullcheck = meta.getLore();
            if(nullcheck == null) continue;

            cStatus itemStatus = new cStatus(StatusRule.ruleGlobal.o(tagGlobal, nullcheck));
            itemStatus.amplify(item.getAmount());

            status.addself(itemStatus);
        }
    }
    public cStatus getStatus() {
        return status;
    }
    public void changeTag(rTags old) {
        for(ItemStack item : inventory) {
            if(item == null) continue;
            ItemMeta meta = item.getItemMeta();

            if(!(meta.hasCustomModelData() && meta.getCustomModelData() != 0))
                continue;

            List<String> lore = meta.getLore();

            cStatus status = new cStatus(StatusRule.ruleGlobal.o(old, lore));

            meta.setLore(status.lorise());
            item.setItemMeta(meta);
        }
    }
}
