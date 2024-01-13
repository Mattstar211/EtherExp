package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class RemoveToBanList implements CommandExecutor {
    EtherExp plugin;
    RemoveToBanList(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length == 2) {
                try {
                    String namePlayer = args[1];
                    plugin.getNameBan().remove(namePlayer);
                    return true;
                } catch (Exception e) {
                    EtherExp.sendErrorMessage(e, 339);
                    return false;
                }
            }
            else player.sendMessage(ChatColor.GREEN + "Использование: /removetobanlist <ник>");
            return true;
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
