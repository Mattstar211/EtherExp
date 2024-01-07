package org.example1.etherexp;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class EtherExp extends JavaPlugin implements Listener {
    private int worldBorderSize = 10;
    private double radius = 10;
    private double angle = 0;
    private double change_radius = 0.1;
    private double change_angle = 0.006;
    private Location lobbyLocation;
    private static final List<String> stringList = Arrays.asList(
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
        lobbyLocation = new Location(getWorld("lobby"),9.606411547450097, 7.0, 26.573462861292374);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        initCommand();
        new BukkitRunnable() {
            @Override
            public void run() {
                setCenterBorder();

            }
        }.runTaskTimer(this, 0, 10);
    }

    private void initCommand() {
        Objects.requireNonNull(getCommand("setBorderRadius")).setExecutor(this);
        Objects.requireNonNull(getCommand("setBorderAngle")).setExecutor(this);
        Objects.requireNonNull(getCommand("resetBorderAngle")).setExecutor(this);
        Objects.requireNonNull(getCommand("resetBorderRadius")).setExecutor(this);
        Objects.requireNonNull(getCommand("getworld")).setExecutor(this);
        Objects.requireNonNull(getCommand("penis")).setExecutor(this);
        Objects.requireNonNull(getCommand("lobby")).setExecutor(this);
        Objects.requireNonNull(getCommand("hub")).setExecutor(this);
        Objects.requireNonNull(getCommand("setlobby")).setExecutor(this);
    }

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        String advancementName = event.getAdvancement().getKey().getKey();
        Player player = event.getPlayer();
        WorldBorder worldBorder = player.getWorld().getWorldBorder();
        if(stringList.contains(advancementName)){
            worldBorderSize = (int)worldBorder.getSize() + 10;
            expandWorldBorder(worldBorder, worldBorderSize);
            player.sendMessage(ChatColor.YELLOW+"Пространство расширяется. " + "Текущее значение барьера: " + worldBorderSize);
            System.out.println("Текущее значение барьера: " + worldBorderSize);
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(event.getPlayer().getName().equals("Mattstar")) event.getPlayer().setGameMode(GameMode.CREATIVE);
        teleportToLobby(event);
    }
    private void teleportToLobby(PlayerEvent event) {
        Player player = event.getPlayer();
        player.teleport(lobbyLocation);
        player.sendMessage("Вы были телепортированы в лобби!");
        System.out.println("EtherExp: Игрок " + player.getName() + " телепортирован в лобби");
    }
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String[] split = event.getMessage().split(" ");
        Player player = event.getPlayer();
        if (player.isOp()) {
            if (split.length > 0 && split[0].equalsIgnoreCase("/setBorderRadius")) {
                if (split.length > 1) {
                    try {
                        changeRadius(event, split);
                    } catch (NumberFormatException e) {
                        player.sendMessage("Некорректное значение радиуса. Введите дробное число с точкой.");
                    }
                } else {
                    player.sendMessage("Использование: /setBorderRadius <радиус>");
                }
            } else if (split.length > 0 && split[0].equalsIgnoreCase("/setBorderAngle")) {
                if (split.length > 1) {
                    try {
                        changeAngle(event, split);
                    } catch (NumberFormatException e) {
                        player.sendMessage("Некорректное значение угла. Введите дробное число с точкой.");
                    }
                } else {
                    player.sendMessage("Использование: /setBorderRadius <угол>");
                }
            } else if (split.length > 0 && split[0].equalsIgnoreCase("/resetBorderAngle")) {
                change_angle = 0.006;
                System.out.println("Текущее значение угла: " + change_angle);
            } else if (split.length > 0 && split[0].equalsIgnoreCase("/resetBorderRadius")) {
                change_angle = 0.1;
                System.out.println("Текущее значение угла: " + change_angle);
            }
            if (split[0].equalsIgnoreCase("/getworld"))
                if (split.length > 1) {
                    String nameWorld = split[1];
                    World world = getWorld(nameWorld);
                    if(world != null) event.getPlayer().sendMessage("Мир " + nameWorld + " успешно получен!");
                    else event.getPlayer().sendMessage("Мир " + nameWorld + " не существует!");
                }else event.getPlayer().sendMessage("Использование: /getworld <имя_мира>");
            if (split.length > 0 && split[0].equalsIgnoreCase("/setlobby")) {
                lobbyLocation = player.getLocation();
                player.sendMessage("Точка успешно установлена!");
                System.out.println("EtherExp: Текущие координаты спавна: X: " + lobbyLocation.getX() + " Y: " + lobbyLocation.getY() + " Z: " + lobbyLocation.getZ());
            }else player.sendMessage("Использование: /setlobby");
        }else player.sendMessage("Недостаточно прав!");
        if (split[0].equalsIgnoreCase("/penis") && !player.getWorld().getName().equals("world")) {
            String nameWorld = "world";
            World world = getWorld(nameWorld);
            if (world != null){
                final Location locationWorldBorder = world.getWorldBorder().getCenter();
                final double x = locationWorldBorder.getX();
                final double y = world.getHighestBlockYAt(locationWorldBorder);
                final double z = locationWorldBorder.getZ();
                final Location newLocation = new Location(world, x, y, z);
                player.teleport(newLocation);
                System.out.println("EtherExp: Игрок " + player.getName() + " телепортирован в мир World");
            }
        }
        if (split[0].equalsIgnoreCase("/lobby") || split[0].equalsIgnoreCase("/hub")) teleportToLobby(event);
    }


    public World getWorld(String name) {
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
    private void changeAngle(PlayerCommandPreprocessEvent event, String[] split) {
        double new_change_angle = Double.parseDouble(split[1]);
        event.getPlayer().sendMessage("Установлен радиус угла: " + new_change_angle);
        change_angle = new_change_angle;
        event.getPlayer().sendMessage("Предыдущее значение угла: " + change_angle);
        System.out.println("Текущее значение угла: " + change_angle);
    }
    private void changeRadius(PlayerCommandPreprocessEvent event, String[] split) {
        double new_change_radius = Double.parseDouble(split[1]);
        event.getPlayer().sendMessage("Установлен радиус барьера: " + new_change_radius);
        change_radius = new_change_radius;
        event.getPlayer().sendMessage("Предыдущее значение барьера: " + change_radius);
        System.out.println("Текущее значение радиуса: " + change_radius);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setCenterBorder(){
        WorldBorder worldBorder = Bukkit.getWorlds().get(0).getWorldBorder();
        long t = System.currentTimeMillis();
        double newWorldBorderX = radius * Math.cos(angle);
        double newWorldBorderY = radius * Math.sin(angle);
        worldBorder.setCenter(newWorldBorderX, newWorldBorderY);
        radius += change_radius;
        angle += change_angle;
    }
}