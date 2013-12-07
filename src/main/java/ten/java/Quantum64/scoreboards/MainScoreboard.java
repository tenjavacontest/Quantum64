package ten.java.Quantum64.scoreboards;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import ten.java.Quantum64.Main;

public class MainScoreboard implements Runnable {
	Main plugin;

	public MainScoreboard(Main base) {
		plugin = base;
	}

	public void run() {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			final Objective objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);

			if (objective == null) {
				createScoreboard(player);
			} else {
				updateScoreboard(player, false);
			}
		}
	}

	private void createScoreboard(Player player) {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("Global", "dummy");
		objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getGlobalScoreboardTitle()));
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		if (player.isOnline()) {
			try {
				player.setScoreboard(scoreboard);
			} catch (IllegalStateException ex) {
				Bukkit.getLogger().log(Level.SEVERE, "The scoarboard could not be created for player " + player.getDisplayName() + "!");
				objective = null;
				return;
			}

			updateScoreboard(player, true);
		}
	}

	private void updateScoreboard(Player player, boolean complete) {
		final Objective objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getGlobalScoreboardTitle()));
		sendScore(objective, "&6Max Players", 12, complete);
		sendScore(objective, "&e" + getPlugin().getServer().getMaxPlayers(), 11, complete);
		sendScore(objective, "&0", 10, complete);
		sendScore(objective, "&6Players", 9, complete);
		sendScore(objective, "&e" + getPlugin().getServer().getOnlinePlayers(), 8, complete);
		sendScore(objective, "&f", 7, complete);
		sendScore(objective, "&6Players Alive", 5, complete);
		sendScore(objective, "&1" + plugin.getDragons().size(), 6, complete);
		sendScore(objective, "&c", 5, complete);
		sendScore(objective, "&6Players Dead", 4, complete);
		sendScore(objective, "&4" + (plugin.getServer().getOnlinePlayers().length - plugin.getDragons().size()), 3, complete);
		sendScore(objective, "&7", 2, complete);
		sendScore(objective, "&f&lPlugin By: &4Q&cu&6a&en&2t&au&bm&36&14", 1, complete);
	}

	public static void sendScore(Objective objective, String title, int value, boolean complete) {

		final Score score = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.translateAlternateColorCodes('&', title)));

		if (title.startsWith("$")) {
			title = "";
		}

		if (complete && value == 0) {
			// Have to use this because the score wouldn't send otherwise
			score.setScore(-1);
		}

		score.setScore(value);
	}

	public Main getPlugin() {
		return plugin;
	}
}
