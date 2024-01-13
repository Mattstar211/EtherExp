package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class AdminList implements CommandExecutor {
    EtherExp plugin;
    AdminList(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.isOp()){
            player.sendMessage("Админы сервера: " + String.join(", ", plugin.getNameAdmin()));
            return true;
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
