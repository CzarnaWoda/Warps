package pl.blackwater.warps.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import pl.blackwater.warps.WarpPlugin;
import pl.blackwater.warps.configs.WarpConfig;
import pl.blackwater.warps.data.Warp;
import pl.blackwater.warps.utils.ChatUtil;
import pl.blackwater.warps.inventoryapi.InventoryGUI;
import pl.blackwater.warps.inventoryapi.ItemBuilder;

public class WarpInventory {

    private static WarpPlugin plugin = WarpPlugin.getWarpPlugin();
    public static InventoryView openGui(Player player){
        final WarpConfig config = plugin.getWarpConfig();

        final InventoryGUI gui = new InventoryGUI(config.getGuiTitle(),config.getGuiSize());

        for(int i : plugin.getWarpConfig().getGuiPanes()){
            gui.setItem(i,plugin.getWarpConfig().getPane(),null);
        }
        for(Warp warp : plugin.getWarpManager().getWarps()){
            final ItemBuilder warpItem = new ItemBuilder(Material.getMaterial(warp.getMaterial()),1)
                    .setTitle(config.getWarpTitle().replace("{NAME}",warp.getName()));
            for(String s : config.getWarpLore()){
                warpItem.addLore(ChatUtil.fixColor(s.replace("{PRICE}",String.valueOf(warp.getCost()))));
            }

            gui.addItem(warpItem.build(),new WarpItemHandler(warp));
        }

        return player.openInventory(gui.get());
    }
}
