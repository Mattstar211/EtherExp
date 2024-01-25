package org.example1.etherexp;

import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.example1.etherexp.commands.*;
import org.example1.etherexp.commands.BanList;
import org.example1.etherexp.configuration.SaveLoadExample;
import org.example1.etherexp.events.PlayerEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class EtherExp extends JavaPlugin {
    private boolean manuallyDisabled = false;
    private int worldBorderSize = 10;
    private double radius = 10;
    private double angle = 0;
    private double change_radius = 0.1;
    private double temp_change_radius = 0.0;
    private double change_angle = 0.0017;
    private double temp_change_angle = 0.0;
    private int change_angle_percent = 1;
    private double xLobby = 5.456;
    private double yLobby = 29.0;
    private double zLobby = -21.443;
    private Location position1Portal;
    private Location position2Portal;
    private float yawLobby = -180;
    private float pitchLobby = 0;
    private double xBorder = 0;
    private double zBorder = 0;
    private int percentNarrWorld = 15;
    private int periodNarrWorld = 1;//Минуты
    private List<String> nameBan = new ArrayList<>();
    private List<String> nameAdmin = new ArrayList<>();
    private SaveLoadExample config;
    public boolean getManuallyDisabled(){return this.manuallyDisabled;}
    public void setManuallyDisabled(boolean manuallyDisabled){ this.manuallyDisabled = manuallyDisabled;}
    public int getPeriodNarrWorld(){return  this.periodNarrWorld;}
    public void setPeriodNarrWorld(int periodNarrWorld){this.periodNarrWorld = periodNarrWorld;}
    public int getPercentNarrWorld(){return  this.percentNarrWorld;}
    public void setPercentNarrWorld(int periodNarrWorld){this.percentNarrWorld = periodNarrWorld;}
    public int getChange_angle_percent() {return this.change_angle_percent;}
    public void setChange_angle_percent(int changeAngleAdvancement) {this.change_angle_percent = changeAngleAdvancement;}
    public double getXLobby() {return xLobby;}
    public void setXLobby(double xLobby) {this.xLobby = xLobby;}
    public double getYLobby() {return yLobby;}
    public void setYLobby(double yLobby) {this.yLobby = yLobby;}
    public double getZLobby() {return zLobby;}
    public void setZLobby(double zLobby) {this.zLobby = zLobby;}
    public float getYawLobby() {return yawLobby;}
    public void setYawLobby(float yawLobby) {this.yawLobby = yawLobby;}
    public float getPitchLobby() {return pitchLobby;}
    public void setPitchLobby(float pitchLobby) {this.pitchLobby = pitchLobby;}
    public List<String> getNameBan() {return nameBan;}
    public void setNameBan(List<String> nameBan){this.nameBan = nameBan;}
    public List<String> getNameAdmin() {return nameAdmin;}
    public void setNameAdmin(List<String> nameAdmin){this.nameAdmin = nameAdmin;}
    public int getWorldBorderSize() {return worldBorderSize;}
    public void setWorldBorderSize(int worldBorderSize) {this.worldBorderSize = worldBorderSize;}
    public double getRadius() {return radius;}
    public void setRadius(double radius) {this.radius = radius;}
    public double getAngle() {return angle;}
    public void setAngle(double angle) {this.angle = angle;}
    public double getChange_radius(){return this.change_radius;}
    public void setChange_radius(double change_radius){this.change_radius = change_radius;}
    public double getChange_angle(){return this.change_angle;}
    public void setChange_angle(double change_angle){this.change_angle = change_angle;}
    public void setXborder(double xBorder){this.xBorder = xBorder;}
    public double getXborder(){return this.xBorder;}
    public void setZborder(double yBorder){this.zBorder = yBorder;}
    public double getZborder(){return this.zBorder;}
    public void setTemp_change_radius(double temp_change_radius){this.temp_change_radius = temp_change_radius;}
    public double getTemp_change_radius(){return temp_change_radius;}
    public void setTemp_change_angle(double temp_change_angle){this.temp_change_angle = temp_change_angle;}
    public double getTemp_change_angle(){return temp_change_angle;}
    public Location getPosition1Portal(){return position1Portal;}
    public Location getPosition2Portal(){return position2Portal;}
    public void setPosition1Portal(Location position1Portal){this.position1Portal = position1Portal;}
    public void setPosition2Portal(Location position2Portal){this.position2Portal = position2Portal;}
    public void setPosition1Portal(int x, int y, int z){this.position1Portal = new Location(getWorldCast("lobby"), x, y, z);}
    public void setPosition2Portal(int x, int y, int z){this.position2Portal = new Location(getWorldCast("lobby"), x, y, z);}
    @Override
    public void onEnable() {
        config = new SaveLoadExample();
        try {
            System.out.println("EtherExp: worldBorderSize1: " + worldBorderSize);
            config.loadConfigurationFromFile("plugins/EtherExp", "config.yml", this);
            if(position1Portal == null){
                position1Portal = new Location(getWorldCast("lobby"), 4, 27, 1);
                position2Portal = new Location(getWorldCast("lobby"), 7, 31, 2);
            }
            System.out.println("EtherExp: worldBorderSize2: " + worldBorderSize);
            WorldBorder worldBorder = getWorldCast("world").getWorldBorder();
            worldBorder.setCenter(xBorder, zBorder);
            worldBorder.setSize(worldBorderSize, 15);
        } catch (Exception e) {
            sendErrorMessage(e, "onEnable error");
        }
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);
        this.initCommand();
        new BukkitRunnable() {
            @Override
            public void run() {
                setCenterBorder();
            }
        }.runTaskTimer(this, 0, 10);
        new BukkitRunnable() {
            @Override
            public void run() {
                setSizeBorder();
            }
        }.runTaskTimer(this, 600, periodNarrWorld* 1200L);
        new BukkitRunnable() {
            @Override
            public void run() {
                config.saveConfigurationToFile("plugins/EtherExp", "config.yml", EtherExp.this);
            }
        }.runTaskTimer(this, 600, 12000L);
        System.out.println(ChatColor.GREEN + this.getDescription().getFullName() + " enabled!");
    }
    public void updatePlugin(){
        World world = Bukkit.getWorld("world");
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorderSize = (int) worldBorder.getSize();
        Location locationBorder = worldBorder.getCenter();
        xBorder = locationBorder.getX();
        zBorder = locationBorder.getZ();
    }
    private void setSizeBorder(){
        try {
            World world = getWorldCast("world");
            WorldBorder worldBorder = world.getWorldBorder();
            if(worldBorder.getSize() <= 25) return;
            int newWorldBorderSize = (int) (worldBorder.getSize() - (worldBorder.getSize() * percentNarrWorld)/100);
            System.out.println(ChatColor.GREEN + "EtherExp: Значение барьера уменьшилось: " + worldBorder.getSize() + " -> " + newWorldBorderSize);
            worldBorder.setSize(newWorldBorderSize, 15);
            worldBorderSize = newWorldBorderSize;
            for (Player player : world.getPlayers())
                player.sendMessage(ChatColor.YELLOW + "Пространство сужается. " + "Текущее значение барьера: " + newWorldBorderSize);
        } catch (Exception e) {
            sendErrorMessage(e, "setSizeBorder error");
        }
    }
    private void initCommand() {
        try {
            this.registerCommand(this.getCommand("setborderradius"), new SetBorderRadius(this));
            this.registerCommand(this.getCommand("setborderangle"), new SetBorderAngle(this));
            this.registerCommand(this.getCommand("setperiodnarrworld"), new SetPeriodNarrWorld(this));
            this.registerCommand(this.getCommand("setpercentnarrworld"), new EtherExpReload(this, this.config));
            this.registerCommand(this.getCommand("setlobby"), new SetLobby(this));
            this.registerCommand(this.getCommand("setpercentangle"), new SetPercentAngle(this));
            this.registerCommand(this.getCommand("resetborderangle"), new ResetBorderAngle(this));
            this.registerCommand(this.getCommand("resetborderradius"), new ResetBorderRadius(this));
            this.registerCommand(this.getCommand("resetlobby"), new ResetLobby(this));
            this.registerCommand(this.getCommand("addtobanlist"), new AddToBanList(this));
            this.registerCommand(this.getCommand("addtoadminlist"), new AddToAdminList(this));
            this.registerCommand(this.getCommand("addopadmin"), new AddOpAdmin(this));
            this.registerCommand(this.getCommand("removetobanlist"), new RemoveToBanList(this));
            this.registerCommand(this.getCommand("removetoadminlist"), new RemoveToAdminList(this));
            this.registerCommand(this.getCommand("getworld"), new GetWorld(this));
            this.registerCommand(this.getCommand("penis"), new Penis(this));
            this.registerCommand(this.getCommand("lobby"), new Lobby(this));
            this.registerCommand(this.getCommand("hub"), new Lobby(this));
            this.registerCommand(this.getCommand("stopborder"), new StopBorder(this));
            this.registerCommand(this.getCommand("restartborder"), new RestartBorder(this));
            this.registerCommand(this.getCommand("banlist"), new BanList(this));
            this.registerCommand(this.getCommand("adminlist"), new AdminList(this));
            this.registerCommand(this.getCommand("etherexp"), new EtherExpReload(this, this.config));
        } catch (Exception e) {
            sendErrorMessage(e, "initCommand error");
        }
    }
    private void registerCommand(PluginCommand command, CommandExecutor executor) {
        if (command != null) {
            command.setExecutor(executor);
        }
    }
    public static void sendErrorMessage(Exception e, String string) {
        System.out.println(ChatColor.RED + "EtherExp: Ошибка " + e.getMessage() + " Строка: " + string);
    }
    public void teleportToLobby(Player player) {
        try {
            World worldLobby = getWorldCast("lobby");
            Location lobbyLocation = new Location(worldLobby, xLobby, yLobby, zLobby, yawLobby, pitchLobby);
            player.teleport(lobbyLocation);
            player.sendMessage(ChatColor.AQUA + "Вы были телепортированы в лобби!");
            System.out.println(ChatColor.GREEN + "Игрок " + player.getName() + " телепортирован в лобби");
        } catch (Exception e) {
            sendErrorMessage(e, "teleportToLobby error");
        }
    }
    public void teleportToWorld(Player player) {
        try {
            String nameWorld = "world";
            World world = getWorldCast(nameWorld);
            sendMessageAdmin(ChatColor.AQUA + "Созидатель " + player.getName() + " пришел в этот мир!");
            if (world != null) {
                final Location newLocation = getLocationBorder(world);
                player.teleport(newLocation);
                System.out.println(ChatColor.GREEN + "EtherExp: Игрок " + player.getName() + " телепортирован в мир World");
            }
        } catch (Exception e) {
            sendErrorMessage(e, "teleportToWorld error");
        }
    }
    public static Player getPlayerByName(String playerName) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.getName().equalsIgnoreCase(playerName)) {
                return onlinePlayer;
            }
        }
        return null; // Если игрок не найден
    }
    @NotNull
    static Location getLocationBorder(World world) {
        Random random = new Random();
        final Location locationWorldBorder = world.getWorldBorder().getCenter();
        int maxValue = (int) (world.getWorldBorder().getSize() / 3);
        int minValue = -maxValue;
        final double x = locationWorldBorder.getX() + random.nextInt((maxValue - minValue) + 1) + minValue;
        final double z = locationWorldBorder.getZ() + random.nextInt((maxValue - minValue) + 1) + minValue;
        final double y = world.getHighestBlockYAt((int) x, (int) z);
        return new Location(world, x, y, z);
    }

    public World getWorldCast(String name) {
        return Bukkit.getWorld(name);
    }
    public void changeAngle(Player player, double new_change_angle) {
        try {
            player.sendMessage("Установлено значение угла: " + new_change_angle);
            change_angle = new_change_angle;
            System.out.println(ChatColor.GREEN + "Текущее значение угла: " + change_angle);
        } catch (Exception e) {
            sendErrorMessage(e, "changeAngle error");
        }
    }
    public void changeRadius(Player player, double new_change_radius) {
        try {
            player.sendMessage("Установлен радиус барьера: " + new_change_radius);
            change_radius = new_change_radius;
            System.out.println(ChatColor.GREEN + "Текущее значение радиуса: " + change_radius);
        } catch (Exception e) {
            sendErrorMessage(e, "changeRadius error");
        }
    }
    @Override
    public void onDisable() {
        WorldBorder border =  Objects.requireNonNull(Bukkit.getWorld("world")).getWorldBorder();
        xBorder = border.getCenter().getX();
        zBorder = border.getCenter().getY();
        worldBorderSize = (int) border.getSize();
        if(!manuallyDisabled) config.saveConfigurationToFile("plugins/EtherExp", "config.yml", this);
    }
    private void setCenterBorder() {
        try {
            WorldBorder worldBorder = getWorldCast("world").getWorldBorder();
            double newWorldBorderX = radius * Math.cos(angle);
            double newWorldBorderY = radius * Math.sin(angle);
            worldBorder.setCenter(newWorldBorderX, newWorldBorderY);
            radius += change_radius;
            angle += change_angle;
        } catch (Exception e) {
            sendErrorMessage(e, "setCenterBorder error");
        }
    }
    public void sendMessageAdmin(String message) {
        try {
            for (String name : nameAdmin) {
                Player player = getPlayerByName(name);
                if (player != null)
                    player.sendMessage(message);
            }
        } catch (Exception e) {
            sendErrorMessage(e, "sendMessageAdmin error");
        }
    }
}
