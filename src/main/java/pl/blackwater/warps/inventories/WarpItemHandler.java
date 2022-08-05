package pl.blackwater.warps.inventories;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.blackwater.warps.WarpPlugin;
import pl.blackwater.warps.configs.WarpConfig;
import pl.blackwater.warps.data.Warp;
import pl.blackwater.warps.inventoryapi.IAction;
import pl.blackwater.warps.timer.TimerUtil;
import pl.blackwater.warps.utils.ChatUtil;
import pl.blackwater.warps.utils.Util;

@RequiredArgsConstructor
public class WarpItemHandler implements IAction {

    private final Warp warp;
    private final WarpConfig config = WarpPlugin.getWarpPlugin().getWarpConfig();

    @Override
    public void execute(Player player, Inventory inventory, int clickedIndex, ItemStack clickedItemStack) {
        if(WarpPlugin.getEconomy().getBalance(Bukkit.getOfflinePlayer(player.getUniqueId())) >= warp.getCost()){
            WarpPlugin.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()),warp.getCost());
            player.closeInventory();
            TimerUtil.teleport(player,warp.getLocation(),WarpPlugin.getWarpPlugin().getWarpConfig().getWarpTeleportDelay());

            player.sendTitle(config.getTitleFirstLine().replace("{WARP}",warp.getName()).replace("{PRICE}",String.valueOf(warp.getCost())),config.getTitleSecondLine().replace("{WARP}",warp.getName()).replace("{PRICE}",String.valueOf(warp.getCost())),config.getTitleFadeInTime(),config.getTitleShowTime(),config.getTitleFadeOutTime());
        }else{
            ChatUtil.sendMessage(player,"&4Blad: &cNie posiadasz wymaganej kwoty &8(&4" + warp.getCost() + "$&8)");
        }
    }
}
