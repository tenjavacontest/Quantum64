package ten.java.Quantum64;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import ten.java.Quantum64.listener.PlayerListener;

public class Main extends JavaPlugin {

	public final int dragonSpeed = 5;

	private String globalScoreboardTitle;

	final HashMap<String, LivingEntity> dragons = new HashMap<String, LivingEntity>();

	public void onEnable() {
		Bukkit.getLogger().log(Level.INFO, "[DRAGON WARS] Enabeling plugin - Made by Quantum64 for ten.java code war");
	}

	public void setupEvents() {
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	}

	public String getGlobalScoreboardTitle() {
		return globalScoreboardTitle;
	}

	public void setGlobalScoreboardTitle(String newTitle) {
		globalScoreboardTitle = newTitle;
	}

	public HashMap<String, LivingEntity> getDragons() {
		return dragons;
	}
}
