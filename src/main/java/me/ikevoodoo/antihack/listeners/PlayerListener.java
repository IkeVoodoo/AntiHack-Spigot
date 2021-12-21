package me.ikevoodoo.antihack.listeners;

import me.ikevoodoo.antihack.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void on(AsyncPlayerChatEvent event) {
        if(Main.shouldDeny(event.getMessage())) {
            event.setCancelled(true);
            event.setMessage("");
            Bukkit.getScheduler().scheduleSyncDelayedTask(
                    Main.INSTANCE, () -> {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + event.getPlayer().getName() + " You have been banned for trying to hack the server!");
                        Bukkit.broadcast(ChatColor.RED + event.getPlayer().getName() + " has been banned for trying to hack the server.", "antihack.notify");
                    }, 1L);
        }
    }

}
