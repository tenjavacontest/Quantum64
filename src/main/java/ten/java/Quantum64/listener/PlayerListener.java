package ten.java.Quantum64.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ten.java.Quantum64.Main;

public class PlayerListener implements Listener {
	Main plugin;

	public PlayerListener(Main plugin) {
		this.plugin = plugin;
	}

	public void playerJoinEvent(PlayerJoinEvent event) {
		event.setJoinMessage(ChatColor.YELLOW + event.getPlayer().getDisplayName() + " has joined!" + ChatColor.RED + "(" + ChatColor.YELLOW + (plugin.getServer().getOnlinePlayers().length + 1) + ChatColor.RED + "/" + ChatColor.YELLOW);
		event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "=======================================================");
		event.getPlayer().sendMessage(ChatColor.WHITE + "WELCOME TO: DRAGON WARS - A plugin by Quantum64 for ten.java");
		event.getPlayer().sendMessage(ChatColor.WHITE + "Ride your dragon and crush your enemies!");
		event.getPlayer().sendMessage(ChatColor.WHITE + "Collect powerups where lightning strikes to gain buffs and weapons");
		event.getPlayer().sendMessage(ChatColor.RED + "The game will start when " + (plugin.getServer().getMaxPlayers() - (plugin.getServer().getOnlinePlayers().length) + 1));
		event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "=======================================================");
	}
}
