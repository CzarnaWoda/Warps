package pl.blackwater.warps.inventoryapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IAction
{
  void execute(Player actionPlayer, Inventory clickedInventory, int clickedIndex, ItemStack clickedItemStack);
}
