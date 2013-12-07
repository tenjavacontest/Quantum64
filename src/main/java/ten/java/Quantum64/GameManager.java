package ten.java.Quantum64;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

import ten.java.Quantum64.entity.EntityDragon;

public class GameManager {

	private GameState currentState;
	private Main plugin;

	public GameManager(Main plugin) {
		this.plugin = plugin;
		this.currentState = GameState.WAITING_FOR_PLAYERS;
	}

	public static enum GameState {
		WAITING_FOR_PLAYERS, STARTING_COUNTDOWN, IN_GAME, POST_GAME
	}

	public void setState(GameState state) {
		this.currentState = state;
		onStateChanged();
	}

	public GameState getState() {
		return currentState;
	}

	public void onStateChanged() {

	}

	public void beginGame() {
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			Location loc = p.getLocation();
	        org.bukkit.World w = loc.getWorld();
	       
	        Object cBworld = ((CraftWorld)loc.getWorld()).getHandle();
	        EntityDragon entityDragon = new EntityDragon(this.plugin, p, loc, (net.minecraft.server.v1_7_R1.World)cBworld);
	        if (!((net.minecraft.server.v1_7_R1.World)cBworld).addEntity(entityDragon, CreatureSpawnEvent.SpawnReason.CUSTOM))
	        {
	          p.kickPlayer(ChatColor.RED + "Error whilst spawning dragon!");
	          continue;
	        }
	        
	        LivingEntity dragon = (LivingEntity)entityDragon.getBukkitEntity();
	        this.plugin.dragons.put(p.getName(), dragon);
	        dragon.setPassenger(p);
	      }
		}
}
