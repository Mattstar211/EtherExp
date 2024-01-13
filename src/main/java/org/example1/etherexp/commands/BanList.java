package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class BanList implements CommandExecutor {
    EtherExp plugin;
    BanList(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            player.sendMessage("Бан список сервера: " + String.join(", ", plugin.getNameBan()));
            return true;
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
