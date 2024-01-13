package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class RestartBorder implements CommandExecutor {
    EtherExp plugin;
    public RestartBorder(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            try {
                plugin.changeRadius(player, plugin.getTemp_change_radius());
                plugin.changeAngle(player, plugin.getTemp_change_angle());
                for (Player pl : player.getWorld().getPlayers())
                    pl.sendMessage(ChatColor.YELLOW + "Пространство двигается!!");
                return true;
            } catch (Exception e) {
                EtherExp.sendErrorMessage(e, 23);
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
