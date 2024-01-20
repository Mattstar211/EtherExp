package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class SetPercentAngle implements CommandExecutor {
    EtherExp plugin;
    public SetPercentAngle(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length == 1) {
                try {
                    int new_change_angle = Integer.parseInt(args[0]);
                    plugin.setChange_angle_percent(new_change_angle);
                    player.sendMessage("Приращение угла изменено на " + new_change_angle);
                    return true;
                } catch (NumberFormatException e) {
                    EtherExp.sendErrorMessage(e, this.getClass().getName());
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
