package yuisanae2f.aml;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static yuisanae2f.aml.StatusRule.tagGlobal;

public class cInventory {
    private cStatus status[];
    private Inventory inventory;
    private EntityEquipment eq;

    private void addSelf(ItemStack item, eInventory e) {
        if(item == null) return;
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return;
        List<String> nullcheck = meta.getLore();
        if(nullcheck == null) return;
        if (!item.getItemMeta().hasCustomModelData()) return;

        int mdl = item.getItemMeta().getCustomModelData();
        if(((mdl >> e.ordinal()) & 1) == 1) {
            cStatus itemStatus = new cStatus(StatusRule.ruleGlobal.o(tagGlobal, nullcheck));
            itemStatus.amplify(item.getAmount());
            status[e.ordinal()].addself(itemStatus);
        }
    }
    public cInventory(Entity entity) {
        this.status = new cStatus[eInventory.LMT.ordinal()];

        if (entity instanceof InventoryHolder holder) {
            inventory = holder.getInventory();
            for(ItemStack item : inventory.getContents()) {
                addSelf(item, eInventory.INVENTORY);
            }
        } else inventory = null;

        if (entity instanceof LivingEntity holder) {
            eq = holder.getEquipment();
            for (ItemStack item : eq.getArmorContents()) {
                addSelf(item, eInventory.ARMOUR);
            }

            addSelf(eq.getItemInMainHand(), eInventory.MAIN);
            addSelf(eq.getItemInOffHand(), eInventory.OFF);
        } else eq = null;
    }
    public cInventory(Block block) {
        if (block instanceof Container holder) {
            inventory = holder.getInventory();
            for(ItemStack item : inventory.getContents()) {
                addSelf(item, eInventory.INVENTORY);
            }
        } else inventory = null;
    }
    private cInventory(Inventory inv) {
        inventory = inv;
        for(ItemStack item : inventory.getContents()) {
            addSelf(item, eInventory.INVENTORY);
        }
    }
    public cStatus getStatus() {
        cStatus add = new cStatus();

        for(cStatus t : status) {
            add.addself(t);
        }

        return add;
    }
    private ItemStack _change(ItemStack item, rTags old) {
        if(item == null) return item;
        ItemMeta meta = item.getItemMeta();

        if(!(meta.hasCustomModelData() && meta.getCustomModelData() != 0))
            return item;

        List<String> lore = meta.getLore();

        cStatus status = new cStatus(StatusRule.ruleGlobal.o(old, lore));

        meta.setLore(status.lorise());
        item.setItemMeta(meta);

        return item;
    }
    public void changeTag(rTags old) {
        if(inventory != null) for(ItemStack item : inventory) {
            _change(item, old);
        }

        if(eq != null) {
            eq.setItemInMainHand(_change(eq.getItemInMainHand(), old));
            eq.setItemInOffHand(_change(eq.getItemInOffHand(), old));

            for(ItemStack i : eq.getArmorContents()) {
                _change(i, old);
            }
        }
    }

    public static boolean changeAll(rTags old) {
        for(Player e : Bukkit.getOnlinePlayers()) {
            new cInventory(e.getEnderChest()).changeTag(old);
        }

        for(OfflinePlayer e : Bukkit.getOfflinePlayers()) {
            new cInventory(e.getPlayer().getEnderChest()).changeTag(old);
        }

        for(World w : Bukkit.getWorlds()) {
            for (Entity e : w.getEntities()) {
                new cInventory(e).changeTag(old);
            }

            for (Chunk ch : w.getLoadedChunks()) {
                for(BlockState block : ch.getTileEntities()) {
                    new cInventory(block.getBlock()).changeTag(old);
                }
            }
        }

        try {
            FStream.save(Aml.Manual);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
