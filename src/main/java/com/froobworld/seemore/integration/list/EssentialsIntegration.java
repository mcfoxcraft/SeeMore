package com.froobworld.seemore.integration.list;

import com.froobworld.seemore.SeeMore;
import com.froobworld.seemore.integration.Integration;
import net.ess3.api.events.AfkStatusChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class EssentialsIntegration implements Integration, Listener {
    
    private final SeeMore plugin;
    
    public EssentialsIntegration(SeeMore plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAfk(AfkStatusChangeEvent event) {
        Player player = event.getAffected().getBase();
        
        int afkViewDistance = this.plugin.getSeeMoreConfig().worldSettings.of(player.getWorld()).disableForAfkPlayers.get() ? Bukkit.getServer().getViewDistance() : this.plugin.getSeeMoreConfig().integrationSettings.essentials.afkViewDistance.get();
        if (afkViewDistance == -1) {
            return;
        }

        boolean nowAfk = event.getValue();
        this.plugin.getViewDistanceController().setTargetViewDistance(player, player.getClientViewDistance(), false, false, nowAfk);
    }
}
