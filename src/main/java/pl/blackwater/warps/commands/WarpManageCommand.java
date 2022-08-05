package pl.blackwater.warps.commands;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.blackwater.warps.WarpPlugin;
import pl.blackwater.warps.data.Warp;
import pl.blackwater.warps.utils.ChatUtil;
import pl.blackwater.warps.utils.Util;

public class WarpManageCommand implements CommandExecutor {

    private final WarpPlugin plugin;

    public WarpManageCommand(WarpPlugin plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player)sender;
            final String usage = "&4Blad: &cPoprawne uzycie /warpmanage create <cost> <name> (REQUIRED ITEM IN HAND) || /warpmanage delete <name>";
            if (args.length == 0) {
                ChatUtil.sendMessage(sender,usage);
                return true;
            }
            if(args[0].equalsIgnoreCase("create")){
                if(args.length >= 3){
                    if(Util.isInteger(args[1])){
                        final int cost = Integer.parseInt(args[1]);
                        final String name = StringUtils.join(args," ",2,args.length);
                        if(plugin.getWarpManager().getWarpByName(name) != null){
                            return ChatUtil.sendMessage(sender, "&4Blad: &cTaki warp juz istnieje!");
                        }
                        player.getInventory().getItemInMainHand();
                        if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                            return ChatUtil.sendMessage(sender, "&4Blad: &cMusisz miec cos w rece!");
                        }
                        plugin.getWarpManager().createWarp(new Warp(name,player.getLocation(),player.getInventory().getItemInMainHand().getType().name(),cost));

                        ChatUtil.sendMessage(sender,"CREATED WARP " + name);
                    }else{
                        ChatUtil.sendMessage(sender,usage);
                    }
                }else{
                    ChatUtil.sendMessage(sender,usage);
                }
            }else if(args[0].equalsIgnoreCase("delete")){
                if(args.length == 2){
                    final Warp warp = plugin.getWarpManager().getWarpByName(StringUtils.join(args," ",1,args.length));
                    if(warp != null){
                        plugin.getWarpManager().deleteWarp(warp);
                        ChatUtil.sendMessage(sender,"DELETED WARP " + warp.getName());
                    }else{
                        ChatUtil.sendMessage(sender,usage);
                    }
                }else{
                    ChatUtil.sendMessage(sender,usage);
                }
            }
        }
        return false;
    }
}
