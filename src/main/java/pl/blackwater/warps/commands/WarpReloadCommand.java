package pl.blackwater.warps.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.blackwater.warps.WarpPlugin;
import pl.blackwater.warps.configs.WarpConfig;
import pl.blackwater.warps.managers.WarpManagerImpl;
import pl.blackwater.warps.utils.ChatUtil;
import pl.mikigal.config.ConfigAPI;
import pl.mikigal.config.style.CommentStyle;
import pl.mikigal.config.style.NameStyle;

public class WarpReloadCommand implements CommandExecutor {

    private  final WarpPlugin plugin;

    public WarpReloadCommand(WarpPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("warp.admin")){
            plugin.setWarpConfig(ConfigAPI.init(
                    WarpConfig.class,
                    NameStyle.UNDERSCORE,
                    null,
                    true,
                    plugin
            ));
            plugin.setWarpManager(new WarpManagerImpl(plugin));
            return ChatUtil.sendMessage(commandSender, "&6Przeladowano warps.yml !");
        }
        return false;
    }
}
