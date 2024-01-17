package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.example1.etherexp.configuration.SaveLoadExample;
import org.jetbrains.annotations.NotNull;

public class SetPeriodNarrWorld implements CommandExecutor {
    EtherExp plugin;
    public SetPeriodNarrWorld(EtherExp plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            if(args.length == 1){
                try{
                    int newPeriod = Integer.parseInt(args[0]);
                    plugin.setPeriodNarrWorld(newPeriod);
                    player.sendMessage( ChatColor.GREEN + "Период успешно изменен! Перезагрузите плагин!");
                    return true;
                }catch (Exception e){
                    EtherExp.sendErrorMessage(e, 26);
                    player.sendMessage( ChatColor.RED + "Не удалось установить новый период!");
                    return false;
                }
            }else{
                player.sendMessage( ChatColor.YELLOW + "Использование: setperiodnarrworld <значение периода>");
                return true;
            }
        }else{
            player.sendMessage( ChatColor.RED + "Команду могут использовать только администарторы сервера!");
            return false;
        }
    }
}
