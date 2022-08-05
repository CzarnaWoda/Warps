package pl.blackwater.warps.managers;

import pl.blackwater.warps.WarpPlugin;
import pl.blackwater.warps.data.Warp;

import java.util.List;

public class WarpManagerImpl implements WarpManager{

    private final WarpPlugin plugin;
    private List<Warp> warps;
    public WarpManagerImpl(WarpPlugin plugin){
        this.plugin = plugin;

        //LOAD WARPS
        load();

    }
    @Override
    public Warp getWarpByName(String warpName) {
        for(Warp warp : getWarps()){
            if(warp.getName().equals(warpName)) {
                return warp;
            }
        }
        return null;
    }

    @Override
    public List<Warp> getWarps() {
        return warps;
    }

    @Override
    public void createWarp(Warp warp) {
        this.warps.add(warp);

        plugin.getWarpConfig().setWarps(warps);
    }

    @Override
    public void deleteWarp(Warp warp) {
        this.warps.remove(warp);

        plugin.getWarpConfig().setWarps(warps);
    }

    @Override
    public void load() {
        this.warps = plugin.getWarpConfig().getWarps();
    }
}
