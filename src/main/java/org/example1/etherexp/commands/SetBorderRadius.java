package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;


public class SetBorderRadius implements CommandExecutor {
    EtherExp plugin;
    public SetBorderRadius(EtherExp plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(player.isOp()){
            try {
                if (args.length == 1) plugin.changeRadius(player, Double.parseDouble(args[0]));
                else player.sendMessage(ChatColor.GREEN + "Использование: /setborderradius <радиус>");
                return true;
            } catch (NumberFormatException e) {
                EtherExp.sendErrorMessage(e, 423);
                player.sendMessage(ChatColor.RED + "Вводи радиус через точку!");
                return false;
            }
        }else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
