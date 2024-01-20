package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class SetPercentNarrWorld implements CommandExecutor {
    EtherExp plugin;
    public SetPercentNarrWorld(EtherExp plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            if(args.length == 1){
                try{
                    int newPercent = Integer.parseInt(args[0]);
                    plugin.setPercentNarrWorld(newPercent);
                    player.sendMessage( ChatColor.GREEN + "Процент успешно изменен!");
                    return true;
                }catch (Exception e){
                    EtherExp.sendErrorMessage(e, this.getClass().getName());
                    player.sendMessage( ChatColor.RED + "Не удалось установить новый процент!");
                    return false;
                }
            }else{
                player.sendMessage( ChatColor.YELLOW + "Использование: setpercentnarrworld <%>");
                return true;
            }
        }else{
            player.sendMessage( ChatColor.RED + "Команду могут использовать только администарторы сервера!");
            return false;
        }
    }
}
