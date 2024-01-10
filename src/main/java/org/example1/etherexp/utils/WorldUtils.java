package org.example1.etherexp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static org.example1.etherexp.EtherExp.sendErrorMessage;

public class WorldUtils {
    @NotNull
    public static Location getLocationBorder(World world) {
        Random random = new Random();
        final Location locationWorldBorder = world.getWorldBorder().getCenter();
        int maxValue = (int) (world.getWorldBorder().getSize() / 3);
        int minValue = -maxValue;
        final double x = locationWorldBorder.getX() + random.nextInt((maxValue - minValue) + 1) + minValue;
        final double z = locationWorldBorder.getZ() + random.nextInt((maxValue - minValue) + 1) + minValue;
        final double y = world.getHighestBlockYAt((int) x, (int) z);
        return new Location(world, x, y, z);
    }
}
