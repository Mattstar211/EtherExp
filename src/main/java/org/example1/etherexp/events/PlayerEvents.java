package org.example1.etherexp.events;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiversePortals.MVPortal;
import com.onarandombox.MultiversePortals.event.MVPortalEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import java.util.Random;

import static org.example1.etherexp.EtherExp.sendErrorMessage;
import static org.example1.etherexp.utils.WorldUtils.getLocationBorder;

public class PlayerEvents {
    @EventHandler
    public void onPlayerPortal(MVPortalEvent event) {
        try {
            Player player = event.getTeleportee();
            MVPortal portal = event.getSendingPortal();
            System.out.println("Игрок " + player.getName() + " зашел в портал " + event.getTeleportee().getName());
            sendMessageAdmin("Игрок " + player.getName() + " зашел в портал " + event.getTeleportee().getName());
            if (Objects.equals(portal.getName(), "portal1")) teleportToWorld(player);
        } catch (Exception e) {
            sendErrorMessage(e, 175);
        }
    }
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String[] split = event.getMessage().split(" ");
        Player player = event.getPlayer();
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            teleportToLobby(event.getPlayer());
        } catch (Exception e) {
            sendErrorMessage(e, 239);
        }
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        try {
            killBanPlayer(player);
        } catch (Exception e) {
            sendErrorMessage(e, 220);
        }
    }
    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        try {
            String advancementName = event.getAdvancement().getKey().getKey();
            World world = getWorldCast("world");
            assert world != null;
            WorldBorder worldBorder = world.getWorldBorder();
            if (advancementsList.contains(advancementName)) {
                worldBorderSize = (int) (Math.ceil(worldBorder.getSize() / 10) * 10) + 10;
                this.change_angle += change_angle_advancement;
                expandWorldBorder(worldBorder, worldBorderSize);
                for (Player player : world.getPlayers())
                    player.sendMessage(ChatColor.YELLOW + "Пространство расширяется. " + "Текущее значение барьера: " + worldBorderSize);
                System.out.println("Текущее значение барьера: " + worldBorderSize);
            }
        } catch (Exception e) {
            sendErrorMessage(e, 211);
        }
    }
    private void killBanPlayer(Player player) {
        if (nameBan.contains(player.getName())) {
            if (player.getHealth() >= 0.5)
                player.setHealth(player.getHealth() - 0.5);
            else player.setHealth(0.0);
            if (player.isOp()) player.setOp(false);
        }
    }
    public void teleportToWorld(Player player) {
        try {
            String nameWorld = "world";
            World world = getWorldCast(nameWorld);
            sendMessageAdmin(ChatColor.AQUA + "Созидатель " + player.getName() + " пришел в этот мир!");
            if (world != null) {
                final Location newLocation = getLocationBorder(world);
                player.teleport(newLocation);
                System.out.println("EtherExp: Игрок " + player.getName() + " телепортирован в мир World");
            }
        } catch (Exception e) {
            sendErrorMessage(e, 438);
        }
    }
    private void teleportToLobby(Player player) {
        try {
            World worldLobby = getWorldCast("lobby");
            Location lobbyLocation = new Location(worldLobby, xLobby, yLobby, zLobby, yawLobby, pitchLobby);
            player.teleport(lobbyLocation);
            player.sendMessage(ChatColor.AQUA + "Вы были телепортированы в лобби!");
            System.out.println("Игрок " + player.getName() + " телепортирован в лобби");
        } catch (Exception e) {
            sendErrorMessage(e, 251);
        }
    }
    public World getWorldCast(String name) {
        MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        if (multiverseCore == null) {
            return null;
        }
        MultiverseWorld nameWorld = multiverseCore.getMVWorldManager().getMVWorld(name);
        if (nameWorld == null) {
            return null;
        }
        return nameWorld.getCBWorld();
    }
    private void sendMessageAdmin(String message) {
        try {
            for (String name : nameadmin) {
                Player player = getPlayerByName(name);
                if (player != null)
                    player.sendMessage(message);
            }
        } catch (Exception e) {
            sendErrorMessage(e, 530);
        }
    }
    public static Player getPlayerByName(String playerName) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.getName().equalsIgnoreCase(playerName)) {
                return onlinePlayer;
            }
        }
        return null; // Если игрок не найден
    }
    public void expandWorldBorder(WorldBorder worldBorder, int newWorldBorderSize) {
        worldBorder.setSize(newWorldBorderSize, 15);
    }
}
