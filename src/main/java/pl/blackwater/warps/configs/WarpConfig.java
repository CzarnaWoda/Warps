package pl.blackwater.warps.configs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.blackwater.warps.data.Warp;
import pl.mikigal.config.BukkitConfiguration;
import pl.mikigal.config.Config;
import pl.mikigal.config.annotation.Comment;
import pl.mikigal.config.annotation.ConfigName;

import java.util.*;

@ConfigName("warps.yml")
public interface WarpConfig extends Config {

    @Comment("Title of warp in gui")
    default String getWarpTitle(){
        return "&8>> &6WARP {NAME}";
    }
    @Comment("Lore of warp in gui")
    default List<String> getWarpLore(){
        return Arrays.asList("CLICK TO TELEPORT","IT'S JUST A LORE","KOSZT: {PRICE}");
    }
    @Comment("TITLE OF GUI")
    default String getGuiTitle(){
        return "&6GUI TITLE";
    }
    @Comment("ROWS OF GUI")
    default int getGuiSize(){
        return 3;
    }
    @Comment("Delay of teleport")
    default int getWarpTeleportDelay(){
        return 10;
    }
    default List<Warp> getWarps(){
        return Collections.singletonList(new Warp("spawn warp", Bukkit.getWorlds().get(0).getSpawnLocation(), "DIAMOND_SWORD", 40));
    }
    @Comment("TITLE PARAMETRS")
    default int getTitleFadeInTime(){
        return 1;
    }
    default int getTitleShowTime(){
        return 5;
    }
    default int getTitleFadeOutTime(){
        return 2;
    }
    default String getTitleFirstLine(){
        return "{WARP} {PRICE} TITLE";
    }
    default String getTitleSecondLine(){
        return "{WARP} SECOND LINE";
    }
    @Comment("MATERIAL OF PANES")
    default ItemStack getPane(){
        return new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
    }
    default List<Integer> getGuiPanes(){
        return Arrays.asList(1,2,3,4,5,6,7,8);
    }
    @Comment("TELEPORT MESSAGE")
    default String getTeleportMessage(){
        return "&6PRZETELEPORTOWANO";
    }
    @Comment("TELEPORT DELAY MESSAGE")
    default String getTeleportDelayMessage(){
        return "Teleportacja nastapi za {TIME}";
    }
    @Comment("TELEPORT ERROR/CANCEL MESSAGE")
    default String getTeleportErrorMessage(){
        return "Blad teleport zostal przerwany";
    }

    public void setWarps(List<Warp> warps);
}
