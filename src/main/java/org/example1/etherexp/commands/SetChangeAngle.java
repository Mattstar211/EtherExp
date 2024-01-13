package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class SetChangeAngle implements CommandExecutor {
    EtherExp plugin;
    SetChangeAngle(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length == 2) {
                try {
                    double new_change_angle = Double.parseDouble(args[1]);
                    plugin.setChangeAngleAdvancement(new_change_angle);
                    player.sendMessage("Приращение угла изменено на " + new_change_angle);
                    return true;
                } catch (NumberFormatException e) {
                    EtherExp.sendErrorMessage(e, 24);
                    return false;
                }
            }
            else player.sendMessage(ChatColor.GREEN + "Использование: /setchangeangle <значение>");
            return true;
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
