package org.example1.etherexp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.example1.etherexp.EtherExp;
import org.example1.etherexp.configuration.SaveLoadExample;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EtherExpReload implements CommandExecutor {
    EtherExp plugin;
    SaveLoadExample config;
    public EtherExpReload(EtherExp plugin, SaveLoadExample config){
        this.plugin = plugin;
        this.config = config;
    }
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            if(args.length == 1 && Objects.equals(args[0], "reload")){
                try {
                    Plugin plg = Bukkit.getPluginManager().getPlugin("EtherExp");
                    if (plg!=null){
                        plugin.setManuallyDisabled(true);
                        SaveLoadExample.updateConfigFile("plugins/EtherExp/config.yml", "xBorder", Double.toString(plugin.getXborder()));
                        SaveLoadExample.updateConfigFile("plugins/EtherExp/config.yml", "zBorder", Double.toString(plugin.getZborder()));
                        Bukkit.getPluginManager().disablePlugin(plg);
                        Bukkit.getPluginManager().enablePlugin(plg);
                        return true;
                    }else{
                        player.sendMessage(ChatColor.RED + "Произошла ошибка при перезагрузке плагина!");
                        return false;
                    }
                }catch (Exception e){
                    EtherExp.sendErrorMessage(e, 489);
                    player.sendMessage(ChatColor.RED + "Ошибка загрузки конфига!");
                    return false;
                }
            }else{
                player.sendMessage(ChatColor.GREEN + "Использование: /etherexp reload");
                return true;
            }
        }
        else{
            player.sendMessage(ChatColor.RED + "Команду могут использовать только администраторы сервера!");
            return false;
        }
    }
}
