package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class GetWorld implements CommandExecutor {
    EtherExp plugin;
    public GetWorld(EtherExp plugin){this.plugin = plugin;}

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        System.out.println("!!!" + args[0]);
        if(player.isOp()){
            try {
                String nameWorld = args[0];
                World world = plugin.getWorldCast(nameWorld);
                if (world != null) player.sendMessage("Мир " + nameWorld + " успешно получен!");
                else player.sendMessage("Мир " + nameWorld + " не существует!");
                return true;
            } catch (Exception e) {
                EtherExp.sendErrorMessage(e, this.getClass().getName());
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }

}
