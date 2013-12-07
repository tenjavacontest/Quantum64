package ten.java.Quantum64;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ten.java.Quantum64.listener.PlayerListener;

public class Main extends JavaPlugin {
	public void onEnable() {
		Bukkit.getLogger().log(Level.INFO, "[DRAGON WARS] Enabeling plugin - Made by Quantum64 for ten.java code war");
	}
	
	public void setupEvents(){
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	}
}
