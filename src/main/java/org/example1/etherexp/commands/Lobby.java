package org.example1.etherexp.commands;

import me.NoChance.PvPManager.PvPManager;
import me.NoChance.PvPManager.PvPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class Lobby implements CommandExecutor {
    EtherExp plugin;
    public Lobby(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        try {
            if (!isInCombat(player)) plugin.teleportToLobby(player);
            return true;
        } catch (Exception e) {
            EtherExp.sendErrorMessage(e, this.getClass().getName());
            return false;
        }
    }
    public boolean isInCombat(Player player) {
        if(isPluginLoaded("PvPManager")) {
            System.out.println("EtherExp: Loaded");
            PvPlayer pvPlayer = PvPManager.getInstance().getPlayerHandler().get(player);
            return pvPlayer.isInCombat();
        }
        return false;
    }
    private boolean isPluginLoaded(String name){
        PluginManager pluginManager = Bukkit.getPluginManager();
        Plugin yourPlugin = pluginManager.getPlugin(name);

        return (yourPlugin != null && yourPlugin.isEnabled());

    }
}
