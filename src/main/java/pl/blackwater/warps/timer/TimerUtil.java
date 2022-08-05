package pl.blackwater.warps.timer;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.blackwater.warps.WarpPlugin;
import pl.blackwater.warps.utils.ChatUtil;
import pl.blackwater.warps.utils.Util;

public class TimerUtil
{
    public static void teleport(Player p, Location location, int delay) {
        if (!p.hasPermission("api.timer.bypass")) {
            ChatUtil.sendMessage(p,ChatUtil.fixColor(WarpPlugin.getWarpPlugin().getWarpConfig().getTeleportDelayMessage().replace("{TIME}",Util.secondsToString(delay))));
        }
        TimerManager.addTask(p, new TimerCallback<Player>() {
			@Override
            public void success(Player player) {
                player.teleport(location);
                ChatUtil.sendMessage(p,ChatUtil.fixColor(WarpPlugin.getWarpPlugin().getWarpConfig().getTeleportMessage()));
            	Location loc = player.getLocation();
            	player.getWorld().refreshChunk(loc.getBlockX(), loc.getBlockZ());
            }
            
            @Override
            public void error(Player player) {
                ChatUtil.sendMessage(player, ChatUtil.fixColor(WarpPlugin.getWarpPlugin().getWarpConfig().getTeleportErrorMessage()));
            }
        }, delay);
    }
}
