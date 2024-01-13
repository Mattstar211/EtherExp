package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class ResetBorderAngle implements CommandExecutor {
    EtherExp plugin;
    ResetBorderAngle(EtherExp plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.isOp()) {
            try {
                plugin.setChange_angle(0.006);
                System.out.println("Текущее значение угла: 0.006");
                return true;
            } catch (Exception e) {
                EtherExp.sendErrorMessage(e, 415);
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
