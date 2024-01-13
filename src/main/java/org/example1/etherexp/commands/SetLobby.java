package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class SetLobby implements CommandExecutor {
    EtherExp plugin;
    public SetLobby(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.isOp()){
            try {
                Location lobbyLocation = player.getLocation();
                plugin.setXLobby(lobbyLocation.getX());
                plugin.setYLobby(lobbyLocation.getY());
                plugin.setZLobby(lobbyLocation.getZ());
                plugin.setYawLobby(lobbyLocation.getYaw());
                plugin.setPitchLobby(lobbyLocation.getPitch());
                player.sendMessage(ChatColor.AQUA + "Точка успешно установлена!");
                System.out.println("EtherExp: Текущие координаты спавна: X: " + lobbyLocation.getX() + " Y: " + lobbyLocation.getY() + " Z: " + lobbyLocation.getZ() + " Yaw: " + lobbyLocation.getYaw() + " Pitch: " + lobbyLocation.getPitch());
                return true;
            } catch (Exception e) {
                EtherExp.sendErrorMessage(e, 395);
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
