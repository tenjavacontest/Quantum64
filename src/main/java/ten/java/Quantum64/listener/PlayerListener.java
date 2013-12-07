package ten.java.Quantum64.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import ten.java.Quantum64.GameManager.GameState;
import ten.java.Quantum64.Main;

public class PlayerListener implements Listener {
	Main plugin;

	public PlayerListener(Main plugin) {
		this.plugin = plugin;
	}

	public void playerJoinEvent(PlayerJoinEvent event) {
		if (plugin.getGameManager().getState() == GameState.IN_GAME || plugin.getGameManager().getState() == GameState.POST_GAME) {
			event.getPlayer().sendMessage(ChatColor.WHITE + "Dragon Wars has already started.  YOu are free to spectate this round.");
		}
		event.setJoinMessage(ChatColor.YELLOW + event.getPlayer().getDisplayName() + " has joined!" + ChatColor.RED + "(" + ChatColor.YELLOW + (plugin.getServer().getOnlinePlayers().length + 1) + ChatColor.RED + "/" + ChatColor.YELLOW);
		event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "=======================================================");
		event.getPlayer().sendMessage(ChatColor.WHITE + "WELCOME TO: DRAGON WARS - A plugin by Quantum64 for ten.java");
		event.getPlayer().sendMessage(ChatColor.WHITE + "Ride your dragon and crush your enemies!");
		event.getPlayer().sendMessage(ChatColor.WHITE + "Collect powerups where lightning strikes to gain buffs and weapons");
		event.getPlayer().sendMessage(ChatColor.RED + "The game will start when " + (plugin.getServer().getMaxPlayers() - (plugin.getServer().getOnlinePlayers().length) + 1));
		event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "=======================================================");
	}

	public void playerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (player.getLocation().getX() > 300)
			player.teleport(new Location(player.getWorld(), player.getLocation().getX() - 50, player.getLocation().getY(), player.getLocation().getZ()));
		if (player.getLocation().getX() < -300)
			player.teleport(new Location(player.getWorld(), player.getLocation().getX() + 50, player.getLocation().getY(), player.getLocation().getZ()));
		if (player.getLocation().getY() > 300)
			player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 50, player.getLocation().getZ()));
		if (player.getLocation().getY() < -300)
			player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 50, player.getLocation().getZ()));
		if (player.getLocation().getZ() > 300)
			player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ() - 50));
		if (player.getLocation().getZ() < -300)
			player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ() + 50));
	}
}
