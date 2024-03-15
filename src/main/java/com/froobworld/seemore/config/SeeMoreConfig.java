package com.froobworld.seemore.config;

import com.froobworld.nabconfiguration.*;
import com.froobworld.nabconfiguration.annotations.Entry;
import com.froobworld.nabconfiguration.annotations.Section;
import com.froobworld.nabconfiguration.annotations.SectionMap;
import com.froobworld.seemore.SeeMore;
import org.bukkit.World;

import java.io.File;

public class SeeMoreConfig extends NabConfiguration {
    private static final int VERSION = 3;

    public SeeMoreConfig(SeeMore seeMore) {
        super(
                new File(seeMore.getDataFolder(), "config.yml"),
                () -> seeMore.getResource("config.yml"),
                i -> seeMore.getResource("config-patches/" + i + ".patch"),
                VERSION
        );
    }

    @Entry(key = "update-delay")
    public final ConfigEntry<Integer> updateDelay = ConfigEntries.integerEntry();

    @Entry(key = "log-changes")
    public final ConfigEntry<Boolean> logChanges = new ConfigEntry<>();

    @SectionMap(key = "world-settings", defaultKey = "default")
    public final ConfigSectionMap<World, WorldSettings> worldSettings = new ConfigSectionMap<>(World::getName, WorldSettings.class, true);

    public static class WorldSettings extends ConfigSection {

        @Entry(key = "maximum-view-distance")
        public final ConfigEntry<Integer> maximumViewDistance = ConfigEntries.integerEntry();

    }
    
    @Section(key = "integration-settings")
    public final IntegrationSettings integrationSettings = new IntegrationSettings();
    
    public static class IntegrationSettings extends ConfigSection {
        
        @Section(key = "essentials")
        public final Essentials essentials = new Essentials();
        
        public static class Essentials extends ConfigSection {
                
            @Entry(key = "afk-view-distance")
            public final ConfigEntry<Integer> afkViewDistance = ConfigEntries.integerEntry();
        }
    }

}
