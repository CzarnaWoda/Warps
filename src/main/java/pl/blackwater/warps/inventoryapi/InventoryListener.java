package pl.blackwater.warps.inventoryapi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import pl.blackwater.warps.utils.ChatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryListener implements Listener {


    private static final Map<Inventory, InventoryGUI> inventories = new HashMap<>();
    private final HashMap<UUID, Long> times = new HashMap<>();

    @EventHandler
    public void onClick(final InventoryClickEvent e){
        final Player player = (Player) e.getWhoClicked();
        InventoryGUI gui = inventories.get(e.getInventory());
        if (gui == null) return;
        e.setCancelled(true);
        IAction action = gui.getActions().get(e.getRawSlot());
        if (action != null) {
            final Long time = this.times.get(player.getUniqueId());
            if (!player.hasPermission("gui.admin") && time != null && time > System.currentTimeMillis()) {
                ChatUtil.sendMessage(player, "&4Blad: &cNie mozesz tak czesto klikac !");
            } else {
                times.put(player.getUniqueId(), System.currentTimeMillis() + 500L);
                action.execute(player, e.getInventory(), e.getRawSlot(), e.getInventory().getItem(e.getRawSlot()));
            }
        }
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent event) {
        InventoryGUI gui = inventories.get(event.getInventory());
        if (gui == null) {
            return;
        }
        inventories.remove(event.getInventory());
    }

    public static Map<Inventory, InventoryGUI> getInventories() {
        return inventories;
    }
}
