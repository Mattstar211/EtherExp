package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class SetBorderAngle implements CommandExecutor {
    EtherExp plugin;
    public SetBorderAngle(EtherExp plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.isOp()){
            try {
                if (args.length == 1) plugin.changeAngle(player, Double.parseDouble(args[0]));
                else player.sendMessage(ChatColor.GREEN + "Использование: /setborderangle <угол>");
                return true;
            } catch (NumberFormatException e) {
                EtherExp.sendErrorMessage(e, this.getClass().getName());
                player.sendMessage(ChatColor.RED + "Вводи угол через точку!");
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
