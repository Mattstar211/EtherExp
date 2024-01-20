package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class ResetLobby implements CommandExecutor {
    EtherExp plugin;
    public ResetLobby(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.isOp()){
            try {
                plugin.setXLobby(9.606411547450097);
                plugin.setYLobby(7.0);
                plugin.setZLobby(26.573462861292374);
                plugin.setYawLobby(-180);
                plugin.setPitchLobby(0);
                double xLobby = plugin.getXLobby();
                double yLobby = plugin.getYLobby();
                double zLobby = plugin.getZLobby();
                float yawLobby = plugin.getYawLobby();
                float pitchLobby = plugin.getPitchLobby();
                player.sendMessage(ChatColor.AQUA + "Точка успешно установлена!");
                System.out.println("EtherExp: Текущие координаты спавна: X: " + xLobby + " Y: " + yLobby + " Z: " + zLobby + " Yaw: " + yawLobby + " Pitch: " + pitchLobby);
                return true;
            } catch (Exception e) {
                EtherExp.sendErrorMessage(e, this.getClass().getName());
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
