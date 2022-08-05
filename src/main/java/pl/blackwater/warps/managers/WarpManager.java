package pl.blackwater.warps.managers;

import pl.blackwater.warps.data.Warp;

import java.util.List;

public interface WarpManager {

    Warp getWarpByName(String warpName);

    List<Warp> getWarps();

    void createWarp(Warp warp);

    void deleteWarp(Warp warp);

    void load();


}
