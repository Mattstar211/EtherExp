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

    public double getChangeAngleAdvancement() {
        return this.change_angle_advancement;
    }
    public void setChangeAngleAdvancement(double changeAngleAdvancement) {
        this.change_angle_advancement = changeAngleAdvancement;
    }
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

    public List<String> getNameAdmin() {
        return nameAdmin;
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

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public double getChange_radius(){
        return this.change_radius;
    }
    public void setChange_radius(double change_radius){
        this.change_radius = change_radius;
    }
    public double getChange_angle(){
        return this.change_angle;
    }
    public void setChange_angle(double change_angle){
        this.change_angle = change_angle;
    }
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

    public static void sendErrorMessage(Exception e, int string) {
        System.out.println("EtherExp: Ошибка " + e.getMessage() + " Строка: " + string);
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
}

