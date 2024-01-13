package org.example1.etherexp;

import com.onarandombox.MultiversePortals.MVPortal;
import com.onarandombox.MultiversePortals.event.MVPortalEvent;
import me.NoChance.PvPManager.Commands.PvP;
import org.bukkit.*;
import me.NoChance.PvPManager.PvPlayer;
import me.NoChance.PvPManager.PvPManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.example1.etherexp.commands.*;
import org.example1.etherexp.commands.BanList;
import org.example1.etherexp.configuration.SaveLoadExample;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class EtherExp extends JavaPlugin implements Listener {
    private int worldBorderSize = 10;
    private double radius = 10;
    private double angle = 0;
    private double change_radius = 0.1;
    private double temp_change_radius = 0.0;
    private double change_angle = 0.0017;
    private double temp_change_angle = 0.0;
    private double change_angle_advancement = 0.0005;
    private double xLobby = 9.606411547450097;
    private double yLobby = 7.0;
    private double zLobby = 26.573462861292374;
    private float yawLobby = -180;
    private float pitchLobby = 0;
    private double xBorder = 0;
    private double yBorder = 0;
    private List<String> nameBan = new ArrayList<>();
    private List<String> nameAdmin = new ArrayList<>();
    private SaveLoadExample config;

    private static final List<String> advancementsList = Arrays.asList(
            "story/root",
            "story/mine_stone",
            "story/upgrade_tools",
            "story/smelt_iron",
            "story/obtain_armor",
            "story/lava_bucket",
            "story/iron_tools",
            "story/deflect_arrow",
            "story/form_obsidian",
            "story/mine_diamond",
            "story/enter_the_nether",
            "story/shiny_gear",
            "story/enchant_item",
            "story/cure_zombie_villager",
            "story/follow_ender_eye",
            "story/enter_the_end",
            "neither/root",
            "neither/return_to_sender",
            "nether/find_bastion",
            "nether/obtain_ancient_debris",
            "nether/fast_travel",
            "neither/find_fortress",
            "nether/obtain_crying_obsidian",
            "nether/distract_piglin",
            "nether/ride_strider",
            "nether/uneasy_alliance",
            "nether/loot_bastion",
            "neither/use_lodestone",
            "nether/netherite_armor",
            "nether/get_wither_skull",
            "nether/obtain_blaze_rod",
            "nether/charge_respawn_anchor",
            "nether/ride_strider_in_overworld_lava",
            "nether/explore_nether",
            "nether/summon_wither",
            "nether/brew_potion",
            "neither/create_beacon",
            "nether/all_options",
            "nether/create_full_beacon",
            "nether/all_effects",
            "end/root",
            "end/kill_dragon",
            "end/dragon_egg",
            "end/enter_end_gateway",
            "end/respawn_dragon",
            "end/dragon_breath",
            "end/find_end_city",
            "end/elytra",
            "end/levitate",
            "adventure/root",
            "adventure/voluntary_exile",
            "adventure/spyglass_at_parrot",
            "adventure/kill_a_mob",
            "adventure/read_power_of_chiseled_bookshelf",
            "adventure/trade",
            "adventure/trim_with_any_armor_pattern",
            "adventure/honey_block_slide",
            "adventure/ol_betsy",
            "adventure/lightning_rod_with_villager_no_fire",
            "adventure/fall_from_world_height",
            "adventure/salvage_sherd",
            "adventure/avoid_vibration",
            "adventure/sleep_in_bed",
            "adventure/hero_of_the_village",
            "adventure/spyglass_at_ghost",
            "adventure/throw_trident",
            "adventure/kill_mob_near_sculk_catalyst",
            "adventure/shoot_arrow",
            "adventure/kill_all_mobs",
            "adventure/totem_of_undying",
            "adventure/summon_iron_golem",
            "adventure/trade_at_world_height",
            "adventure/trim_with_all_exclusive_armor_patterns",
            "adventure/two_birds_one_arrow",
            "adventure/whos_the_pillager_now",
            "adventure/arbalistic",
            "adventure/craft_decorated_pot_using_only_sherds",
            "adventure/adventuring_time",
            "adventure/play_jukebox_in_meadows",
            "adventure/walk_on_powder_snow_with_leather_boots",
            "adventure/spyglass_at_dragon",
            "adventure/very_very_frightening",
            "adventure/sniper_duel",
            "adventure/bullseye",
            "husbandry/root",
            "husbandry/safely_harvest_honey",
            "husbandry/breed_an_animal",
            "husbandry/allay_deliver_item_to_player",
            "husbandry/ride_a_boat_with_a_goat",
            "husbandry/tame_an_animal",
            "husbandry/make_a_sign_glow",
            "husbandry/fishy_business",
            "husbandry/silk_touch_nest",
            "husbandry/tadpole_in_a_bucket",
            "husbandry/obtain_sniffer_egg",
            "husbandry/plant_seed",
            "husbandry/wax_on",
            "husbandry/bred_all_animals",
            "husbandry/allay_deliver_cake_to_note_block",
            "husbandry/complete_catalogue",
            "husbandry/tactical_fishing",
            "husbandry/leash_all_frog_variants",
            "husbandry/feed_snifflet",
            "husbandry/balanced_diet",
            "husbandry/obtain_netherite_hoe",
            "husbandry/wax_off",
            "husbandry/axolotl_in_a_bucket",
            "husbandry/froglights",
            "husbandry/plant_any_sniffer_seed",
            "husbandry/kill_axolotl_target"
    );
    public double getChangeAngleAdvancement() {
        return this.change_angle_advancement;
    }
    public void setChangeAngleAdvancement(double changeAngleAdvancement) {this.change_angle_advancement = changeAngleAdvancement;}
    public double getXLobby() {
        return xLobby;
    }
    public void setXLobby(double xLobby) {
        this.xLobby = xLobby;
    }
    public double getYLobby() {
        return yLobby;
    }
    public void setYLobby(double yLobby) {
        this.yLobby = yLobby;
    }
    public double getZLobby() {
        return zLobby;
    }
    public void setZLobby(double zLobby) {
        this.zLobby = zLobby;
    }
    public float getYawLobby() {
        return yawLobby;
    }
    public void setYawLobby(float yawLobby) {
        this.yawLobby = yawLobby;
    }
    public float getPitchLobby() {
        return pitchLobby;
    }
    public void setPitchLobby(float pitchLobby) {
        this.pitchLobby = pitchLobby;
    }
    public List<String> getNameBan() {
        return nameBan;
    }
    public void setNameBan(List<String> nameBan){
        this.nameBan = nameBan;
    }
    public List<String> getNameAdmin() {return nameAdmin;}
    public void setNameAdmin(List<String> nameAdmin){
        this.nameAdmin = nameAdmin;
    }
    public int getWorldBorderSize() {
        return worldBorderSize;
    }
    public void setWorldBorderSize(int worldBorderSize) {
        this.worldBorderSize = worldBorderSize;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {this.radius = radius;}
    public double getAngle() {
        return angle;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }
    public double getChange_radius(){return this.change_radius;}
    public void setChange_radius(double change_radius){
        this.change_radius = change_radius;
    }
    public double getChange_angle(){
        return this.change_angle;
    }
    public void setChange_angle(double change_angle){
        this.change_angle = change_angle;
    }
    public void setXborder(double xBorder){this.xBorder = xBorder;}
    public double getXborder(){return this.xBorder;}
    public void setYborder(double yBorder){this.yBorder = yBorder;}
    public double getYborder(){return this.yBorder;}
    public void setTemp_change_radius(double temp_change_radius){this.temp_change_radius = temp_change_radius;}
    public double getTemp_change_radius(){return temp_change_radius;}
    public void setTemp_change_angle(double temp_change_angle){this.temp_change_angle = temp_change_angle;}
    public double getTemp_change_angle(){return temp_change_angle;}
    @Override
    public void onEnable() {
        config = new SaveLoadExample();
        try {
            nameAdmin.add("PavelDurov");
            nameAdmin.add("Mattstar");
            nameAdmin.add("Reqwenx");
            config.loadConfigurationFromFile("plugins/EtherExp", "config.yml", this);
            WorldBorder worldBorder = getWorldCast("world").getWorldBorder();
            worldBorder.setCenter(xBorder, yBorder);
        } catch (Exception e) {
            sendErrorMessage(e, 163);
        }
        getServer().getPluginManager().registerEvents(this, this);
        this.initCommand();
        new BukkitRunnable() {
            @Override
            public void run() {
                setCenterBorder();
            }
        }.runTaskTimer(this, 0, 10);
    }

    @EventHandler
    public void onPlayerPortal(MVPortalEvent event) {
        try {
            Player player = event.getTeleportee();
            MVPortal portal = event.getSendingPortal();
            System.out.println("Игрок " + player.getName() + " зашел в портал " + event.getTeleportee().getName());
            sendMessageAdmin("Игрок " + player.getName() + " зашел в портал " + event.getTeleportee().getName());
            if (Objects.equals(portal.getName(), "portal1")) teleportToWorld(player);
        } catch (Exception e) {
            sendErrorMessage(e, 175);
        }
    }

    private void initCommand() {
        try {
            this.registerCommand(this.getCommand("setborderradius"), new SetBorderRadius(this));
            this.registerCommand(this.getCommand("setborderangle"), new SetBorderAngle(this));
            this.registerCommand(this.getCommand("resetborderangle"), new ResetBorderAngle(this));
            this.registerCommand(this.getCommand("resetborderradius"), new ResetBorderRadius(this));
            this.registerCommand(this.getCommand("getworld"), new GetWorld(this));
            this.registerCommand(this.getCommand("penis"), new Penis(this));
            this.registerCommand(this.getCommand("lobby"), new Lobby(this));
            this.registerCommand(this.getCommand("hub"), new Lobby(this));
            this.registerCommand(this.getCommand("setlobby"), new SetLobby(this));
            this.registerCommand(this.getCommand("resetlobby"), new ResetLobby(this));
            this.registerCommand(this.getCommand("addtobanlist"), new AddToBanList(this));
            this.registerCommand(this.getCommand("removetobanlist"), new RemoveToBanList(this));
            this.registerCommand(this.getCommand("addtoadminlist"), new AddToAdminList(this));
            this.registerCommand(this.getCommand("removetoadminlist"), new RemoveToAdminList(this));
            this.registerCommand(this.getCommand("addopadmin"), new AddOpAdmin(this));
            this.registerCommand(this.getCommand("setchangeangle"), new SetChangeAngle(this));
            this.registerCommand(this.getCommand("stopborder"), new StopBorder(this));
            this.registerCommand(this.getCommand("restartborder"), new RestartBorder(this));
            this.registerCommand(this.getCommand("banlist"), new BanList(this));
            this.registerCommand(this.getCommand("adminlist"), new AdminList(this));
            this.registerCommand(this.getCommand("etherexp"), new EtherExpReload(this, this.config));
        } catch (Exception e) {
            sendErrorMessage(e, 187);
        }
    }
    private void registerCommand(PluginCommand command, CommandExecutor executor) {
        if (command != null) {
            command.setExecutor(executor);
        }
    }
    public static void sendErrorMessage(Exception e, int string) {
        System.out.println("EtherExp: Ошибка " + e.getMessage() + " Строка: " + string);
    }

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        try {
            String advancementName = event.getAdvancement().getKey().getKey();
            World world = getWorldCast("world");
            assert world != null;
            WorldBorder worldBorder = world.getWorldBorder();
            if (advancementsList.contains(advancementName)) {
                worldBorderSize = (int) (Math.ceil(worldBorder.getSize() / 10) * 10) + 10;
                this.change_angle += change_angle_advancement;
                expandWorldBorder(worldBorder, worldBorderSize);
                for (Player player : world.getPlayers())
                    player.sendMessage(ChatColor.YELLOW + "Пространство расширяется. " + "Текущее значение барьера: " + worldBorderSize);
                System.out.println("Текущее значение барьера: " + worldBorderSize);
            }
        } catch (Exception e) {
            sendErrorMessage(e, 211);
        }
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        try {
            killBanPlayer(player);
        } catch (Exception e) {
            sendErrorMessage(e, 220);
        }
    }

    private void killBanPlayer(Player player) {
        if (nameBan.contains(player.getName())) {
            if (player.getHealth() >= 0.5)
                player.setHealth(player.getHealth() - 0.5);
            else player.setHealth(0.0);
            if (player.isOp()) player.setOp(false);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            teleportToLobby(event.getPlayer());
        } catch (Exception e) {
            sendErrorMessage(e, 239);
        }
    }

    public void teleportToLobby(Player player) {
        try {
            World worldLobby = getWorldCast("lobby");
            Location lobbyLocation = new Location(worldLobby, xLobby, yLobby, zLobby, yawLobby, pitchLobby);
            player.teleport(lobbyLocation);
            player.sendMessage(ChatColor.AQUA + "Вы были телепортированы в лобби!");
            System.out.println("Игрок " + player.getName() + " телепортирован в лобби");
        } catch (Exception e) {
            sendErrorMessage(e, 251);
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
                System.out.println("EtherExp: Игрок " + player.getName() + " телепортирован в мир World");
            }
        } catch (Exception e) {
            sendErrorMessage(e, 438);
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

    private void expandWorldBorder(WorldBorder worldBorder, int newWorldBorderSize) {
        worldBorder.setSize(newWorldBorderSize, 15);
    }

    public void changeAngle(Player player, double new_change_angle) {
        try {
            player.sendMessage("Установлено значение угла: " + new_change_angle);
            change_angle = new_change_angle;
            System.out.println("Текущее значение угла: " + change_angle);
        } catch (Exception e) {
            sendErrorMessage(e, 490);
        }
    }

    public void changeRadius(Player player, double new_change_radius) {
        try {
            player.sendMessage("Установлен радиус барьера: " + new_change_radius);
            change_radius = new_change_radius;
            System.out.println("Текущее значение радиуса: " + change_radius);
        } catch (Exception e) {
            sendErrorMessage(e, 499);
        }
    }


    @Override
    public void onDisable() {
        WorldBorder border =  Objects.requireNonNull(Bukkit.getWorld("world")).getWorldBorder();
        xBorder = border.getCenter().getX();
        yBorder = border.getCenter().getY();
        config.saveConfigurationToFile("plugins/EtherExp", "config.yml", this);
    }

    private void setCenterBorder() {
        try {
            WorldBorder worldBorder = Bukkit.getWorlds().get(0).getWorldBorder();
            double newWorldBorderX = radius * Math.cos(angle);
            double newWorldBorderY = radius * Math.sin(angle);
            worldBorder.setCenter(newWorldBorderX, newWorldBorderY);
            radius += change_radius;
            angle += change_angle;
        } catch (Exception e) {
            sendErrorMessage(e, 518);
        }
    }

    private void sendMessageAdmin(String message) {
        try {
            for (String name : nameAdmin) {
                Player player = getPlayerByName(name);
                if (player != null)
                    player.sendMessage(message);
            }
        } catch (Exception e) {
            sendErrorMessage(e, 530);
        }
    }
}
