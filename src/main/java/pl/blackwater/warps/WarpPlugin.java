package pl.blackwater.warps;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.blackwater.warps.commands.WarpCommand;
import pl.blackwater.warps.commands.WarpManageCommand;
import pl.blackwater.warps.commands.WarpReloadCommand;
import pl.blackwater.warps.configs.WarpConfig;
import pl.blackwater.warps.inventoryapi.InventoryListener;
import pl.blackwater.warps.managers.WarpManager;
import pl.blackwater.warps.managers.WarpManagerImpl;
import pl.mikigal.config.ConfigAPI;
import pl.mikigal.config.style.CommentStyle;
import pl.mikigal.config.style.NameStyle;

import java.util.logging.Logger;

public class WarpPlugin extends JavaPlugin {

    private static WarpPlugin warpPlugin;
    private WarpManager warpManager;
    private WarpConfig warpConfig;

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    @Override
    public void onLoad() {
        warpPlugin = this;
    }

    @Override
    public void onEnable() {

        this.warpConfig = ConfigAPI.init(
                WarpConfig.class,
                NameStyle.UNDERSCORE,
                CommentStyle.ABOVE_CONTENT,
                true,
                this
        );

        this.warpManager = new WarpManagerImpl(this);

        //VAULT IMPL

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.getCommand("warp").setExecutor(new WarpCommand());
        this.getCommand("warpmanage").setExecutor(new WarpManageCommand(this));
        this.getCommand("warpreload").setExecutor(new WarpReloadCommand(this));

        Bukkit.getPluginManager().registerEvents(new InventoryListener(),this);


    }

    public WarpConfig getWarpConfig() {
        return warpConfig;
    }

    public static WarpPlugin getWarpPlugin() {
        return warpPlugin;
    }

    public void setWarpConfig(WarpConfig warpConfig) {
        this.warpConfig = warpConfig;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    public static Economy getEconomy() {
        return econ;
    }

    public void setWarpManager(WarpManager warpManager) {
        this.warpManager = warpManager;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
}
