package pl.blackwater.warps.data;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

import java.io.Serializable;


@Data
public class Warp implements Serializable {

    private String name;
    private Location location;
    private String material;
    private double cost;

    public Warp(){

    }
    public Warp(String name, Location location, String material, double cost){
        this.name = name;
        this.location = location;
        this.material = material;
        this.cost = cost;
    }
}
