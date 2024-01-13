package org.example1.etherexp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class Penis implements CommandExecutor {
    EtherExp plugin;
    public Penis(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        try {
            Player player = (Player) sender;
            if(!player.getWorld().getName().equals("world")) plugin.teleportToWorld(player);
            return true;
        } catch (Exception e) {
            EtherExp.sendErrorMessage(e, 19);
            return false;
        }
    }
}
