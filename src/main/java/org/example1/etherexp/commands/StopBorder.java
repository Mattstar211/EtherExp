package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class StopBorder implements CommandExecutor {
    EtherExp plugin;
    StopBorder(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            try {
                plugin.setTemp_change_radius(plugin.getChange_radius());
                plugin.setTemp_change_angle(plugin.getChange_angle());
                plugin.changeRadius(player, 0.0);
                plugin.changeAngle(player, 0.0);
                for (Player pl : player.getWorld().getPlayers())
                    pl.sendMessage(ChatColor.YELLOW + "Пространство остановилось!");
                return true;
            } catch (Exception e) {
                EtherExp.sendErrorMessage(e, 26);
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
